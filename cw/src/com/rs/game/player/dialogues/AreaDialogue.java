package com.rs.game.player.dialogues;

import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.minigames.rfd.RecipeforDisaster;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class AreaDialogue extends Dialogue {

	public AreaDialogue() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Where would you like to go?", "Traning",
						"Minigames", "Frost Dragons", "Corp", "More options");
		
	}

	@Override
	public void run(int interfaceId, int componentId) {
 if (stage == 1) {
	 if (componentId == OPTION_1) {
			sendOptionsDialogue("Where would you like to go?", "Rock Crabs",
					"Yaks");
			stage = 2;	
	 } else if( componentId == OPTION_2) {

			sendOptionsDialogue("Where would you like to go?",
					"SoulWars", "Clan Wars", "Duel Arena",
					"Castle Wars", "More Options");
			stage = 3;
	 } else if(componentId == OPTION_3) {
		 teleportPlayer(1297, 4510, 0);
	 } else if(componentId == OPTION_4) {
		 Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966,
					4383, 2));
	 } else if(componentId == OPTION_5) {
		 sendOptionsDialogue("Where would you like to go?",
					"Armadyl", "Zamorak", "Bandos", "Sara", "More Options");
			stage = 4;
	 }

 } else if (stage == 2) {
		if (componentId == OPTION_1) {
			teleportPlayer(3555, 9947, 0);
		} else if (componentId == OPTION_2){
			teleportPlayer(2326, 3801, 0);
		}
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1890, 3177, 0));
			}	else if (componentId == OPTION_2) {
				teleportPlayer(2994, 9679, 0);
			
			}			else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3365,
						3275, 0));
				player.getControlerManager().startControler("DuelControler");
			}			else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, CastleWars.LOBBY);
			}
			else if (componentId == OPTION_5) {
				stage = 20;
				sendOptionsDialogue("Where would you like to go?",
						"Fight Pits", "Fight Kiln", "Fight Caves",
						"Crucible", "More Options");
			}
		} else if (stage == 20) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4608,
						5061, 0));
			} else if (componentId == OPTION_2) 
				Magic.sendNormalTeleportSpell(player, 0, 0, FightKiln.OUTSIDE);
				else if (componentId == OPTION_3) {
					Magic.sendNormalTeleportSpell(player, 0, 0, FightCaves.OUTSIDE);
				}	else if (componentId == OPTION_4) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3358, 6112, 0));
				}	else if (componentId == OPTION_5) {
					sendOptionsDialogue("Where would you like to go?",
							"Brimhaven agility", "Trivia", "Dominion Tower", "More Options");
					stage = 21;
				}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				teleportPlayer(2838, 5297, 2);
			} else if (componentId == OPTION_2) {
				teleportPlayer(2925, 5330, 2);
			} else if (componentId == OPTION_3)
				teleportPlayer(2870, 5363, 2);
			else if (componentId == OPTION_4) {

				teleportPlayer(2901, 5264, 0);
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Where would you like to go?",
						"Queen Black Dragon", "Nex", "Tormented Deamons", "Jadinkos", "More Options");
				stage = 5;
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {//qbdplayer.getInterfaceManager().closeChatBoxInterface(); 			player.getControlerManager().startControler("QueenBlackDragonControler");
				player.getControlerManager().startControler("QueenBlackDragonControler");
				player.getInterfaceManager().closeChatBoxInterface(); 
			} else if (componentId == OPTION_2)
				
				teleportPlayer(2905, 5203, 0); 
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562,
						5739, 0));
				else if (componentId == OPTION_4) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3011, 9274, 0));
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Where would you like to go?",
						"Gnome Agility.", "Magic Bank.", "Multi Area (PvP)", "Wests (PvP)", "More Options");
				stage = 6;
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1) 
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2470,
						3436, 0));
			
			else if (componentId == OPTION_2) 
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2538,
						
						4715, 0));
			
		else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3240,
						3611, 0));
				player.getControlerManager().startControler("Wilderness");
		}
			else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2984,
						3596, 0));
				player.getControlerManager().startControler("Wilderness");
			}
			else if (componentId == OPTION_5) {
				sendOptionsDialogue("Where would you like to go?",
						"Kalphite Queen", "RuneSpan", "Living Rock Caverns",
						"Beggening Options");
				stage = 7;
			}
		} else if (stage == 7) {
			if (componentId == OPTION_1) 
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3226, 3108, 0));
			else if (componentId == OPTION_2)
				return;
			else if(componentId == OPTION_3) 
				teleportPlayer(3653, 5115, 0);
			else if (componentId == OPTION_4) {
			stage = 1;
			}
		} else if (stage == 50) {
			if (componentId == OPTION_1) {
 Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3689,
			3503, 0));
			} else if (componentId == OPTION_2) {
				end();
				RecipeforDisaster.enterRfd(player);
			} else if (componentId == OPTION_3) {
				stage = 1;
			}
		} else if(stage == 21) {

			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2709,
						9464, 0));
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2647, 9378, 0));
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3366,
						3083, 0));
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("Where would you like to go?",
						"Zombies", "Recipe for Disaster", "Beggening Options");
				stage = 50;
			}
		}
	
				}
	/**
	 * } else if (componentId == OPTION_2) {
				sendOptionsDialogue("Where would you like to go?",
						"Zombies","Beggening Options");
				stage = 50;
			}
		} else if (stage == 50) {
			if (componentId == OPTION_1) {
 Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3689,
			3503, 0));
			} else if (componentId == OPTION_2) {
				stage = 1;
			}
			
	 * @param x
	 * @param y
	 * @param z
	 */
							

					
	/*
	 * if (componentId == OPTION_1) {
				
			}
			if (componentId == OPTION_2){
				
			}
			if(componentId == OPTION_3) {
				
			}
			if (componentId == OPTION_4) {
				
			}
			if (componentId == OPTION_5) {
				
			}										
	 */
										
								
						

		
	

	private void teleportPlayer(int x, int y, int z) {
		player.setNextGraphics(new Graphics(111));
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();

	}

	@Override
	public void finish() {
	}


}
