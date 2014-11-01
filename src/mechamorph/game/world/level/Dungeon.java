package mechamorph.game.world.level;

import mechamorph.engine.level.Level;

public class Dungeon extends Level {
	
	public Dungeon(int width, int height, String path) {
		super(width, height, path);
	}
	
	public void generateLevel() {
		super.generateLevel();
		generateEnemies();
	}
	
	//Still TBD on whether enemies should be generated with or after the level
	public void generateEnemies() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
			}
		}
	}
}