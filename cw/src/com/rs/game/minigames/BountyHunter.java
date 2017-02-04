package com.rs.game.minigames;

import java.util.LinkedList;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTasksManager;

/**
 * @author Viper
 * 
 */

public class BountyHunter {
	public static LinkedList<Player> gamePlayers = new LinkedList<Player>();
	private static WorldTile GAME_LOCATION = new WorldTile(3745, 5886, 0);
	private static WorldTile OUTSIDE_GAME_LOCATION = new WorldTile(2785, 4382, 0);
	public static boolean startedGame = false;
	public static void startGame(Player player) {
		gamePlayers.add(player);
		player.setCanPvp(true);
		player.isInBountyHunter = true;
		player.setNextWorldTile(GAME_LOCATION);
		player.getControlerManager().startControler("BountyControler");
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 10 : 8, 591);
	if (player.getSkills().getCombatLevelWithSummoning() > 100) {
		player.setNextWorldTile(new WorldTile(3746, 5825, 0));
		player.setBountyHunterSkullR();
		} else 
		if (player.getSkills().getCombatLevelWithSummoning() > 80) {
			player.setNextWorldTile(new WorldTile(3745, 5886, 0));
			player.setBountyHunterSkullB();
		} else 
		if (player.getSkills().getCombatLevelWithSummoning() > 50) {
			player.setNextWorldTile(new WorldTile(3713, 5854, 0));
				player.setBountyHunterSkullR();
		}	else {
			player.setBountyHunterSkullGR();
			player.setNextWorldTile(new WorldTile(3773, 5854, 0));
		player.getPackets().sendIComponentText(591, 9, "Telopya" );//Target Name here
		player.getPackets().sendGameMessage("You enter the Cavern.");
		}
		}
	
	public static void leaveGame(Player player) {
		if (gamePlayers.contains(player)) {
			gamePlayers.remove(player);
		}
		WorldTasksManager.removeTask();
		player.setCanPvp(false);
		player.getPackets()
				.closeInterface(
						player.getInterfaceManager().hasRezizableScreen() ? 11
								: 0);
		player.getAppearence().generateAppearenceData();
		player.getEquipment().refresh(null);
		player.getControlerManager().forceStop();
		player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 10 : 730);
		player.setNextWorldTile(OUTSIDE_GAME_LOCATION);
		}
	
	public static void endGame(Player player) {
		if (gamePlayers.contains(player)) {
			gamePlayers.remove(player);
		}
		player.isInBountyHunter = false;
		startedGame = false;
		player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 10 : 7);
		player.setNextWorldTile(OUTSIDE_GAME_LOCATION);
		player.getPackets().sendGameMessage("GAMEENDED");
		Item stone = new Item(12845);
		Item elemental_rune = new Item(12850);
		Item catalytic_rune = new Item(12851);
		if (player.getInventory().getItems().contains(elemental_rune)|| player.getInventory().getItems().contains(stone)|| player.getInventory().getItems().contains(catalytic_rune)) {
			player.getInventory().deleteItem(stone.getId(), Integer.MAX_VALUE);//stone
			player.getInventory().deleteItem(elemental_rune.getId(), Integer.MAX_VALUE);//elemental rune
			player.getInventory().deleteItem(catalytic_rune.getId(), Integer.MAX_VALUE);//catalytic rune
		}
	}
	
}
