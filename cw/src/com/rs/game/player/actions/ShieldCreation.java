package com.rs.game.player.actions;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;

/**
 *
 * @author Adam
 */
public class ShieldCreation 
{
    
    private static final int[] SIGILS = { 13746, 13748, 13750, 13752 };
    private static final int[] SHIELDS = { 13738, 13740, 13642, 13744 };
    
    public static void handleShieldCreation(Player player, int itemUsed, int usedWith)
    {
        if(!player.getInventory().containsItem(itemUsed, 1)) 
        {
            return;
        }
        
        if(!player.getInventory().containsItem(usedWith, 1)) 
        {
            return;
        }
        
        for(int i = 0; i < SIGILS.length; i++)
        {
            if(itemUsed == SIGILS[i] && usedWith == 13736 || usedWith == SIGILS[i] && itemUsed == 13736)
            {
                if(80 > player.getSkills().getLevel(Skills.SMITHING)) {
                    return;
                }
                if(!player.getInventory().containsItem(13754, 1))
                player.getPackets().sendGameMessage("You combine the sigil with the shield to create a spirite shield.");
                player.getInventory().deleteItem(SIGILS[i], 1);
                player.getInventory().deleteItem(13734, 1);
                player.getInventory().addItem(SHIELDS[i], 1);
            }
        }
        
        if(itemUsed == 11286 && usedWith == 1540 || usedWith == 11286 && itemUsed == 1540) {
            if(!player.getInventory().containsItem(2347, 1)) {
                player.getPackets().sendGameMessage("You need a hammer to make a Dragonfire shield.");
                return;
            }
            player.getInventory().deleteItem(11286, 1);
			player.getInventory().deleteItem(1540, 1);
            player.getInventory().addItem(11284, 1);
			player.getPackets().sendGameMessage("You combine the Anti Dragon fireshielf with the Draconic Visage and create a DragonFireShield");
        }
    }
}
