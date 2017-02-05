package com.rs.content.utils;

import java.text.DecimalFormat;

import com.rs.game.player.Player;



/**
 * 
 * 
 * @author: Adam
 *
 */

public class MoneyPouch {

	
	
	
	/**
	 * 
	 * Withdraw's the amount in the pouch.
	 * @param player
	 */
	
	
	
	public static void withdraw(Player player) {
		
}
	
	
	
/**
 * Add's coins to pouch.
 * @param player
 */
	
	
	public static void addToPouch(Player player) {
	int coins = player.getInventory().getNumerOf(995);
	if (coins + player.getCoins() >= 2147000000) {
		player.out("You're money pouch is full.");
		return;
	}
	player.setCoins(player.getCoins() + player.getInventory().getNumerOf(995));
	player.getInventory().containsOneItem(995);
	player.getInventory().deleteItem(995, 2147000000);
	player.out(getFormattedNumber(coins) + " coins have been added to your money pouch.");
	player. getPackets().sendRunScript(5561, 1, "n", coins);
	  player.getPackets().sendRunScript(5560, player.getCoins(), "n");
	  
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @param player
	 */
	
	
	
	public static void checkCoins(Player player){
		if (player.getCoins() >= 2147000000) {
			player.out("You're money pouch is full.");
			
			return;
		}
	}
	
	public static String getFormattedNumber(int amount) {
		return new DecimalFormat("#,###,##0").format(amount).toString();
	}
	
	/**
	 * 5557 1 toggle
5560 n set amount
5561 1 n add amount popup + glow
5561 0 n remove amount popup + glow
Scripts.
	 */
	
}
