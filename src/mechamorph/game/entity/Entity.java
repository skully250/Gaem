package mechamorph.game.entity;

import mechamorph.game.render.Screen;
import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.util.input.Keyboard;
import mechamorph.game.util.math.Vector2i;
import mechamorph.game.world.level.Level;

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

		//if (level.getTile(newX, newY).blocks) {
			pos.setX(newX);
			pos.setY(newY);
		//}
	}

	public void render(Screen screen) {
		screen.renderSprite(pos.getX(), pos.getY(), sprite, false);
	}

}