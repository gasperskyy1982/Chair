package com.windows;

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ChairMusic implements Runnable {
	public static volatile boolean checkPlay = true;
	public static volatile int check = 0;
	private Clip clip;
	private Thread music;
	private Chair chair;
	private String filePath;

	public ChairMusic(Chair chair, String filePath) {
		this.chair = chair;
		this.filePath = filePath;
		music = new Thread(this);
		music.start();
	}

	public void playSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(filePath));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			check = 1;
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public synchronized void resumeSound() {
		checkPlay = true;
		System.out.print("\nМузыка началась");
		notify();
		playSound();
	}

	public void pauseSound() {
		clip.stop();
	}

	@Override
	public void run() {
		playSound();
		while (Chair.game) {
			synchronized (this) {
				if (!Chair.music) {
					pauseSound();
					check = 0;
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
			}
		}

	}
}