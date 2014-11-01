package mechamorph.engine.render.gui;

import mechamorph.engine.render.Screen;
import mechamorph.engine.render.sprite.Sprite;

public class Gui {
	
	public static Gui levelUp = new Gui(new Sprite(128, 0xff0000), "Level up");
	
	public String name;
	public Sprite sprite;
	public int[][] pixels;
	
	public Gui(Sprite sprite, String name) {
		this.sprite = sprite;
		this.name = name;
		this.pixels = new int[sprite.width][sprite.height];
	}
	
	public void render(Screen screen, int x, int y) {
		screen.renderGui(this, x, y);
	}
}