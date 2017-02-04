package com.rs.game.player.actions.slayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rs.game.player.Player;
import com.rs.utils.Utils;


public class Slayer {

	/**
	 * 0 = Hello 1 = Option
	 * 
	 * OTHER 2 = For your first task I'm assigning you to 3 = You still have a
	 * task 4 = Great your doing great, Your new task is
	 */

	public static String assignTask(Player player, SlayerMaster master) {
		player.hasTask = true;
		SlayerTask tasks = player.slayerTask;
		List<SlayerTasks> possibleTasks = new ArrayList<SlayerTasks>();
		for (SlayerTasks task : SlayerTasks.values()) {
			if (task.type == master.type) {
				possibleTasks.add(task);
			}
		}
		if (possibleTasks.isEmpty()) {
			return null;
		}
		Collections.shuffle(possibleTasks);
		SlayerTasks task = possibleTasks.get(0);
		tasks.setTask(task);
		tasks.setMonstersLeft(Utils.random(tasks.getTask().min,
				tasks.getTask().max));
		return tasks.getTaskMonstersLeft() + " " + tasks.getTask().simpleName;
	}

}
