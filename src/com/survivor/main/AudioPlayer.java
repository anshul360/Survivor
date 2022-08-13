package com.survivor.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	private static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	private static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		AudioPlayer ap = new AudioPlayer();
		try {
			soundMap.put("menu_sound", new Sound(ap.getClass().getResource("/click.ogg")));
			musicMap.put("music", new Music(ap.getClass().getResource("/background.ogg")));
			musicMap.put("music1", new Music(ap.getClass().getResource("/back1.ogg")));
			musicMap.put("music2", new Music(ap.getClass().getResource("/back2.ogg")));
			
		} catch(SlickException e) {
			//e.printStackTrace();
			System.out.println("exception: " + e.getMessage());
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
