package com.rs.game.npc.others;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class CastleWarBarricade extends NPC {

	private int team;
	
	private boolean isLit = false;
	
	private int litTimer = 0;
	
	private int cooldown = -1;
	
	public boolean isLit() {
		return isLit;
	}

	public CastleWarBarricade(int team, WorldTile tile) {
		super(1532, tile, -1, true, true);
		setCantFollowUnderCombat(true);
		this.team = team;
	}

	@Override
	public void processNPC() {
		if (cooldown > 0) 
			cooldown--;
		cancelFaceEntityNoCheck();
		if (getId() == 1533 && isLit) {
			litTimer++;
			isLit = true;
			if (litTimer == 10) {
				isLit = false;
				litTimer = 0;
				sendDeath(this);
	} else if (litTimer == 9) {
		setNextAnimation(new Animation(9658));	
			}
		}
	}

	public void litFire(Player plr) {
		if (cooldown > 0) {
			plr.sendMessage("The barricade is still damp.");
			return;
		}
		transformIntoNPC(1533);
		isLit = true;
	}
	
	public void unlightFire() {
		isLit = false;
		litTimer = 0;
		cooldown = 3;
		transformIntoNPC(1532);
	}

	public void explode() {
		// TODO gfx
		setNextGraphics(new Graphics(2738));
		sendDeath(this);
	}

 @Override
 public void sendDeath(Entity killer) {
  resetWalkSteps();
  getCombat().removeTarget();
  if (this.getId() != 1533) {
   setNextAnimation(null);
   reset();
   setLocation(getRespawnTile());
   finish();
  } else {
   super.sendDeath(killer);
  }
  CastleWars.removeBarricade(team, this);
 }

}