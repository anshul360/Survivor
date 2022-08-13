package com.survivor.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class HardEnemy extends GameObject{
	
	Handler handler;
	Random r = new Random();
	private BufferedImage enemyImage;
	
	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		velX = r.nextInt(5) + 5;
		velY = r.nextInt(5) + 5;
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite);
		enemyImage = ss.grabImage(2, 1, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 52) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 27) {
			velX *= -1;
		}
		//handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, 16, 16, 0.05f, handler));
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.magenta);
//		g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(enemyImage, (int)x, (int)y, null);
	}
	
}
