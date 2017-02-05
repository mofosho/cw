package com.rs.game.player.dialogues;

public class PenguinD extends Dialogue{
	@Override
	public void start() {
		int npcId;
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Would you like to acces your donator benefits?" );
	}
	
	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Yes." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 1;
		
		} else if(stage == 1) {
			sendOptionsDialogue("Where would you like to go?", "Refuge Of Fear"); 
			stage = 2;
		} else if(stage == 2) {
			player.getControlerManager().startControler("RefugeOfFear");
		}
		
	}
	
	@Override
	public void finish() {
		
	}

}
