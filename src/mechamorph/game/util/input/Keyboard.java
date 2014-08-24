package mechamorph.game.util.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	public static int UP = KeyEvent.VK_UP;
	public static int DOWN = KeyEvent.VK_DOWN;
	public static int LEFT = KeyEvent.VK_LEFT;
	public static int RIGHT = KeyEvent.VK_RIGHT;
	
	public boolean[] keys = new boolean[65535];
	
	public boolean isKeyDown(int keycode) {
		return keys[keycode];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
