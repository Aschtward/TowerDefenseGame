package com.omg.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.omg.main.Game;

public class UI {
	
	private BufferedImage towerLvl1;
	private BufferedImage towerLvl2;
	private BufferedImage towerLvl3;
	public int selected = 3;
	
	
	public UI() {
		super();
		this.towerLvl1 = Game.spritesheet.getSprite(48, 0, 16, 16);
		this.towerLvl2 = Game.spritesheet.getSprite(64, 0, 16, 16);
		this.towerLvl3 = Game.spritesheet.getSprite(80, 0, 16, 16);
	}



	public void render(Graphics g) {
		//Render painel
		g.setColor(Color.black);
		g.fillRect(Game.WIDTH-124,10,120,60);
		//Render de seleçao
		g.setColor(Color.blue);
		g.fillRect(Game.WIDTH - (selected*40), 16, 34,34);
		g.setColor(Color.gray);
		//Render de icons
		g.fillRect(Game.WIDTH - 40,16,32,32);
		g.fillRect(Game.WIDTH - 80,16,32,32);
		g.fillRect(Game.WIDTH - 120,16,32,32);
		g.drawImage(towerLvl1,Game.WIDTH-120,16,32,32,null);
		g.drawImage(towerLvl2,Game.WIDTH-80,16,32,32,null);
		g.drawImage(towerLvl3,Game.WIDTH-40,16,32,32,null);
		//Render dos n das torres
		g.setFont(new Font("Arial",20,20));
		g.setColor(Color.white);
		g.drawString("  1     2     3", Game.WIDTH-120, 67);
		g.setFont(new Font("Arial",10,10));
		g.setColor(Color.yellow);
		g.drawString("   100        200      300", Game.WIDTH-120, 80);
		g.setFont(new Font("Arial",15,15));
		g.setColor(Color.white);
		g.drawString("Life:" + Game.life, Game.WIDTH-260, 30);
		g.setColor(Color.white);
		g.drawString("Coins:" + Game.coins, Game.WIDTH-195, 30);
	}
}
