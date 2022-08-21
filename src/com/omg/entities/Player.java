package com.omg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.omg.main.Game;

public class Player extends Entity{
	
	private BufferedImage spritePlayer;
	private int xTarget,yTarget;
	private boolean isAttacking = false;
	private int kind;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite,int kind) {
		super(x, y, width, height, sprite);
		spritePlayer = sprite;
		this.kind = kind;
	}
	public void tick() {
		Enemy en = null;
		for(int i = 0; i < Game.enemies.size();i++) {
			Enemy e = Game.enemies.get(i);
			if(Entity.distanceBetwen(e.getX(), e.getY(), this.getX(), this.getY()) < 60) {
				en = e;
			}
		}
		
		if(en != null) {
			isAttacking = true;
			xTarget = en.getX();
			yTarget = en.getY();
			en.life -=(1*kind)	;
		}else {
			isAttacking = false;
		}
		
	}
	public void render(Graphics g) {
		g.drawImage(spritePlayer, getX(), getY(), 32, 32, null);
		if(isAttacking) {
			if(kind == 1) {
				g.setColor(Color.orange);
			}else if(kind == 2) {
				g.setColor(Color.red);
			}else if(kind == 3) {
				g.setColor(Color.black);
			}
			g.drawLine(this.getX()+16, this.getY()+16, xTarget+16, yTarget+16);
		}
	}


}
