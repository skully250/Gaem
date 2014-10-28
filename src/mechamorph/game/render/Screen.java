package mechamorph.game.render;

import mechamorph.game.render.gui.Gui;
import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.world.tile.Tile;

public class Screen {

	public int width, height;
	private int xOffset, yOffset;
	public int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width*height];
		this.xOffset = this.yOffset = 0;
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void renderGui(Gui gui, int xp, int yp) {
		
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.size; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.size; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.size || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.size];
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2 || flip == 3) ys = 31 - y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) xs = 31 - x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int pix = sprite.pixels[xs + ys * 32];
				if (pix != 0xFFD67FFF) pixels[xa + ya * width] = pix;
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yPix = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xPix = x + xp;
				if (xPix < 0 || yPix < 0 || xPix > width || yPix > height) continue;
				pixels[xPix + yPix * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
}