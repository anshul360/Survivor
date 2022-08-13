package com.survivor.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	int enemy = 0, trail = 0, player = 0;
	public int speed = 5;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
		countType(object, true);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
		countType(object, false);
	}
	
	public void clearAll() {
		object = new LinkedList<GameObject>();
	}
	
	public void clearEnemies() {
		System.out.println("Object size before: " + this.object.size());
		System.out.println("Player size before: " + player);
		System.out.println("Trail size before: " + trail);
		System.out.println("Enemy size before: " + enemy);
		int remCount = 0;
		int objSize = object.size();
		for(int i = 0; i < objSize; i++) {
			GameObject tempObject = object.get(i-remCount);
			if(tempObject.id != ID.Player) {
				removeObject(tempObject);
				remCount++;
			}
		}
		System.out.println("Object size after: " + this.object.size());
		System.out.println("Player size after: " + player);
		System.out.println("Trail size after: " + trail);
		System.out.println("Enemy size after: " + enemy);
	}
	
	private void countType(GameObject object, boolean bump) {
		if(object.id == ID.Player) { if(bump) player++; else player--; }
		else if(object.id == ID.Trail) { if(bump) trail++; else trail--; }
		else { if(bump) enemy++; else enemy--; }
	}
}
