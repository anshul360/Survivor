package com.survivor.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -37249182091570096L;
	public static final int WIDTH = 640, HEIGHT = WIDTH/4*3;
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	public int diff = 0;//0 - normal, 1 - hard
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	private Random r;
	
	public enum STATE {
		Menu,
		Select,
		Game,
		Shop,
		Help,
		End,
	}
	
	public STATE gameState = STATE.Menu;
	
	public static BufferedImage sprite;
	
	public Game() {
		r = new Random();
		handler = new Handler();
		if(gameState != STATE.Game) {
			addParticles();
		}
		hud = new HUD();
		shop = new Shop(handler, hud);
		spawner = new Spawn(handler, hud, this);
		menu = new Menu(this, handler, hud);
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music1").loop();
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		new Window(WIDTH, HEIGHT, "Survivor!", this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite = loader.loadImage("/sprite.png");
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 27), r.nextInt(HEIGHT - 52), ID.BasicEnemy, handler));
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		new Game();
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			//frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				//frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(gameState == STATE.Game) {
			if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					handler.clearAll();
					gameState = STATE.End;
					addParticles();
				}
			}
			
		} else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help || gameState == STATE.Select) {
			menu.tick();
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		} else if(gameState == STATE.Shop) {
			shop.render(g);
		} else if(gameState == STATE.Menu || gameState == STATE.Help  || gameState == STATE.End || gameState == STATE.Select) {
			menu.render(g);
			handler.render(g);
		}
		
		if(paused) {
			Font font = new Font("ariel", 1, 50);
			FontMetrics metrics = g.getFontMetrics(font);
			g.setFont(font);
			g.setColor(Color.yellow);
			g.drawString(
				"PAUSED", 
				(WIDTH - metrics.stringWidth("PAUSED"))/2, 
				HEIGHT/2
			);
		
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) {
			return max;
		} else if(var <= min) {
			return min;
		} else {
			return var;
		}
	}
	
	private void addParticles() {
		for(int i = 0; i < 20; i++) {
			handler.addObject(new MenuParticle(r.nextInt(WIDTH - 27), r.nextInt(HEIGHT - 52), ID.MenuParticle, handler));
		}
	}
	
}
