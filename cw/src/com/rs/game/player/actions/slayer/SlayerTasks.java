package com.rs.game.player.actions.slayer;

/**
 * This is a list of assignable tasks
 * 
 * @author Emperial
 * 
 */
public enum SlayerTasks {
	/**
	 * TURAEL TASKS
	 */
	CHICKENS("Chickens", TaskSet.TURAEL, 15, 50, "Chicken"), BEARS("Bears",
			TaskSet.TURAEL, 15, 50, "Grizzly bear cub", "Grizzly bear"), COWS(
			"Cows", TaskSet.TURAEL, 15, 50, "Cow calf", "Cow"), GOBLINS(
			"Goblins", TaskSet.TURAEL, 15, 50, "Goblin", "Cave goblin guard"), ICEFIENDS(
			"Icefiends", TaskSet.TURAEL, 10, 20, "Icefiend"), MINOTAURS(
			"Minotaurs", TaskSet.TURAEL, 15, 50, "Minotaur"), ZOMBIES(
			"Zombies", TaskSet.TURAEL, 15, 50, "Zombie", "Armoured zombie"), CRAWLING_HANDS(
			"Crawling hand", TaskSet.TURAEL, 15, 50,
			"Zombie hand", "Skeletal hand"), STS("Ghosts", TaskSet.TURAEL,
			15, 50, "Ghost"), BATS("Bats", TaskSet.TURAEL, 15, 50, "Bat",
			"Giant bat"), BIRDS("Birds", TaskSet.TURAEL, 15, 50, "Chicken",
			"Terrorbird");

	private SlayerTasks(String simpleName, TaskSet type, int min, int max,
			String... monsters) {
		this.type = type;
		this.slayable = monsters;
		this.simpleName = simpleName;
		this.min = min;
		this.max = max;
	}

	/**
	 * A simple name for the task
	 */
	public String simpleName;

	/**
	 * The task set
	 */
	public TaskSet type;
	/**
	 * The monsters that will effect this task
	 */
	public String[] slayable;
	/**
	 * The minimum amount of monsters the player may be assigned to kill
	 */
	public int min;
	/**
	 * The maximum amount of monsters the player may be assigned to kill
	 */
	public int max;
}
