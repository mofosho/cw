package com.rs.tools;

import java.io.File;
import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.game.player.Player;
import com.rs.utils.SerializableFilesManager;

public class UnbanAll {

	public static void main(String[] args)throws Exception { {
		try {
			Cache.init();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File dir = new File("./data/characters/");
		File[] accs = dir.listFiles();
		for (File acc : accs) {
			Player player = (Player) SerializableFilesManager
					.loadSerializedFile(acc);
			System.out.println(acc);
			player.setPermBanned(false);
			player.setBanned(0);
		}
	}
	}
}
