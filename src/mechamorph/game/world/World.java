package mechamorph.game.world;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import mechamorph.game.render.Screen;
import mechamorph.game.world.tile.Tile;

public class World {

	/*
	 * Having multiple ways to generate a level is great :D
	 */

	public int[] pixels;
	public int width, height;
	public int[] tiles;

	/**
	 * Makes a random level, made to the size specified
	 * @param size The size of the generated level this will be 
	 * multiplied by itself to make the tile amount
	 */
	public World(int size) {
		this.width = this.height = size;
		this.pixels = new int[size * size];
		this.tiles = new int[size * size];
	}

	/**
	 * Makes a random level that is not cubed in size, but instead
	 * using independent width and height variables
	 * @param width The width of the level to be generated
	 * @param height The height of the level to be generated
	 */
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.tiles = new int[width * height];
		generateLevel();
	}

	/**
	 * Loads a level using an image and analise's it to generate a level
	 * based on the image provide
	 * @param path The file path to the image that will serve as the level
	 */
	public World(String path) {

	}

	public void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				switch(pixels[x + y * width]) {
				case 0x00ff00:
					tiles[x + y * width] = Tile.col_grass;
					break;
				case 0xff0000:
					tiles[x + y * width] = Tile.col_stone;
					break;
				}
			}
		}
	}

	public void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
			int x1 = (xScroll + screen.width + 16) >> 4;
			int y0 = yScroll >> 4;
			int y1 = (yScroll + screen.height + 16) >> 4;
			for (int y = y0; y < y1; y++) {
				for (int x = x0; x < x1; x++) {
					getTile(x, y).render(x, y, screen);
				}
			}
	}

	//TBD whether tiles will get assigned based on R, G or B
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass;
		if (tiles[x + y * width] == Tile.col_stone) return Tile.stone;
		return Tile.voidTile;
	}
}