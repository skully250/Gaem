package mechamorph.engine.level;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import mechamorph.engine.entity.Entity;
import mechamorph.engine.level.tile.Tile;
import mechamorph.engine.render.Screen;
import mechamorph.engine.util.math.Vector2i;

public class Level {

	public int width, height;
	private int[] pixels;
	public Tile[][] tiles;
	public Vector2i spawnPoint;
	
	private String path;

	public Level(int width, int height, String path) {
		this.width = width;
		this.height = height;
		this.path = path;
		this.tiles = new Tile[height][width];
		loadLevel();
	}
	
	public Level(int width, int height, Tile tile) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[height][width];
		setLevel(tile);
	}
	
	public void setLevel(Tile tile) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[y][x] = tile;
			}
		}
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
		generateLevel();
	}
	
	public boolean collides(int xPos, int yPos, Entity entity) {
		if (xPos < 0 || yPos < 0 || xPos >= width || yPos >= height) return true;
		else return tiles[yPos][xPos].collides(entity);
	}
	
	public Tile getTile(int xTile, int yTile) {
		if (xTile < 0 || yTile < 0 || xTile >= width || yTile >= height) return Tile.getTile((byte)0);
		return tiles[yTile][xTile];
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
				tiles[y][x] = Tile.grass;
				switch(pixels[x + y * width]) {
				//Color decision: ARGB
				case 0xff000000:
					tiles[y][x] = Tile.stone;
					tiles[y][x].setPos(x, y);
					break;
				case 0xffffff00:
					tiles[y][x] = Tile.SBrick;
					spawnPoint = new Vector2i(x*16, y*16);
					System.out.println("Spawnpoint at " + spawnPoint.getX() + " " + spawnPoint.getY());
					tiles[y][x].setPos(x, y);
					break;
				case 0xffff0000:
					tiles[y][x] = Tile.SBrick;
					tiles[y][x].setPos(x, y);
					break;
				case 0xff800000:
					tiles[y][x] = Tile.SBrick2;
					tiles[y][x].setPos(x, y);
					break;
				case 0xffffffff:
					tiles[y][x] = Tile.grass;
					tiles[y][x].setPos(x, y);
					break;
				}
			}
		}
	}
}