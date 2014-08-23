package mechamorph.game.render;

import mechamorph.game.render.sprite.Sprite;

public class Screen {

	private int width, height;
	public int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width*height];
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = Sprite.grass.pixels[(x / 16) + (y / 16) * Sprite.grass.getWidth()];
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yPix = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xPix = x + xp;
				if (xPix < 0 || yPix < 0 || xPix > width || yPix > height) continue;
				if (sprite.pixels[x + y * sprite.getWidth()] != 0xFFD67FFF)
				pixels[xPix + yPix * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
}