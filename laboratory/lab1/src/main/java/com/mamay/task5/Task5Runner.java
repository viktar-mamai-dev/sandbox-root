package com.mamay.task5;

import java.util.LinkedList;

public class Task5Runner {
	public static void main(String[] args) {
		LinkedList<AudioChannel> list = new LinkedList<AudioChannel>() {
			{
				this.add(new AudioChannel());
				this.add(new AudioChannel());
				this.add(new AudioChannel());
				this.add(new AudioChannel());
				this.add(new AudioChannel());
			}
		};
		ChannelPool pool = new ChannelPool(list);
		for (int i = 0; i < 20; i++) {
			new Client(pool).start();
		}
	}
}
