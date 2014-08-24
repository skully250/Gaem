package mechamorph.game.level.tile;

public class Tile {
	
	public static Tile grass = new Tile(0);
	public static Tile stone = new Tile(1);
	
	protected int id;
	
	//Variables will change later
	//Testing purposes
	public Tile(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}
