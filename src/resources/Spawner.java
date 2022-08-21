package resources;

import java.awt.image.BufferedImage;

import com.omg.entities.Enemy;
import com.omg.entities.Entity;
import com.omg.main.Game;
import com.omg.world.World;

public class Spawner extends Entity{
	
	private int seconds = 0;

	public Spawner(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		seconds++;
		if(seconds > 40) {
			Enemy en = new Enemy(this.getX(),this.getY(),World.tile_size,World.tile_size,Entity.enemy);
			Game.enemies.add(en);
			seconds = 0;

		}

	}


}
