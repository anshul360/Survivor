package com.survivor.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private Game game;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		keyDown[0] = keyDown[1] = keyDown[2] = keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE)  System.exit(0);
		
		if(key == KeyEvent.VK_P && game.gameState == Game.STATE.Game) {
			Game.paused = Game.paused?false:true;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			game.gameState = 
					game.gameState == Game.STATE.Game?Game.STATE.Shop
							:(game.gameState == Game.STATE.Shop?Game.STATE.Game:game.gameState);
		}
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-handler.speed); keyDown[0] = true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(handler.speed); keyDown[1] =  true; }
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-handler.speed); keyDown[2] =  true; }
				if(key == KeyEvent.VK_D) { tempObject.setVelX(handler.speed); keyDown[3] =  true; }
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) keyDown[0] = false;
				if(key == KeyEvent.VK_S) keyDown[1] = false;
				if(key == KeyEvent.VK_A) keyDown[2] = false;
				if(key == KeyEvent.VK_D) keyDown[3] = false;
				
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
	}
	
}