package com.survivor.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velX, velY;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	//x getter setter
	public void setX(float x) {
		this.x = x;
	}
	public float getX() {
		return x;
	}
	//y getter setter
	public void setY(float y) {
		this.y = y;
	}
	public float getY() {
		return y;
	}
	//id getter setter
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	//velX getter setter
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public float getVelX() {
		return velX;
	}
	//velY getter setter
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public float getVelY() {
		return velY;
	}
	
}
