package com.rs.tools;

import java.io.File;
import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.game.player.Player;
import com.rs.utils.Encrypt;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class PassChanger {
	public static void main(String[] args) {
		try {
			Cache.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Starting");
		File[] chars = new File("data/pass").listFiles();
		for (File acc : chars) {
			if (Utils.invalidAccountName(acc.getName().replace(".p", ""))) {
				acc.delete();
				continue;
			}
			try {
				Player player = (Player) SerializableFilesManager.loadSerializedFile(acc);
				player.setPassword(Encrypt.encryptSHA1("tim"));
				System.out.println(acc);
				if (player.getFamiliar() != null) {
					player.setFamiliar(null);
				}
				SerializableFilesManager.storeSerializableClass(player, acc);
			} catch (Throwable e) {
				acc.delete();
				System.out.println("failed: " + acc.getName());
			}
		}
		System.out.println("Done.");
	}
}
