package mechamorph.game.entity;

import mechamorph.game.render.Screen;
import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.util.input.Keyboard;
import mechamorph.game.util.math.Vector2i;

public class Entity {
	
	protected Vector2i pos;
	protected Sprite sprite;
	
	public Entity(Vector2i pos, Sprite sprite) {
		this.pos = pos;
		this.sprite = sprite;
	}
	
	public void update(Keyboard keyboard) {
		
	}
	
	public void render(Screen screen) {
		screen.renderSprite(pos.getX(), pos.getY(), sprite);
	}

}