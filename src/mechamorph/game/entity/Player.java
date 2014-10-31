package mechamorph.game.entity;

import java.awt.event.KeyEvent;

import mechamorph.game.render.Screen;
import mechamorph.game.render.gui.Gui;
import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.util.Sound;
import mechamorph.game.util.input.Keyboard;
import mechamorph.game.util.math.Vector2i;
import mechamorph.game.world.level.Level;

public class Player extends Mob {

	public boolean levelUp = false;
	public int exp, PLevel, skillPoints;
	public int maxLevel = 100;

	private int flip = 0;

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
		if (levelUp)
			screen.renderGui(Gui.levelUp, screen.width / 2, screen.height / 2);
		screen.renderPlayer(getX(), getY(), sprite, flip);
	}

	private int anim = 0;

	public void update(Keyboard keyboard) {
		//checkExp();
		//Dont need to deal with huge numbers here
		if (anim < 7500) anim++;
		else anim = 0;
			if (anim % 30 > 10)
				sprite = Sprite.player2;
			else 
				sprite = Sprite.player;
		if (keyboard.isKeyDown(Keyboard.LEFT)) { move(-2, 0); flip = 1; }

		if (keyboard.isKeyDown(Keyboard.RIGHT)) { move(2, 0); flip = 0; }

		if (keyboard.isKeyDown(Keyboard.UP)) move(0, -2);

		if (keyboard.isKeyDown(Keyboard.DOWN)) move(0, 2);

		if (keyboard.isKeyDown(KeyEvent.VK_V)) addExp(100);
		if (keyboard.isKeyDown(KeyEvent.VK_G)) Sound.bgm.play();
		if (keyboard.isKeyDown(KeyEvent.VK_H)) Sound.test.play();
	}

}
