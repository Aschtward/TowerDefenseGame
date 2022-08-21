package com.omg.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.omg.main.Game;
import com.omg.world.World;

public class Entity {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage sprite;
	public static BufferedImage enemy = Game.spritesheet.getSprite(32,0,16,16);
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	protected static double distanceBetwen(int x1, int y1, int x2, int y2) {
		return Math.sqrt(((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2)));
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(),World.tile_size,World.tile_size,null);
	}
	
	public boolean collided() {
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),World.tile_size,World.tile_size);
		Rectangle ent = new Rectangle(this.getX(),this.getY()+8,10,10);
		return player.intersects(ent);
	}
	
	public static boolean notPlayerCollided(Entity a, Entity b) {
		Rectangle ent_a = new Rectangle(a.getX(),a.getY(),a.getWidth(),a.getHeight());
		Rectangle ent_b = new Rectangle(b.getX(),b.getY(),b.getWidth(),b.getHeight());
		return ent_a.intersects(ent_b);
	}
	public void tick(){
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
		
}
