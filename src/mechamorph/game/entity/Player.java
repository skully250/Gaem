package mechamorph.game.entity;

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
		if (keyboard.isKeyDown(Keyboard.LEFT)) move("X", -2);
		if (keyboard.isKeyDown(Keyboard.RIGHT)) move("X", 2);
		if (keyboard.isKeyDown(Keyboard.UP)) move("Y", -2);
		if (keyboard.isKeyDown(Keyboard.DOWN)) move("Y", 2);
	}

}
