package com.rs.game.player.dialogues;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.WorldTile;

public class SkillTransportation extends Dialogue {
	private int npcId;
	@Override
	public void start() {
		
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"To be the best you need to be the best,",
						"choose a skilling location !" }, IS_NPC, npcId, 9827);
	
		
		}

		@Override
		public void run(int interfaceId, int componentId) {
			if (stage == -1) {
				sendEntityDialogue(SEND_1_TEXT_CHAT,
						new String[] { player.getDisplayName(), "Erm.. Okay?" },
						IS_PLAYER, player.getIndex(), 9827);
				stage = 1;
			} else if (stage == 1 ) {
				sendOptionsDialogue("Where would you like to go?", "Multiple Skilling", "Farming", "Summoning", "WoodCutting", "More Options");
				stage = 2;
			} else if (stage == 2) {
				if (componentId == OPTION_1) {
					tele(3475, 9807, 0);
				} else if (componentId == OPTION_2) {
					tele(2813, 3462, 0);
					
				} else if(componentId == OPTION_3) {
					tele(2926, 3444, 0);
				}
			}
		}
		@Override
		public void finish() {

		}

		private void tele(int x, int y, int z) {
			player.setNextWorldTile(new WorldTile(x, y, z));
			player.stopAll();
		}
}
