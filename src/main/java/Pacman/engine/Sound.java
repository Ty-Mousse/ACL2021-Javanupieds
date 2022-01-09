package main.java.Pacman.engine;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	static Clip audioClip = null;
	
	public static void playMusic() {
		try {
			File file = new File("src/main/java/Pacman/sounds/inGame.wav");
			Clip clip = AudioSystem.getClip();
			audioClip = clip;
			audioClip.open(AudioSystem.getAudioInputStream(file));
			audioClip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static void gameOver() {
		try {
			File file = new File("src/main/java/Pacman/sounds/death.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static void victory() {
		try {
			File file = new File("src/main/java/Pacman/sounds/victory.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	public static void nextStage() {
		try {
			File file = new File("src/main/java/Pacman/sounds/nextStage.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	public static void loseLive() {
		try {
			File file = new File("src/main/java/Pacman/sounds/loseLive.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static void stop() {
		try {
			audioClip.stop();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void music() {

	}
}