package mechamorph.engine.render.gui;

import mechamorph.engine.render.Screen;
import mechamorph.engine.render.sprite.SpriteSheet;

public class Text {
	
	public static final String chars = "" + //
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ.,!?\"'/\\<>()[]{}" + //
			"abcdefghijklmnopqrstuvwxyz_				" + //
			"0123456789+-=*:;÷≈ƒÂ						" + //
			"";
	
	private Screen screen;
	private int width, height;
	public int[][] pixels;
	
	//Giving width and height variables as it will essentially be another renderer
	//Over the already existing 2D renderer that is in place
	public Text(Screen screen) {
		this.screen = screen;
		this.width = screen.width;
		this.height = screen.height;
		this.pixels = new int[height][width];
	}
	
	public void render(SpriteSheet spritesheet, int xOffs, int yOffs, int xo, int yo, int w, int h, int col) {
		for (int y = 0; y < h; y++) {
			int yPix = y + yOffs;
			if (yPix < 0 || yPix >= height) continue;
			for (int x = 0; x < w; x++) {
				int xPix = x + xOffs;
				if (xPix < 0 || xPix >= width) continue;
				
				int src = spritesheet.pixels[(y+yo)] [(x+xo)];
				if (src != 0xffff00ff) screen.pixels[yPix][xPix] = src & col;
				
			}
		}
	}
	
	public void draw(String string, int x, int y, int col) {
		for (int i = 0; i < string.length(); i++) {
			int ch = chars.indexOf(string.charAt(i));
			if  (ch < 0) continue;
			int xx = ch % 42;
			int yy = ch / 42;
			render(SpriteSheet.font, x + i * 6, y, xx * 6, yy * 8, 5, 8, col);
		}
	}

}
