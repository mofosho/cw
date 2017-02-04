package com.rs.tools;

import java.io.File;

import com.rs.game.player.Player;
import com.rs.utils.SerializableFilesManager;

public class Unban {

	public static void main(String[] args) throws Exception {
		File acc = new File("./data/characters/prothieum.p");
		Player player = (Player) SerializableFilesManager
				.loadSerializedFile(acc);
		player.setPermBanned(false);
		player.setBanned(0);
		SerializableFilesManager.storeSerializableClass(player, acc);
	}
}
