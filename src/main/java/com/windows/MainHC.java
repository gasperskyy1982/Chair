package com.windows;

import java.util.concurrent.Semaphore;

public class MainHC {

	public static void main(String[] args) {
		Chair chair = new Chair();
		Semaphore s = new Semaphore(1);
		Human humans[] = new Human[10];

		for (int i = 0; i < humans.length; i++) {
			humans[i] = new Human("Human" + i, chair, s);
		}

		ChairMusic player = new ChairMusic(chair, "/sounds/Between_You.wav");
		while (Chair.game) {
			if (Chair.music == true && ChairMusic.check == 0) {
				player.resumeSound();
			}
		}

		try {
			for (Human human : humans) {
				human.human.join();
			}

			Chair.game = false;
		} catch (InterruptedException e) {
		}

		System.out.println("\nИгра закончилась");

	}

}
