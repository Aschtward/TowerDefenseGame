package com.omg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.omg.main.Game;
import com.omg.world.World;

public class Enemy extends Entity {

	private int speed = 1;
	private int going = 0;
	private BufferedImage spriteEnemy[];
	private int curAnimation;
	private int timer = 0;
	public int life = 100;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		spriteEnemy = new BufferedImage[2];
		spriteEnemy[0] = sprite;
		spriteEnemy[1] = Game.spritesheet.getSprite(32, 16, 16, 16);
	}
	
	public void enemyDeath() {
		Game.entities.remove(this);
		Game.enemies.remove(this);
	}
	
	public void tick() {
		if(life < 0) {
			enemyDeath();
			Game.coins +=20;
		}
		timer++;
		
		if(timer == 10) {
			if(curAnimation == 1) {
				curAnimation = 0;
			}else {
				curAnimation = 1;
			}
			timer = 0;
		}
		
		if(World.isFree(this.getX()+speed, this.getY())) {
			this.setX(this.getX()+speed);
		}else if(World.isFree(this.getX(), this.getY()+speed) && going == 1) {
			this.setY(this.getY()+speed);
		}else if(World.isFree(this.getX(), this.getY()-speed)) {
			this.setY(this.getY()-speed);
			going = 0;
			
		}else if(going == 0) {
			going = 1;
		}
		if(getX()+speed > Game.WIDTH){
			enemyDeath();
			Game.life-=10;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.getX(), this.getY()-4, life/3, 3);
		g.drawImage(spriteEnemy[curAnimation], this.getX(), this.getY(),World.tile_size,World.tile_size,null);
	}

}
