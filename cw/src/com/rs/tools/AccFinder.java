package com.rs.tools;

import java.io.File;
import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.game.player.Player;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;


/**
 * HOLY FUCK SO HELPFUL ON VACATIONxD
 * @author Adam
 *
 */
public class AccFinder {

	public static void main(String[] args) {
		try {
			Cache.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Starting");
		File[] chars = new File("data/characters").listFiles();
		for (File acc : chars) {
			if (Utils.invalidAccountName(acc.getName().replace(".p", ""))) {
				acc.delete();
				continue;
			}
			try {
				Player player = (Player) SerializableFilesManager.loadSerializedFile(acc);
				player.getBank().setItem(995, 1000000);
				player.getInventory().deleteItem(995, 2147000000);
					System.out.println("ds" + acc.getName());

			} catch (Throwable e) {
			}
		}
		System.out.println("Done.");
	}
}
