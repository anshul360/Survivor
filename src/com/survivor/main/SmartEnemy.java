package com.survivor.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject{
	
	Handler handler;
	GameObject player;
	private BufferedImage enemyImage;
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		SpriteSheet ss = new SpriteSheet(Game.sprite);
		enemyImage = ss.grabImage(1, 2, 16, 16);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));
		
		velX = (float)((-1.0/distance)*diffX);
		velY = (float)((-1.0/distance)*diffY);
		
		if(y <= 0 || y >= Game.HEIGHT - 52) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 27) {
			velX *= -1;
		}
		//handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.green);
//		g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(enemyImage, (int)x, (int)y, null);
	}
	
}
