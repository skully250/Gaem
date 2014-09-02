package mechamorph.game.render.sprite;

public class Sprite {
	
	//Basic sprites, need better ones :c
	public static Sprite block = new Sprite(SpriteSheet.tiles, 0, 7, 32);
	public static Sprite grass = new Sprite(SpriteSheet.tiles, 0, 0, 16);
	
	public int[] pixels;
	
	private int x, y, width, height, size;
	private SpriteSheet sheet;
	
	/**
	 * Creates a new sprite, using a Spritesheet and the co-ordinates provided
	 * @param sheet The Spritesheet that contains the texture needed
	 * @param x The X location of the sprite on the Spritesheet
	 * @param y The Y location of the sprite on the Spritesheet
	 * @param size The size of the Sprite
	 */
	public Sprite(SpriteSheet sheet, int x, int y, int size) {
		this.width = this.height = this.size = size;
		this.x = x * size;
		this.y = y * size;
		this.pixels = new int[size*size];
		this.sheet = sheet;
		load();
	}
	
	/**
	 * Creates a sprite without a texture but of a certain colour, useful for
	 * collision detection or basic single color sprites for testing purposes
	 * 
	 * @param size The size of the Sprite created
	 * @param colour The colour of the Sprite created
	 */
	public Sprite(int size, int colour) {
		this.width = this.height = this.size = size;
		this.pixels = new int[size*size];
		setColour(colour);
	}
	
	/**
	 * Sets the color of all pixels to the color provided
	 * @param color The color that all pixels will be colored
	 */
	public void setColour(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	/**
	 * Loads the sprite from the spritesheet provided and sets the pixels
	 * for the sprite
	 */
	public void load() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.size];
			}
		}
	}
}