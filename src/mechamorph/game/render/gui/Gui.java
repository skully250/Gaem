package mechamorph.game.render.gui;

import mechamorph.game.render.Screen;
import mechamorph.game.render.sprite.Sprite;

public class Gui {
	
	public static Gui levelUp = new Gui(new Sprite(16, 0xff0000), "Level up");
	
	private Sprite window;
	private String name;
	
	public Gui(Sprite window, String name) {
		this.window = window;
		this.name = name;
	}
	
	public void render(Screen screen, int x, int y) {
		screen.renderGui(this, x, y);
	}
}