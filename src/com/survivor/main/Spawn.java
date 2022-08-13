package com.survivor.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Game game;
	Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 250) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			if(hud.getLevel() == 2) {
				GameObject obj = game.diff == 0?
						  (new BasicEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.BasicEnemy, handler)):
						  (new HardEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.HardEnemy, handler));
				handler.addObject(obj);
			} else if(hud.getLevel() == 3) {
				GameObject obj = game.diff == 0?
						  (new BasicEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.BasicEnemy, handler)):
						  (new HardEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.HardEnemy, handler));
				handler.addObject(obj);
			} else if(hud.getLevel() == 4) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.FastEnemy, handler));
			} else if(hud.getLevel() == 5) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.SmartEnemy, handler));
			} else if(hud.getLevel() == 6) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.FastEnemy, handler));
			} else if(hud.getLevel() == 7) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 27), r.nextInt(Game.HEIGHT - 52), ID.FastEnemy, handler));
			} else if(hud.getLevel() == 10) {
				handler.clearEnemies();
				handler.addObject(new EnemyBoss(Game.WIDTH/2-46, -100, ID.EnemyBoss, handler));
			}
		}
	}
}
