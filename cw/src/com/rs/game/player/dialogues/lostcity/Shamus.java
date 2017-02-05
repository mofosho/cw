package com.rs.game.player.dialogues.lostcity;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class Shamus extends Dialogue {

	private int npcId;
	
	@Override
	public void start() {
		if (player.shamus > 1||player.lostCityFinished == 1) {
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(654).name,
							"Now what does yer want...?" }, IS_NPC, 654, 9829);
			stage = 17;
			return;
		}
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Ay yer big elephent! Yer've caught me, to be sure!","What would an elephant like yer be wanting wid 'ol","Shamus then?" }, IS_NPC, npcId, 9827);

	}
	
	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "I want to find Zanaris."},
					IS_PLAYER, player.getIndex(), 9827);
			stage = 3;
			
		} else if (stage == 3) {
			sendEntityDialogue(SEND_3_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"Zanaris is it now? Well well well... Yer'll be needing to","be going to that little shed out there in the ","swamp, so you will." }, IS_NPC, npcId, 9827);
			stage = 4;
	} else if (stage == 4) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "...but... I thought... Zanaris was a city...?" },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 5;
		
	} else if (stage == 5) {
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Aye that it is!" }, IS_NPC, npcId, 9827);
		stage = 6;
	} else if (stage == 6) {
		sendOptionsDialogue("Select an Option","How does it fit in a shed then?","I've been in that shed, I didn't see a city.");
		stage = 7;
	} else if (stage == 7) {
		if (componentId == OPTION_1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "...How does it fit in a shed then?" },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 8;
		} else {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "I've been in that shed, I didn't see a city." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 17;
		} 
	}else if (stage == 8) {
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Ah yer stupid elephant! The city isnt IN the shed! The","doorway to the shed is being a portal to Zanaris so it","is." }, IS_NPC, npcId, 9827);
	stage = 9;
	} else if (stage == 9) {
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { player.getDisplayName(), "So I just walk into the shed and end up in Zanaris"," then?" },
				IS_PLAYER, player.getIndex(), 9827);
		stage = 10;
	} else if (stage == 10) {
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Oh, was I forgetting to say? Yer need to be carrying a","Dramonwood staff to be getting in there! Otherwise yer'll","just be ending up in the shed." }, IS_NPC, npcId, 9827);
			stage = 11;
		} else if (stage == 11) {
					sendEntityDialogue(SEND_1_TEXT_CHAT,
							new String[] { player.getDisplayName(), "So where would I get a staff?" },
							IS_PLAYER, player.getIndex(), 9827);
			stage = 12;
	} else if (stage == 12) {
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Dramenwood staffs are crafted from the branches of the ","Dramen tree, so they are. I hear there's a Dramen ","tree over onthe island of Entrana in a cave" }, IS_NPC, npcId, 9827);
		stage = 13;
	} else if (stage == 13) {
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"or some such. There would probably be a good place","for an elephant like yer to be starting looking I reckon." }, IS_NPC, npcId, 9827);
		stage = 15;
	} else if (stage == 14) {
		sendEntityDialogue(SEND_3_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Ye.. uh no. No not at all. And even if he did - which","he doesn't - he DEFINATELY ISN'T hiding in some","tree around here. Nope, definately not. Honestly." }, IS_NPC, npcId, 9827);
		stage = 15;
	} else if (stage == 15) {
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"The monks are running a ship from Port Sarim to","Entrana, I hear too. Now leave me alone yer elephant!" }, IS_NPC, npcId, 9827);
		stage = 17;
		player.shamus += 1;
		player.cityStarted = 2;
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
