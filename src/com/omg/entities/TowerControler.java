package com.omg.entities;

import java.awt.image.BufferedImage;

import com.omg.main.Game;
import com.omg.world.World;

public class TowerControler extends Entity{

	public boolean isPressed = false;
	public int xTarget,yTarget;
	private BufferedImage tower[];
	
	
	public TowerControler(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		tower = new BufferedImage[3];
		tower[2] = Game.spritesheet.getSprite(48, 0, 16, 16);
		tower[1] = Game.spritesheet.getSprite(64, 0, 16, 16);
		tower[0] = Game.spritesheet.getSprite(80, 0, 16, 16);
	}
	
	public void tick() {
		if(isPressed) {
			int xx = (xTarget/32)*32;
			int yy = (yTarget/32)*32;
			if(!World.isFree(xx, yy) && validPosition(xx,yy) && (Game.coins + 100*(Game.ui.selected-4)) >= 0) {
				Player player = new Player(xx,yy,32,32,tower[Game.ui.selected-1],-1*(Game.ui.selected-4));
				Game.coins += 100*(Game.ui.selected-4);//Another lazy ass programmer example
				Game.entities.add(player);
			}
			isPressed = false;
		}
	}

	private boolean validPosition(int xx, int yy) {
		for(Entity e:Game.entities) {
			if(e.getX() == xx && e.getY() == yy){
				return false;
			}
		}
		return true;
	}

}
