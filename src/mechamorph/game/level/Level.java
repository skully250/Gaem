package mechamorph.game.level;

import java.util.Random;

import mechamorph.game.level.tile.Tile;

public class Level {

	/*
	 * Having multiple ways to generate a level is great :D
	 */

	//Might have subclasses later hence protected
	protected int width, height;
	protected int[] tiles;

	private Random random = new Random();

	public Level(int size) {
		this.width = this.height = size;
		this.tiles = new int[size * size];
	}

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new int[width * height];
	}

	public Level(String path) {

	}

	public Level(int[] level) {

	}

	//Type checking for file, random or int array
	public void generateLevel(String type) {
		if (type == "random" || type == "Random") {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					tiles[x + y] = random.nextInt(2);
					//Working on for later
					//tiles[x + y] = random.nextInt(Tiles.tiles.length);
				}
			}
		}
		if (type == "file" || type == "File") {
			//TODO: Value assigning
		}
		if (type == "int" || type == "Int") {
			//TODO: Value assigning
		}

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				switch(tiles[x + y]) {
				case 1:
					tiles[x + y] = Tile.grass.getID();
					break;
				case 2: 
					tiles[x + y] = Tile.stone.getID();
					break;
				}
			}
		}
	}

}