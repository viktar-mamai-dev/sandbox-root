package com.mamay.task5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
public class AudioChannel {
	private static int nextId = 1;

	private int channelId;

	public AudioChannel() {
		this.channelId = nextId++;
	}

	public void using() {
		try {
			Thread.sleep(new Random().nextInt(2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
