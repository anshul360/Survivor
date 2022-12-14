package com.survivor.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	Handler handler;
	Random r = new Random();
	int timer = 25;
	int timer2 = 50;
	
	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		velX = 0;
		velY = 5;
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) velY = 0;
		else timer --;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 2) {
			if(velX == 0) velX = 2;
			
			if(velX > 0) velX += 0.005f;
			else if(velX < 0) velX -= 0.005f;
			
			velX = Game.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(10);
			if(spawn == 0) handler.addObject(new EnemyBossBullet((int)x + 48, (int)y + 48, ID.BasicEnemy, handler));
		}
		
		//if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.01f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
	}
	
}
