package com.jmp17.task4.singleton;

public class SingletonDemo {

	public void demo() {
		Thread t1 = new Thread(new DemoThread());
		Thread t2 = new Thread(new DemoThread());
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static class DemoThread implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " is getting Singleton");
			Singleton s = Singleton.getInstance();
			s.action();
		}
		
	}
}
