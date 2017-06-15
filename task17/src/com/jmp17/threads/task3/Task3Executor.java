package com.jmp17.threads.task3;

import java.util.Iterator;
import java.util.Map;

public class Task3Executor {

	private MapProvider mp = new MapProvider();
	
	private Runnable reader = () -> {
		Map<Integer, Integer> map = mp.getMap();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Iterator<Integer> it = map.keySet().iterator();
		int sum = 0;
		sum += map.get(it.next());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sum += map.get(it.next());
		sum += map.get(it.next());
		sum += map.get(it.next());
		sum += map.get(it.next());
		System.out.println(sum);
	};
	
	private Runnable writer = () -> {
		Map<Integer, Integer> map = mp.getMap();
		for (int i = 1; i < 7; i++) {
			map.put(i, i);
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	public void runHashMap() {
		mp.setHashMap();
		Thread t1 = new Thread(writer);
		Thread t2 = new Thread(reader);
		
		try {
			t1.start();
			Thread.sleep(100);
			t2.start();
			t1.join();
			t2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runConcurrentMap() {
		mp.setConcurrentMap();
		Thread t1 = new Thread(writer);
		Thread t2 = new Thread(reader);
		
		try {
			t1.start();
			Thread.sleep(100);
			t2.start();
			t1.join();
			t2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runSynchronizedMap() {
		
		Runnable syncReader = () -> {
			Map<Integer, Integer> map = mp.getMap();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (map) {

				Iterator<Integer> it = map.keySet().iterator();
				int sum = 0;
				sum += map.get(it.next());
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sum += map.get(it.next());
				sum += map.get(it.next());
				sum += map.get(it.next());
				sum += map.get(it.next());
				System.out.println(sum);
			}
		};
		
		//*****************************
		
		mp.setSynchronizedMap();
		Thread t1 = new Thread(writer);
		Thread t2 = new Thread(syncReader);
		
		try {
			t1.start();
			Thread.sleep(100);
			t2.start();
			t1.join();
			t2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
