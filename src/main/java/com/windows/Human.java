package com.windows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Human implements Runnable {
	public Thread human;
	private String name;
	private Semaphore s;
	private Chair chair;

	public Human(String name, Chair chair, Semaphore s) {
		this.name = name;
		this.s = s;
		this.chair = chair;
		human = new Thread(this);
		human.start();
	}

	public void setChair() {
		chair.setOwnerName(name);
		chair.setChair();
	}

	@Override
	public void run() {
		System.out.println(name + " в игре");
		try {
			s.acquire();
		} catch (InterruptedException e) {
		}
		setChair();
		System.out.println("\n" + chair.getOwnerName() + " сел на стул");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
		}
		Chair.busy = false;
		ChairMusic.check = 0;
		System.out.println(name + " закончил игру");
		s.release();
		Chair.music = true;
	}

}
