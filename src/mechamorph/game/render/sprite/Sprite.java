package mechamorph.game.render.sprite;

public class Sprite {
	
	public static Sprite block = new Sprite(SpriteSheet.tiles, 0, 7, 32);
	public static Sprite grass = new Sprite(SpriteSheet.tiles, 0, 0, 16);
	
	public int[] pixels;
	
	private int x, y, width, height, size;
	private SpriteSheet sheet;
	
	public Sprite(SpriteSheet sheet, int x, int y, int size) {
		this.width = this.height = this.size = size;
		this.x = x * size;
		this.y = y * size;
		this.pixels = new int[size*size];
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int colour) {
		this.width = this.height = this.size = size;
		this.pixels = new int[size*size];
		setColour(colour);
	}
	
	public void setColour(int colour) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = colour;
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void load() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.size];
			}
		}
	}
}