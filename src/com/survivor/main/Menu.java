package com.survivor.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		boolean clicked = true;
		//play
		if(mouseOver(mx, my, 210, 150, 200, 64) && game.gameState == Game.STATE.Menu) {
			game.gameState = Game.STATE.Select;
		}
		//help
		else if(mouseOver(mx, my, 210, 250, 200, 64) && game.gameState == Game.STATE.Menu) {
			game.gameState = Game.STATE.Help;
		}
		//back
		else if(mouseOver(mx, my, 210, 350, 200, 64) && game.gameState == Game.STATE.Help) {
			game.gameState = Game.STATE.Menu;
		}
		//quit
		else if(mouseOver(mx, my, 210, 350, 200, 64) && game.gameState == Game.STATE.Menu) {
			System.exit(0);
		}
		//try again
		else if(mouseOver(mx, my, 210, 250, 200, 64) && game.gameState == Game.STATE.End) {
			game.gameState = Game.STATE.Game;
			handler.clearAll();
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			GameObject obj = game.diff == 0?
					  (new BasicEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.BasicEnemy, handler)):
					  (new HardEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.HardEnemy, handler));
			handler.addObject(obj);
			hud.setLevel(1);
			hud.setScore(0);
		}//Back to Menu
		else if(mouseOver(mx, my, 210, 350, 200, 64) && game.gameState == Game.STATE.End) {
			game.gameState = Game.STATE.Menu;
		} 
		//select - normal
		else if(mouseOver(mx, my, 210, 150, 200, 64) && game.gameState == Game.STATE.Select) {
			game.gameState = Game.STATE.Game;
			handler.clearAll();
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.BasicEnemy, handler));
			hud.setLevel(1);
			hud.setScore(0);
			game.diff = 0;
		}
		//select - Hard
		else if(mouseOver(mx, my, 210, 250, 200, 64) && game.gameState == Game.STATE.Select) {
			game.gameState = Game.STATE.Game;
			handler.clearAll();
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.HardEnemy, handler));
			hud.setLevel(1);
			hud.setScore(0);
			game.diff = 1;
		}
		//select - back
		else if(mouseOver(mx, my, 210, 350, 200, 64) && game.gameState == Game.STATE.Select) {
			game.gameState = Game.STATE.Menu;
		} else {
			clicked = false;
		}
		if(clicked) {
			AudioPlayer.getSound("menu_sound").play();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
		}
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font font = new Font("ariel", 1, 50);
		Font font2 = new Font("ariel", 1, 30);
		Font font3 = new Font("ariel", 1, 20);
		if(game.gameState == Game.STATE.Menu) {
			g.setFont(font);
			FontMetrics metrics = g.getFontMetrics(font);
			int titleX = (Game.WIDTH - metrics.stringWidth("SURVIVOR"))/2;
			g.setColor(Color.getHSBColor(038f, 0.67f, 1.0f));
			g.drawString("SURVIVOR", titleX-15, 70);
			
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Play", 280, 185);
			g.setColor(Color.white);
			g.drawRect(210, 150, 200, 064);
			
			g.setColor(Color.orange);
			g.drawString("Help", 280, 285);
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 064);
			
			g.setColor(Color.red);
			g.drawString("Quit", 280, 385);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 064);
		} else if(game.gameState == Game.STATE.Help) {
			g.setFont(font);
			g.setColor(Color.getHSBColor(038f, 0.67f, 1.0f));
			g.drawString("HELP", 245, 70);
			
			g.setFont(font3);
			g.setColor(Color.white);
			g.drawString("Use WASD keys to move player and dodge enemies!!!", 65, 170);
			
			g.setFont(font2);
			g.setColor(Color.gray);
			g.drawString("Back", 280, 385);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 064);
		} else if(game.gameState == Game.STATE.End) {
			g.setFont(font);
			g.setColor(Color.getHSBColor(038f, 0.67f, 1.0f));
			g.drawString("GAME OVER", 155, 70);
			
			g.setFont(font3);
			FontMetrics metrics3 = g.getFontMetrics(font3);
			String score = "Final Score: " + hud.getScore();
			//System.out.println("length: " + score.length());
			int scorex = (Game.WIDTH - metrics3.stringWidth(score))/2;
			g.drawString("You are dead", 240, 170);
			g.drawString(score, scorex - 15, 200);
			
			g.setFont(font2);
			g.setColor(Color.yellow);
			g.drawString("Try Again!", 240, 285);
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 064);
			
			g.setColor(Color.green);
			g.drawString("Menu", 275, 385);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 064);
		} else if(game.gameState == Game.STATE.Select) {
			g.setFont(font);
			g.setColor(Color.getHSBColor(038f, 0.67f, 1.0f));
			g.drawString("SELECT DIFFICULTY", 70, 70);
			
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Normal", 263, 185);
			g.setColor(Color.white);
			g.drawRect(210, 150, 200, 064);
			
			g.setColor(Color.red);
			g.drawString("Hard", 280, 285);
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 064);
			
			g.setColor(Color.gray);
			g.drawString("Back", 280, 385);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 064);
		}
	}
}
