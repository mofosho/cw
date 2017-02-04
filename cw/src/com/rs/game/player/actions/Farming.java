package com.rs.game.player.actions;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

/**
 *
 * @author Adam
 */
public class Farming extends Action
{

    private Plant plant;
    
  
    
    public enum Plant
    {
        GUAM(1, 11, 199),
        MARRENTILL(14, 13.5, 201),
        TARRMOIN(19, 16, 203),
        HARRALANDER(26, 21.5, 205),
        RANARR(32, 27, 207);
        
        private Plant(int level, double exp, int herbId)
        {
            this.level = level;
            this.exp = exp;
            this.herbId = herbId;
        }
        
        private int level;
        private double exp;
        private int herbId;
        
        public int getLevel()
        {
            return level;
        }
       
        
        public double getExp()
        {
            return exp;
        }
        
        public int getHerbId()
        {
            return herbId;
        }
    }
    
    public Farming(Plant plant)
    {
        this.plant = plant;
    }
    
    @Override
    public boolean start(Player player)
    {
        if(!checkAll(player)) {
            return false;
        }
        return true;
    }
    
    private boolean checkAll(Player player)
    {
        if(!hasLevel(player)) {
            return false;
        }
        if(!hasRake(player)) {
            return false;
        }
        if(!player.getInventory().hasFreeSlots()) {
            player.getPackets().sendGameMessage("You've ran out of space in your inventory.");
            return false;
        }
        return true;
    }
    
    private boolean hasRake(Player player)
    {
        if(!player.getInventory().containsItem(5341, 1)) {
            player.getPackets().sendGameMessage("You need a rake to farm here.");
            return false;
        }
        return true;
    }
    
    private boolean hasLevel(Player player)
    {
        if(plant.getLevel() > player.getSkills().getLevel(Skills.FARMING)) {
            player.getPackets().sendGameMessage("You do not have the level required to farm this herb.");
            return false;
        }
        return true;
    }
    
    @Override
    public boolean process(Player player)
    {
        if(player.getSkills().getLevel(Skills.FARMING) < 5) {
            player.getSkills().addXp(Skills.FARMING, 388);
        }
        player.setNextAnimation(new Animation(2273));
        return true;
    }
    
    @Override
    public int processWithDelay(Player player)
    {
        this.addReward(player);
        return -1;
    }
    
    private void addReward(Player player)
    {
        String itemName = ItemDefinitions.getItemDefinitions(plant.getHerbId()).getName().toString();
        player.getInventory().addItem(plant.getHerbId(), 1);
        player.getSkills().addXp(Skills.FARMING, plant.getExp());
        player.getPackets().sendGameMessage("You farm the " + itemName + ".");
    }
    
    @Override
    public void stop(Player player)
    {
        this.setActionDelay(player, 3);
    }
}
