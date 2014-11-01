package mechamorph.engine.entity;

import mechamorph.engine.level.Level;
import mechamorph.engine.render.Screen;
import mechamorph.engine.render.sprite.Sprite;
import mechamorph.engine.util.input.Keyboard;
import mechamorph.engine.util.math.Vector2i;

public class Entity {

	protected Vector2i pos;
	protected Sprite sprite;
	protected Level level;

	/**
	 * Creates an entity with a set position and sprite
	 * and sets the parameters accordingly
	 * @param pos The position as a Vector of the Entity
	 * @param sprite The Sprite that is going to be used for the Entity
	 */
	public Entity(Vector2i pos, Sprite sprite, Level level) {
		this.pos = pos;
		this.sprite = sprite;
		this.level = level;
	}

	public void update(Keyboard keyboard) {

	}

	public int getX() {
		return pos.getX();
	}

	public int getY() {
		return pos.getY();
	}

	/**
	 * Moves the Entity by the specified X and Y co-ordinates
	 * provided and updates accordingly 
	 * @param x How much on the X Axis the Entity should move
	 * @param y How much on the Y Axis the Entity should move
	 */
	public void move(int x, int y) {
		int posX = pos.getX();
		int posY = pos.getY();

		int newX = posX + x;
		int newY = posY + y;
		if (newX < 0) newX = 0;
		if (newY < 0) newY = 0;



		//if(collision(newX, newY))
		pos.setPos(newX, newY);
		//else
		//return;
	}

	/**
	 * Checks the tile that the player is on aswell as the tiles around them
	 * to see if a solid connection has been made and returns a boolean
	 * based on whether a collision with tiles around the x and y co-ordinates
	 * have been made
	 * @param xa The x Co-ordinate to scan
	 * @param ya The y Co-ordinate to scan
	 * @return Whether a collision is happening
	 */
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((getX() + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((getY() + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).blocks) solid = true;
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite(pos.getX(), pos.getY(), sprite, false);
	}
}