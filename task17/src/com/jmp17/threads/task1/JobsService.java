package com.jmp17.threads.task1;

public class JobsService {

	/**
	 * @return 10
	 */
	public int a() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return 10;
	}
	
	/**
	 * @return 5
	 */
	public int b() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return 5;
	}
	
	/**
	 * @return c^2
	 */
	public int c(int c) {
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return c*c;
	}
	
	/**
	 * @return d+2
	 */
	public int d(int d) {
		
		try {
			Thread.sleep(2300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return d+2;
	}
}
