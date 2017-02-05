package com.rs.game.player.actions.slayer;

import java.io.Serializable;

import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;


/**
 * Slayer is a members-only skill that allows players to kill monsters which are
 * often otherwise immune to damage. Slayer was introduced on 26 January 2005.
 * Players get a Slayer task from one of seven Slayer Masters, and players gain
 * Slayer experience for killing monsters that they are assigned.
 * 
 * @author Emperial
 * 
 */
public class SlayerTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7184740247844324413L;

	/**
	 * The players current assigned task
	 */
	private SlayerTasks currentTask;

	/**
	 * The monsters left.
	 */
	private int monstersLeft = -1;

	/**
	 * The slayer task
	 * 
	 * @return
	 */
	public SlayerTasks getTask() {
		return currentTask;
	}

	/**
	 * The monsters left to kill
	 */
	public int getTaskMonstersLeft() {
		return monstersLeft;
	}

	/**
	 * Sets the player current task
	 * 
	 * @param task
	 */
	public void setTask(SlayerTasks task) {
		currentTask = task;
	}

	/**
	 * Sets monsters left to kill
	 * 
	 * @param i
	 */
	public void setMonstersLeft(int i) {
		monstersLeft = i;
	}

	/**
	 * Called on npc death if part of task.
	 */
	public void onMonsterDeath(Player player, NPC n) {
		player.getSkills().addXp(Skills.SLAYER,
				n.getCombatDefinitions().getHitpoints() / 10);
		monstersLeft--;
		int[] checkpoints = new int[] { 2, 3, 4, 5, 10, 15, 25, 50, 75, 100,
				125, 150 };
		for (int i : checkpoints) {
			if (monstersLeft == i) {
				player.getPackets().sendGameMessage(
						"You're doing great, Only " + monstersLeft + " "
								+ getTask().simpleName + " left to slay.");
				player.hasTask = true;
			}
		}
		if (monstersLeft < 1) {
			player.getPackets()
					.sendGameMessage(
							"You have finished your slayer task, talk to a slayer master for a new one.");
			player.hasTask = false;
		}
	}

}