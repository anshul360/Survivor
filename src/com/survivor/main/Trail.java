package com.survivor.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Trail extends GameObject {
	
	private float alpha = 1, life;
	private Handler handler;
	private Color color;
	private int width, height;
	private BufferedImage img;
	
	/*public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.color = color;
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.life = life;
	}*/
	
	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler, BufferedImage img) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.color = color;
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.life = life;
		this.img = img;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(alpha > life) {
			alpha -= life - 0.0001f;
		} else {
			handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		if(img != null) {
			g.drawImage(img, (int)x, (int)y, null);
		} else {
			g.setColor(color);
			g.fillRect((int)x, (int)y, width, height);
		}
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
