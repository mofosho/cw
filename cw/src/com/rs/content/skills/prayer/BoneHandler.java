package com.rs.content.skills.prayer;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Burying.Bone;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;


public class BoneHandler {

	/**
	 * 
	 * @param player
	 *            The player using it
	 * @param object
	 *            The object used with
	 * @param itemId
	 *            The item used
	 * @param amount
	 *            The amount of bones you wish to create
	 */

	public static void handleItemOnObject(final Player player,
			final WorldObject object, final int itemId, final int amount) {
		if (amount > player.getInventory().getItems().getNumberOf(itemId)) {
			player.setBonesToMake(player.getInventory().getItems().getNumberOf(itemId));
		}
		if (object.getId() == 44945 && object != null) {
			for (final Bone bone : Bone.values()) {
				if (itemId == bone.getId()) {
					player.setBonesOnAltar(true);
					player.getTemporaryAttributtes().put("bones",
							Integer.valueOf(0));
					player.getTemporaryAttributtes().put("bitem", itemId);
					player.setBonesToMake(amount);
					if (!player.isUsingBones()) {
						player.getPackets()
								.sendRunScript(
										108,
										new Object[] { "How many bones do you want to use?" });
					} else {
						player.setUsingBones(true);
					}
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.setUsingBones(true);
							if (player.isBonesOnAltar()
									&& player.getBonesToMake() != -1) {
								player.getDialogueManager().startDialogue("",
										new Object[0]);
								if (player.getBonesToMake() < 0
										|| player.getBonesToMake() == 0) {
									stop();
									return;
								}
								if (!player.getInventory().containsItem(itemId,
										1)) {
									stop();
									return;
								}
								player.getInventory().deleteItem(itemId, 1);
								player.getSkills().addXp(Skills.PRAYER,
										bone.getExperience() * (int) 2.5);
								player.setNextAnimation(new Animation(713));
								player.getPackets().sendGraphics(new Graphics(624, 50, 150), object);
								player.setBonesToMake(player.getBonesToMake() - 1);
								player.sendMessage("The gods are very pleased with your offering.");
							} else {
								player.setUsingBones(true);
								player.setBonesOnAltar(true);
								stop();
								return;
							}
						}

					}, 0, (int) 1.5);
					return;
				}
			}
		}
	}

}
