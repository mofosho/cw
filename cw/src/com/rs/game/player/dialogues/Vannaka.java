package com.rs.game.player.dialogues;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.actions.slayer.Slayer;
import com.rs.game.player.actions.slayer.SlayerMaster;



public class Vannaka extends Dialogue {

	/**
	 * Starts the dialogue
	 */

	public Vannaka() {

	}

	@Override
	public void start() {
		npcId = ((Integer) parameters[0]).intValue();
		sendEntityDialogue((short) 241,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Hello, brave warrior. What would you like?" },
				(byte) 1, npcId, 9827);
	}

	/**
	 * Runs the dialogue
	 */

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue(
					"What would you like to say?",
					"I would like a slayer task.",
					"What is my current slayer task?");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				if (player.hasTask == false) {
					Slayer.assignTask(player, SlayerMaster.TURAEL);
					sendEntityDialogue((short) 241, new String[] {
							NPCDefinitions.getNPCDefinitions(1597).name,
							"Your slayer task is to kill "
									+ player.slayerTask.getTask().simpleName +  " "
									+ "'s." }, (byte) 1, 1597, 9827);
				} else {
					sendEntityDialogue(
							(short) 243,
							new String[] {
									NPCDefinitions.getNPCDefinitions(1597).name,
									"You already have a slayer task! ",
									"You need to kill "
											+ player.slayerTask.getTaskMonstersLeft()
											+ " "
											+ player.slayerTask.getTask().simpleName +  "'s.",
									"Would you like a new slayer task?" },
							(byte) 1, 1597, 9827);
				}
				stage = 1;
			} else if (componentId == OPTION_2) {
				if (player.hasTask == true)
					sendEntityDialogue((short) 242, new String[] {
							NPCDefinitions.getNPCDefinitions(1597).name,
							"You have a short memory, don't you?",
							"You need to kill "
									+ player.slayerTask.getTaskMonstersLeft() + " "
									+ player.slayerTask.getTask().simpleName
									+ "'s." }, (byte) 1, 1597, 9827);
				else
					sendEntityDialogue((short) 241, new String[] {
							NPCDefinitions.getNPCDefinitions(1597).name,
							"Foolish warrior. You don't have a slayer task!" },
							(byte) 1, 1597, 9827);

				stage = -1;
			} else if (componentId == OPTION_3)
				end();
			else if (componentId == OPTION_4) {
				stage = 1;
			} else {
				end();
			}
		} else if (stage == 1) {
			sendOptionsDialogue(
					"Would you like a new Slayer task?", "Yes", "No" );
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				Slayer.assignTask(player, SlayerMaster.TURAEL);
				sendEntityDialogue((short) 241,
						new String[] {
								NPCDefinitions.getNPCDefinitions(1597).name,
								"Your slayer task is to kill "
										+ player.slayerTask.getTaskMonstersLeft()
										+ " "
										+ player.slayerTask.getTask().simpleName + "'s." },
						(byte) 1, 1597, 9827);
			} else {
				end();
				stage = -1;
			}
			stage = 3;
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				stage = 4;
			} else if (componentId == OPTION_2) {
				stage = 5;
			} else {
				end();
			}
		} else if (stage == 4) {
			stage = -2;
		} else if (stage == 5) {
			stage = -2;
		} else {
			end();
		}
	}

	@Override
	public void finish() {

	}

	/**
	 * Declares the npc ID
	 */

	private int npcId;
}
