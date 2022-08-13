package com.survivor.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
	
	Handler handler;
	private BufferedImage playerImage;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite);
		playerImage = ss.grabImage(1, 1, 32, 32);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 46);
		y = Game.clamp(y, 0, Game.HEIGHT - 70);
		//handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.08f, handler));
		collision();
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(playerImage, (int)x, (int)y, null);
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.id == ID.BasicEnemy || tempObject.id == ID.FastEnemy || tempObject.id == ID.HardEnemy
					|| tempObject.id == ID.SmartEnemy || tempObject.id == ID.EnemyBoss) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}
}
