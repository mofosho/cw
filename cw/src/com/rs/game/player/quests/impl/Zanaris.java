package com.rs.game.player.quests.impl;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;


public class Zanaris {
	
	public static final Animation DISSAPEAR = new Animation(3254);
	
	protected static Player player;
	
	private static final int SHAMUS = 654;
	
	public static NPC findNPC(int id) {
		for (NPC npc : World.getNPCs()) {
			if (npc == null || npc.getId() != id)
				continue;
			return npc;
		}
		return null;
	}
	
	public static void catchShamus() {
		NPC shamus = findNPC(SHAMUS);
		player.getHintIconsManager().addHintIcon(shamus, 0, -1, false);
		
	}

	public static void finishOff(final Player player) {
		if (player.getBoneDelay() > Utils.currentTimeMillis())
			return;
		player.addBoneDelay(3000);
		player.getPackets().sendSound(2738, 0, 1);
		player.setNextAnimation(new Animation(3254));
		player.setNextGraphics(new Graphics(2670));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(new WorldTile(2452, 4473, 0));
				player.setNextAnimation(new Animation(3255));
			}
		}, 2);
	}
}
