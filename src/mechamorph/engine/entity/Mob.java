package mechamorph.engine.entity;

import mechamorph.engine.level.Level;
import mechamorph.engine.render.sprite.Sprite;
import mechamorph.engine.util.math.Vector2i;

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