package mechamorph.game.entity;

import java.awt.event.KeyEvent;

import mechamorph.game.render.Screen;
import mechamorph.game.render.gui.Gui;
import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.util.input.Keyboard;
import mechamorph.game.util.math.Vector2i;
import mechamorph.game.world.level.Level;

public class Player extends Mob {

	public boolean levelUp = false;
	public int exp, PLevel, skillPoints;
	public int maxLevel = 100;

	public Player(Vector2i pos, Sprite sprite, Level level) {
		super(pos, sprite, level);
		this.exp = this.skillPoints = 0;
		this.PLevel = 1;
	}

	public void addExp(int amount) {
		exp += amount;
		System.out.println(amount + " Exp Added. Total EXP: " + exp);
	}

	public void checkExp() {
		double req = Math.floor(PLevel + 300 * Math.pow(2, PLevel / 7));
		if (exp >= req) {
			skillPoints += 5;
			levelUp = true;
		}
	}

	public void render(Screen screen) {
		if (levelUp) {
			screen.renderGui(Gui.levelUp, this.getX(), this.getY());
		}
		screen.renderPlayer(getX(), getY(), sprite, 0);
	}

	public void update(Keyboard keyboard) {
		checkExp();
		if (keyboard.isKeyDown(Keyboard.LEFT)) move(-2, 0);
		if (keyboard.isKeyDown(Keyboard.RIGHT)) move(2, 0);
		if (keyboard.isKeyDown(Keyboard.UP)) move(0, -2);
		if (keyboard.isKeyDown(Keyboard.DOWN)) move(0, 2);
		if (keyboard.isKeyDown(KeyEvent.VK_V)) addExp(100);
	}

	public int getX() {
		return pos.getX();
	}

	public int getY() {
		return pos.getY();
	}

}
