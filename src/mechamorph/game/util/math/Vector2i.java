package mechamorph.game.util.math;

public class Vector2i {
	
	private int x, y;
	
	//Creating early on as i know im going to need it later
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Generic Getters and setters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}