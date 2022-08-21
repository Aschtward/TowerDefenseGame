package com.omg.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.omg.entities.Enemy;
import com.omg.entities.Entity;
import com.omg.entities.Player;
import com.omg.entities.TowerControler;
import com.omg.graph.Spritesheet;
import com.omg.graph.UI;
import com.omg.world.World;

public class Game extends Canvas implements Runnable, KeyListener,MouseListener{
	
	
	private static final long serialVersionUID = 1227254042505466843L;
	
	///Definindo parametros para janela
	public static JFrame frame;
	public static int WIDTH;
	public static int HEIGHT;
	public static int SCALE = 1;
	///Fim parametros para janela
	
	public static BufferedImage image;
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static Spritesheet spritesheet;
	public static Player player;
	public static Random rand;
	public static UI ui;
	public String level_now = "/map.png";
	public static World world;
	private Thread thread;
	private boolean isRunning = true;
	public boolean restartGame = false;
	public static int[] pixels;
	public static int life,coins;
	private TowerControler tC; 

	public Game() {
		
		spritesheet = new Spritesheet("/text.png");
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		world = new World("/map.png");
		WIDTH = (world.width*32)*SCALE;
		HEIGHT = (world.height*32);
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		life = 1000;
		coins = 300;
		tC = new TowerControler(0,0,0,0,null);
		///Cria��o da Janela
		setPreferredSize(new Dimension((world.width*32)*SCALE,(world.height*32)*SCALE));
		inicia_frame();
		///
		addKeyListener(this);
		addMouseListener(this);
		rand = new Random();
		ui = new UI();

		
	}
	
	public void inicia_frame() {///Inicializa janela
		frame = new JFrame("Kill the red marshmallow");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}
		
	public void tick() {
		for(int i = 0; i < entities.size();i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		for(int i = 0; i < enemies.size();i++) {
			Enemy e = enemies.get(i);
			e.tick();
		}
		tC.tick();
		if(life == 0)
			World.worldRestart(level_now);
	}
	
	public void  render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g = bs.getDrawGraphics();
		world.render(g);
		for(int i = 0; i < enemies.size();i++) {
			enemies.get(i).render(g);
		}
		for(int i = 0; i < entities.size();i++) {
			entities.get(i).render(g);
		}
		ui.render(g);
		bs.show();
}
	public void run() {
		
		//Implementa��o game looping
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		int frames = 0;
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+=(now - lastTime)/ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				frames = 0;
				timer += 1000;
			}
		}//Fim implementa��o game looping
		
		stop();
}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//Just a lazy ass programmer, this is to make te Ui render easyer and use less lines of code.
		if(e.getKeyCode() == KeyEvent.VK_1) {
			ui.selected = 3;
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			ui.selected = 2;
		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			ui.selected = 1;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		tC.isPressed = true;
		tC.xTarget = e.getX();
		tC.yTarget = e.getY();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
