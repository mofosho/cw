package com.rs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alex.store.Index;
import com.rs.cache.Cache;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ItemsEquipIds;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.content.exchange.GrandExchange;
import com.rs.cores.CoresManager;
import com.rs.game.Region;
import com.rs.game.RegionBuilder;
import com.rs.game.World;
import com.rs.game.minigames.soulwars.SoulLobby;
import com.rs.game.minigames.soulwars.SoulWars;
import com.rs.game.npc.combat.CombatScriptsHandler;
import com.rs.game.player.Player;
import com.rs.game.player.content.FishingSpotsHandler;
import com.rs.game.player.content.FriendChatsManager;
import com.rs.game.player.controlers.ControlerHandler;
import com.rs.game.player.cutscenes.CutscenesHandler;
import com.rs.game.player.dialogues.DialogueHandler;
import com.rs.net.ServerChannelHandler;
import com.rs.utils.DTRank;
import com.rs.utils.DisplayNames;
import com.rs.utils.IPBanL;
import com.rs.utils.ItemBonuses;
import com.rs.utils.ItemExamines;
import com.rs.utils.Logger;
import com.rs.utils.MapArchiveKeys;
import com.rs.utils.MapAreas;
import com.rs.utils.MusicHints;
import com.rs.utils.NPCBonuses;
import com.rs.utils.NPCCombatDefinitionsL;
import com.rs.utils.NPCDrops;
import com.rs.utils.NPCSpawns;
import com.rs.utils.ObjectSpawns;
import com.rs.utils.PkRank;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;
import com.rs.utils.huffman.Huffman;

public final class Launcher {

	public static void main(String[] args) throws Exception {
		long currentTime = Utils.currentTimeMillis();
		if (Settings.HOSTED) {
			
			// System.setErr(new PrintStream(new
			// FileOutputStream("data/auto/err.txt")));
			// System.setOut(new PrintStream(new
			// FileOutputStream("data/auto/out.txt")));
		}
	
		Logger.log("Launcher", "Initing Cache...");
		Cache.init();
		Logger.log("GrandExchange", "Loading Data...");
		GrandExchange.init();
		ItemsEquipIds.init();
		Huffman.init();
		Logger.log("Launcher", "Initing Data Files...");
		DisplayNames.init();
		IPBanL.init();
		PkRank.init();
		DTRank.init();
		minigames();
		MapArchiveKeys.init();
		MapAreas.init();
		ObjectSpawns.init();
		NPCSpawns.init();
		NPCCombatDefinitionsL.init();
		NPCBonuses.init();
		NPCDrops.init();
		ItemExamines.init();
		ItemBonuses.init();
		MusicHints.init();
		ShopsHandler.init();
		Logger.log("Launcher", "Initing Fishing Spots...");
		FishingSpotsHandler.init();
		Logger.log("Launcher", "Initing NPC Combat Scripts...");
		CombatScriptsHandler.init();
		Logger.log("Launcher", "Initing Dialogues...");
		DialogueHandler.init();
		Logger.log("Launcher", "Initing Controlers...");
		ControlerHandler.init();
		Logger.log("Launcher", "Initing Cutscenes...");
		CutscenesHandler.init();
		Logger.log("Launcher", "Initing Friend Chats Manager...");
		FriendChatsManager.init();
		Logger.log("Launcher", "Initing Cores Manager...");
		CoresManager.init();
		Logger.log("Launcher", "Initing World...");
		World.init();
		Logger.log("Launcher", "Initing Region Builder...");
		RegionBuilder.init();
		Logger.log("Launcher", "Initing Server Channel Handler...");
		try {
			ServerChannelHandler.init();
		} catch (Throwable e) {
			Logger.handle(e);
			Logger.log("Launcher",
					"Failed initing Server Channel Handler. Shutting down...");
			System.exit(1);
			return;
		}
		Logger.log("Launcher", "Server took "
				+ (Utils.currentTimeMillis() - currentTime)
				+ " milli seconds to launch.");
		addAccountsSavingTask();
		if (Settings.HOSTED)
			addUpdatePlayersOnlineTask();
	//	addCleanMemoryTask();
		// Donations.init();
	}

	private static void setWebsitePlayersOnline(int amount) throws IOException {
		URL url = new URL("http://www.matrixftw.com/updateplayeramount.php?players="+ amount + "&auth=JFHDJF3847234");
		url.openStream().close();
	}

	private static void addUpdatePlayersOnlineTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					setWebsitePlayersOnline(World.getPlayers().size());
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 2, 2, TimeUnit.MINUTES);
	}

	@SuppressWarnings("unused")
	private static void addCleanMemoryTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					cleanMemory(Runtime.getRuntime().freeMemory() < Settings.MIN_FREE_MEM_ALLOWED);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 10, TimeUnit.MINUTES);
	}

	private static void addAccountsSavingTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					saveFiles();
				} catch (Throwable e) {
					Logger.handle(e);
				}

			}
		}, 15, 15, TimeUnit.MINUTES);
	}

	public static void saveFiles() {
		for (Player player : World.getPlayers()) {
			if (player == null || !player.hasStarted() || player.hasFinished())
				continue;
			SerializableFilesManager.savePlayer(player);
		}
		DisplayNames.save();
		IPBanL.save();
		PkRank.save();
		DTRank.save();
	}

	public static void cleanMemory(boolean force) {
		if (force) {
			ItemDefinitions.clearItemsDefinitions();
			NPCDefinitions.clearNPCDefinitions();
			ObjectDefinitions.clearObjectDefinitions();
			for (Region region : World.getRegions().values())
				region.removeMapFromMemory();
		}
		for (Index index : Cache.STORE.getIndexes())
			index.resetCachedFiles();
		CoresManager.fastExecutor.purge();
		System.gc();
	}

	public static void shutdown() {
		try {
			closeServices();
		} finally {
			System.exit(0);
		}
	}

	public static void closeServices() {
		ServerChannelHandler.shutdown();
		CoresManager.shutdown();
		if (Settings.HOSTED) {
			try {
				setWebsitePlayersOnline(0);
			} catch (Throwable e) {
				Logger.handle(e);
			}
		}
	}
	public static void minigames() {
		//SoulLobby.minutes = 5;
		//SoulWars.startedGame = false;
		//SoulLobby.redWait.clear();
		//SoulLobby.blueWait.clear();
		//SoulLobby.allWaiting.clear();
		//SoulWars.playing.clear();
	}
	 public static void restart() {
	        closeServices();
	        System.gc();
	        try {
	            System.exit(0); // temp usualy 2
	        } catch (Throwable e) {
	            Logger.handle(e);
	        }

	    }

	    public static void restarter() {

	                try {
	                    File file = new File("./run.bat");
	                    Runtime.getRuntime().exec("cmd.exe /C start " + file.getPath());
	                    shutdown();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                System.exit(0);
	         
	    }

	    public static final boolean compile(long delay) {
	        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	        executor.schedule(new Runnable() {

	            @Override
	            public void run() {
	                try {
	                    File file = new File("./compile.bat");
	                    Runtime.getRuntime().exec("cmd.exe /C start " + file.getPath());
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }, delay, TimeUnit.MILLISECONDS);
	        return true;
	    }
	    
	


	private Launcher() {

	}
	
	public void Databases() {
		
	}
	
	public static long START_TIME = System.currentTimeMillis();
    {
	    }

}
