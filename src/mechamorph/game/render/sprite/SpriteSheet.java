package mechamorph.game.render.sprite;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public int[] pixels;
	public int size;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.size = size;
		this.pixels = new int[size*size];
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
