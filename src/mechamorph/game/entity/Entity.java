package mechamorph.game.entity;

import mechamorph.game.render.Screen;
import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.util.input.Keyboard;
import mechamorph.game.util.math.Vector2i;

public class Entity {
	
	protected Vector2i pos;
	protected Sprite sprite;
	
	/**
	 * Creates an entity with a set position and sprite
	 * and sets the parameters accordingly
	 * @param pos The position as a Vector of the Entity
	 * @param sprite The Sprite that is going to be used for the Entity
	 */
	public Entity(Vector2i pos, Sprite sprite) {
		this.pos = pos;
		this.sprite = sprite;
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
		
		pos.setX(newX);
		pos.setY(newY);
	}
	
	public void render(Screen screen) {
		screen.renderSprite(pos.getX(), pos.getY(), sprite, false);
	}

}