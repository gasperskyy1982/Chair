package com.windows;

import java.util.concurrent.TimeUnit;

public class Chair {
	public static volatile boolean busy = false;
	public static volatile boolean music = true;
	public static volatile int timeMusic;
	public static boolean game = true;
	private String ownerName;
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public synchronized void setChair() {

		try {
		timeMusic = (int)(Math.random()*3+1);
		TimeUnit.SECONDS.sleep(timeMusic);
		System.out.println("\nМузыка длилась " + timeMusic + " сек");
		music = false;
		} catch (InterruptedException e1) {
		}
		while (busy) {
			System.out.println("Стул занят\n");
		}
		System.out.println("Стул свободен");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {}
		busy = true;

	}

}
