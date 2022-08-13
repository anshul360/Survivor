package com.survivor.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	
	Handler handler;
	Random r = new Random();
	
	public EnemyBossBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		velX = (r.nextInt(10) - 5);
		velY = 5;
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		/*if(y <= 0 || y >= Game.HEIGHT - 52) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 27) {
			velX *= -1;
		}*/
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.09f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
}
