package com.survivor.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FastEnemy extends GameObject{

	Handler handler;
	private BufferedImage enemyImage;
	
	public FastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		velX = 2;
		velY = 9;
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite);
		enemyImage = ss.grabImage(4, 1, 16, 16);
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
		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.02f, handler, enemyImage));
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.cyan);
		//g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(enemyImage, (int)x, (int)y, null);
	}
}
