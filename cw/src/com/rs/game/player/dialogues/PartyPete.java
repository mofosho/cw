package com.rs.game.player.dialogues;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.content.PartyRoom;

public class PartyPete extends Dialogue {
	private int npcId;
	@Override
	public void start() {
		sendEntityDialogue(SEND_3_TEXT_CHAT, 
			new String[] { 
				NPCDefinitions.getNPCDefinitions(659).name, 
				"The items in the party chest are worth "+PartyRoom.getTotalCoins()+"", 
				"coins! Hang around until they drop and you might get",
				"something valueable!"
		}, IS_NPC, 659, 9843);
	}

	@Override
	public void run(int interfaceId, int componentId) {
if (stage == -1) {
			
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Now what did you need?" },
					IS_NPC, npcId, 9827);
			stage = 1;
}
	}

	@Override
	public void finish() {
		
	}

}
