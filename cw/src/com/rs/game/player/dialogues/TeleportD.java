package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;

public class TeleportD extends Dialogue{
	public TeleportD() {
	}

	@Override
	public void start() {
		stage = 2;
		sendOptionsDialogue("What would you like to do?", "Castle Wars", "Load Stone Options", "Nothing");
		
	}
	@Override
	public void run(int interfaceId, int componentId) {
	if (stage == 2) {
		if (componentId == OPTION_1)
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2442, 3089, 0));
		player.getInterfaceManager().closeChatBoxInterface();
		}
	if (componentId == OPTION_2 ) {
		player.getInterfaceManager().sendInterface(1092);
		player.getInterfaceManager().closeChatBoxInterface();
	}
	if (componentId == OPTION_3) {
		player.getInterfaceManager().closeChatBoxInterface();
	}
	}
		



	@Override
	public void finish() {
	}
	


}
