package mechamorph.game.level;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class Level {

	/*
	 * Having multiple ways to generate a level is great :D
	 */

	public int[] pixels;

	//Might have subclasses later hence protected
	protected int width, height;
	protected int[] tiles;

	/**
	 * Makes a random level, made to the size specified
	 * @param size The size of the generated level this will be 
	 * multiplied by itself to make the tile amount
	 */
	public Level(int size) {
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
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.tiles = new int[width * height];
	}

	/**
	 * Loads a level using an image and analise's it to generate a level
	 * based on the image provide
	 * @param path The file path to the image that will serve as the level
	 */
	public Level(String path) {

	}

	/**
	 * Generates a level based on an int array that will have 
	 * numbers corresponding to each Tile in the game
	 * @param level The array of the entire level
	 */
	public Level(int[] level) {

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

	//Type checking for file, random or int array
	public void generateLevel(String type) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				switch(pixels[x + y * width]) {
				//5 Tiles to begin with
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				}
			}
		}
	}
}