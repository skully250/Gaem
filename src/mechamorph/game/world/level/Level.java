package mechamorph.game.world.level;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import mechamorph.game.render.Screen;
import mechamorph.game.world.tile.Tile;

public class Level {

	public int width, height;
	private int[] pixels;
	public byte[][] tiles;
	
	private String path;

	public Level(int width, int height, String path) {
		this.width = width;
		this.height = height;
		this.path = path;
		this.tiles = new byte[width][height];
		loadLevel();
	}
	
	public Level(int width, int height, Tile tile) {
		this.width = width;
		this.height = height;
		this.tiles = new byte[width][height];
	}

	public void loadLevel() {
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			int w = image.getWidth();
			int h = image.getHeight();
			this.pixels = new int[w * h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch(Exception e) {
			e.printStackTrace();
		}
		colorGenLevel();
	}
	
	public Tile getTile(int xTile, int yTile) {
		return Tile.getTile(tiles[xTile][yTile]);
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

	public void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Tile.getTileID(tiles[x][y]);
			}
		}
	}
	
	public void colorGenLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (pixels[x + y * width] == 0x00000000) tiles[x][y] = 1;
				if (pixels[x + y * width] == 0xffffffff) tiles[x][y] = 3;
				else tiles[x][y] = 2;
			}
		}
	}
}