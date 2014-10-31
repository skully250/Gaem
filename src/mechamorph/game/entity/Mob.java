package mechamorph.game.entity;

import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.util.math.Vector2i;
import mechamorph.game.world.level.Level;

public class Mob extends Entity {
	
	public int hp, mp, atk, def;
	
	public Mob(Vector2i pos, Sprite sprite, Level level) {
		super(pos, sprite, level);
	}
	
	public void damage(int damage) {
		hp -= damage;
	}
	
	public void attack(Mob mob) {
		mob.damage(atk);
	}
}