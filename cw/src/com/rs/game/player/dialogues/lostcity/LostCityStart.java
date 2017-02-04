package com.rs.game.player.dialogues.lostcity;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class LostCityStart extends Dialogue {

	private int npcId;
	
	@Override
	public void start() {
		if (player.cityStarted == 1&& player.shamus > 0||player.lostCityFinished == 1) {
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(650).name,
							"I knew you wouldn't find the Leprechaun..." }, IS_NPC, 650, 9829);
			stage = 17;
			return;
		}
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Hello there traveller." }, IS_NPC, npcId, 9827);

	}
	
	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Select an Option", "What are you camped out here for?","Do you know any good adventures I can go on?");
			stage = 1;
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				sendEntityDialogue(SEND_1_TEXT_CHAT,
						new String[] { player.getDisplayName(), "What are you camped out here for?" },
						IS_PLAYER, player.getIndex(), 9827);
				stage = 2;
			} else {
				sendEntityDialogue(SEND_1_TEXT_CHAT,
						new String[] { player.getDisplayName(), "Do you know of any good Adventures I can go on?" },
						IS_PLAYER, player.getIndex(), 9827);
				stage = 17;
			}
		} else if (stage == 2) {
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"We're looking for Zanaris...GAH! I mean we're not","here for particular reason at all." }, IS_NPC, npcId, 9829);
			stage = 3;
			
		} else if (stage == 3) {
			sendOptionsDialogue("Select an Option", "Who's Zanaris?","What's Zanaris?","What makes you think its out here?");
			stage = 4;
	} else if (stage == 4) {
		if (componentId == OPTION_1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Who's Zanaris?" },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 17;
		} else if (componentId == OPTION_2) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "What's Zanaris?" },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 17;
		} else {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "What makes you think its out here?" },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 5;
		}
	} else if (stage == 5) {
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Don't you know of the legends that tell of the magical","city, hidden in the swam... Uh, no, you're right, we're","wasting our time here." }, IS_NPC, npcId, 9827);
		stage = 6;
	} else if (stage == 6) {
		sendOptionsDialogue("Select an Option", "If its hidden how are you planning to find it?","Bullshit! Theres no such thing!");
		stage = 7;
	} else if (stage == 7) {
		if (componentId == OPTION_1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "If its hidden how are you planning to find it?" },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 8;
		} else if (componentId == OPTION_2) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Theres no such thing!" },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 17;
		} else {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "What makes you think its out here?" },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 17;
		} 
	}else if (stage == 8) {
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Well, we don't want to tell anyone else about that","because we don't want anyone else sharing in all that","glory and treasure." }, IS_NPC, npcId, 9827);
	stage = 9;
	} else if (stage == 9) {
		sendOptionsDialogue("Select an Option", "Please tell me.","Looks like you don't know either.");
		stage = 10;
	} else if (stage == 10) {
		if (componentId == OPTION_1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Please tell me." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 17;
		} else {
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Well, it looks to me like YOU don't know EITHER","seeing as you've all sat around here." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 11;
	}
		} else if (stage == 11) {
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"Of course we know! We just haven't found which tree","the stupid leprechaun's hiding in yet!" }, IS_NPC, npcId, 9827);
			stage = 12;
	} else if (stage == 12) {
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"GAH! I didn't mean to tell you that! Look, just forget I","said anything, okay?" }, IS_NPC, npcId, 9827);
		stage = 13;
	} else if (stage == 13) {
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { player.getDisplayName(), "So a leprechaun knows where Zanaris is?" },
				IS_PLAYER, player.getIndex(), 9827);
		stage = 14;
	} else if (stage == 14) {
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Ye.. uh no. No not at all. And even if he did - which","he doesn't - he DEFINATELY ISN'T hiding in some","tree around here. Nope, definately not. Honestly." }, IS_NPC, npcId, 9827);
		stage = 15;
	} else if (stage == 15) {
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { player.getDisplayName(), "Thanks for the help!" },
				IS_PLAYER, player.getIndex(), 9827);
		stage = 16;
	} else if (stage == 16) {
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Help? What help? I didn't help! Please don't say I did.","I'll get in trouble!" }, IS_NPC, npcId, 9827);
		player.cityStarted = 1;
		player.shamus = 1;
		stage = 17;
	} else if (stage == 17) {
		player.getPackets().sendGameMessage("He doesn't look interested in talking anymore");
		end();
		}
}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
