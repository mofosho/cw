package com.rs.game.player.dialogues;

import com.rs.cache.loaders.NPCDefinitions;


public class NomadSw extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Why hello there stranger!, Welcome to soulwars!" }, IS_NPC, npcId, 9827);
		
	}
	


	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Now what did you need?" },
					IS_NPC, npcId, 9827);
			stage = 1;
			
			
		} else if (stage == 1) {
				sendEntityDialogue((short) 241,
						new String[] { player.getDisplayName(),
								"I wanted to know some things...." }, (byte) 0,
						player.getIndex(), 9827);
				stage = 2;
		} else	if(stage ==2) {
			sendOptionsDialogue(
					"Select an option",
					"What is Soul Wars?", "I want to see your shop",
					"Nevermind." );
			stage = 3;
		} else	if (stage == 3) {
				if (componentId == OPTION_1) {
					sendEntityDialogue(SEND_1_TEXT_CHAT,
							new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Go into one of the portals your self, its quite fun." },
							IS_NPC, npcId, 9827);
				stage = -1;
				} else if(componentId == OPTION_2) {
			
					player.getInterfaceManager().sendInterface(276);
					player.getPackets().sendIComponentText(276, 59,
							"Zeal: " + player.zeal);
					end();
				}
				}
				
				
				
		/*		case2:
		player.getInterfaceManager().sendInterface(276);
				player.getPackets().sendIComponentText(276, 59,
						"" + player.zeal);

				break;*/
		}
		
				
		
		
	@Override
	public void finish() {

	}

}