package com.omg.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.omg.main.Game;

import resources.Spawner;

public class World {
	
	public static Tile[] tiles;
	public static int width, height;
	public static int tile_size = 32;

	public World(String path) {
		try {
			BufferedImage mapa = ImageIO.read(getClass().getResource(path));
			int[] px = new int[mapa.getWidth() * mapa.getHeight()];
			width = mapa.getWidth();
			height = mapa.getHeight();
			tiles = new Tile[mapa.getWidth() * mapa.getHeight()];
			mapa.getRGB(0, 0, mapa.getWidth(), mapa.getHeight(), px ,0, mapa.getWidth());
			for(int i = 0; i <  mapa.getWidth(); i++) {
				
				for(int j = 0; j < mapa.getHeight();j++) {
					
						int pxAtual = px[i + (j*mapa.getWidth())];
						tiles[i + (j *width)] = new Wall(i*tile_size,j*tile_size,Tile.tile_floorFlower);
						
						if(pxAtual == 0xFFFFFFFF) {
							tiles[i + (j *width)] = new Floor(i*tile_size,j*tile_size,Tile.tile_floorBar);
						}else if(pxAtual == 0xFF0026FF) {
							Spawner sp = new Spawner(i*tile_size,j*tile_size,32,32,null);
							Game.entities.add(sp);
							tiles[i + (j *width)] = new Floor(i*tile_size,j*tile_size,Tile.tile_floorBar);
						}
					}
				}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void worldRestart(String path) {
		Game.enemies.clear();
		Game.life = 1000;
	}
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext / tile_size;
		int y1 = ynext/ tile_size;
		
		int x2 = (xnext+tile_size-1) / tile_size;
		int y2 = ynext/ tile_size;
		
		int x3 = xnext / tile_size;
		int y3 = (ynext+tile_size-1)/ tile_size;
		
		int x4 = (xnext+tile_size-1) / tile_size;
		int y4 = (ynext+tile_size-1)/ tile_size;
		
		return !(tiles[x1+(y1*World.width)] instanceof Wall ||
				tiles[x2+(y2*World.width)] instanceof Wall ||
				tiles[x3+(y3*World.width)] instanceof Wall ||
				tiles[x4+(y4*World.width)] instanceof Wall );
	}
	public void render(Graphics g) {
		for(int i = 0; i < tiles.length; i++) {
			tiles[i].render(g);
		}
	}

}
