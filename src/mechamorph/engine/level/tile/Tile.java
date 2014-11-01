package mechamorph.engine.level.tile;

import mechamorph.engine.entity.Entity;
import mechamorph.engine.render.Screen;
import mechamorph.engine.render.sprite.Sprite;
import mechamorph.engine.util.math.Vector2i;

public class Tile {

	private static byte[] tileID = new byte[128];
	private static Tile[] tiles = new Tile[128];

	public static Tile voidTile = new Tile(0, 0x000000, true);
	public static Tile grass = new Tile(1, Sprite.tree, true);
	//These two will be true in the near future, when collision is added
	public static Tile stone = new Tile(2, Sprite.cobble, false);
	public static Tile SBrick = new Tile(3, Sprite.SBrick, false);
	public static Tile SBrick2 = new Tile(4, Sprite.SBrick2, false);

	public static int col_grass = 0x00ff00;
	public static int col_stone = 0xff0000;

	private byte id;
	public Sprite sprite;
	public boolean blocks;

	public Vector2i pos;

	//Variables will change later
	//Testing purposes
	public Tile(int id, int col, boolean blocks) {
		//No point in casting each id to byte, so i'll do it in the constructor
		this.id = (byte)id;
		tileID[id] = this.id;
		tiles[id] = this;
		this.sprite = new Sprite(16, col);
		this.blocks = blocks;
	}

	public Tile(int id, Sprite sprite, boolean blocks) {
		//No point in casting each id to byte, so i'll do it in the constructor
		this.id = (byte)id;
		tileID[id] = this.id;
		tiles[id] = this;
		this.sprite = sprite;
	}

	public byte getID() {
		return this.id;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	public void setPos(int x, int y) {
		pos = new Vector2i(x, y);
	}

	public boolean collides(Entity entity) {
		if (!blocks) return false;
		if (entity.getX() == pos.getX() && entity.getY() == pos.getY()) {
			System.out.println("Colliding!!");
			return true;
		} else {
			System.out.println("Not colliding!!");
			return false;
		}
	}

	public static byte getTileID(byte id) {
		return tileID[id];
	}

	public static Tile getTile(byte id) {
		return tiles[id];
	}
}