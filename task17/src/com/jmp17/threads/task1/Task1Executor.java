package com.jmp17.threads.task1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task1Executor {
	
	private JobsService service = new JobsService();
	private ResultStorage result = new ResultStorage();
	
	public void executeWithWaitNotify() {
		Object lockA = new Object();
		Object lockB = new Object();
		Object lockC = new Object();
		
		Thread threadA = new Thread(() -> {
			result.setA(service.a());
			synchronized (lockA) {
				lockA.notify();
			}
		});
		
		Thread threadB = new Thread(() -> {
			result.setB(service.b());
			synchronized (lockB) {
				lockB.notify();
			}
		});
		
		Thread threadC = new Thread(() -> {
			try {
				synchronized (lockA) {
					if (!result.hasA()) {
						lockA.wait();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			result.setC(service.c(result.getA()));
			synchronized (lockC) {
				lockC.notify();
			}
		});
		
		Thread threadD = new Thread(() -> {
			try {
				synchronized (lockC) {
					if (!result.hasC()) {
						lockC.wait();
					}
				}
				synchronized (lockB) {
					if (!result.hasB()) {
						lockB.wait();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			result.setD(service.d(result.getC() + result.getB()));
		});
		
		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		
		try {
			threadD.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
	}
	
	public void executeWithLocks() {
		Lock lockA = new ReentrantLock();
		Lock lockB = new ReentrantLock();
		Lock lockC = new ReentrantLock();
		Condition conditionA = lockA.newCondition();
		Condition conditionB = lockB.newCondition();
		Condition conditionC = lockC.newCondition();
		
		Thread threadA = new Thread(() -> {
			result.setA(service.a());
			lockA.lock();
			conditionA.signal();
			lockA.unlock();
		});
		
		Thread threadB = new Thread(() -> {
			result.setB(service.b());
			lockB.lock();
			conditionB.signal();
			lockB.unlock();
			
		});
		
		Thread threadC = new Thread(() -> {
			try {
				lockA.lock();
				if (!result.hasA()) {
					conditionA.await();
				}
				lockA.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			result.setC(service.c(result.getA()));
			lockC.lock();
			conditionC.signal();
			lockC.unlock();
			
		});
		
		Thread threadD = new Thread(() -> {
			try {
				lockC.lock();
				if (!result.hasC()) {
					conditionC.await();
				}
				lockC.unlock();
				lockB.lock();
				if (!result.hasB()) {
					conditionB.await();
				}
				lockB.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			result.setD(service.d(result.getC() + result.getB()));
		});
		
		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		
		try {
			threadD.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
	}
	
	public void executeWithFutures() {
		try {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			Future<Integer> futureA = executor.submit(() -> service.a());
			Future<Integer> futureB = executor.submit(() -> service.b());
			int a = futureA.get();
			Future<Integer> futureC = executor.submit(() -> service.c(a));
			int b = futureB.get();
			int c = futureC.get();
			Future<Integer> futureD = executor.submit(() -> service.d(c + b));
			int d = futureD.get();
			
			System.out.format("a=%d b=%d c=%d d=%d\n", a, b, c, d);
		
			executor.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeWithCompletableFutures() {
		try {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> service.a(), executor);
			CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> service.b(), executor);
			CompletableFuture<Integer> futureC = futureA.thenApplyAsync(a -> service.c(a), executor);
			CompletableFuture<Integer> futureD = futureC.thenCombineAsync(futureB, (c, b) -> service.d(c + b), executor);
			
			int a = futureA.get();
			int b = futureB.get();
			int c = futureC.get();
			int d = futureD.get();
			
			System.out.format("a=%d b=%d c=%d d=%d\n", a, b, c, d);
			
			executor.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
