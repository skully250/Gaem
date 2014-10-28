package mechamorph.game.render.sprite;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public static SpriteSheet tiles = new SpriteSheet("res/tiles.png", 256);
	
	private String path;
	public int[] pixels;
	public int size, width, height;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.width = this.height = this.size = size;
		this.pixels = new int[size*size];
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		load();
	}
	
	public void load() {
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
