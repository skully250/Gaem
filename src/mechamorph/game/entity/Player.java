package mechamorph.game.entity;

import java.awt.event.KeyEvent;

import mechamorph.game.render.Screen;
import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.util.input.Keyboard;
import mechamorph.game.util.math.Vector2i;

public class Player extends Entity {

	public Player(Vector2i pos, Sprite sprite) {
		super(pos, sprite);
	}
	
	public void render(Screen screen) {
		super.render(screen);
	}
	
	public void update(Keyboard keyboard) {
		if (keyboard.keys[KeyEvent.VK_LEFT]) pos.setX(pos.getX() + -2);
		if (keyboard.keys[KeyEvent.VK_RIGHT]) pos.setX(pos.getX() + 2);
		if (keyboard.keys[KeyEvent.VK_UP]) pos.setY(pos.getY() + -2);
		if (keyboard.keys[KeyEvent.VK_DOWN]) pos.setY(pos.getY() + 2);
	}

}
