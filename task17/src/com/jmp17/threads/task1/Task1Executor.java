package com.jmp17.threads.task1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task1Executor {
	
	private static final int TIMEOUT = 60_000;
	
	private JobsService service = new JobsService();
	private ResultStorage result = new ResultStorage();
	
	public void executeWithWaitNotify() {
		Object lockA = new Object();
		Object lockB = new Object();
		Object lockC = new Object();
		
		Thread threadA = new Thread(() -> {
			try {
				result.setA(service.a());
			} finally {
				synchronized (lockA) {
					lockA.notify();
				}
			}
		});
		
		Thread threadB = new Thread(() -> {
			try {
				result.setB(service.b());
			} finally {
				synchronized (lockB) {
					lockB.notify();
				}
			}
		});
		
		Thread threadC = new Thread(() -> {
			try {
				synchronized (lockA) {
					if (!result.hasA()) {
						lockA.wait(TIMEOUT);
					}
				}
				result.setC(service.c(result.getA()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				synchronized (lockC) {
					lockC.notify();
				}
			}
		});
		
		Thread threadD = new Thread(() -> {
			try {
				synchronized (lockC) {
					if (!result.hasC()) {
						lockC.wait(TIMEOUT);
					}
				}
				synchronized (lockB) {
					if (!result.hasB()) {
						lockB.wait(TIMEOUT);
					}
				}
				result.setD(service.d(result.getC() + result.getB()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
			try {
				result.setA(service.a());
			} finally {
				try {
					lockA.lock();
					conditionA.signal();
				} finally {
					lockA.unlock();
				}
			}
		});
		
		Thread threadB = new Thread(() -> {
			try {
				result.setB(service.b());
			} finally {
				try {
					lockB.lock();
					conditionB.signal();
				} finally {
					lockB.unlock();
				}
			}
		});
		
		Thread threadC = new Thread(() -> {
			try {
				lockA.lock();
				if (!result.hasA()) {
					conditionA.await(TIMEOUT, TimeUnit.MILLISECONDS);
				}
				result.setC(service.c(result.getA()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lockA.unlock();
			}
			try {
				lockC.lock();
				conditionC.signal();
			} finally {
				lockC.unlock();
			}
		});
		
		Thread threadD = new Thread(() -> {
			try {
				lockC.lock();
				if (!result.hasC()) {
					conditionC.await(TIMEOUT, TimeUnit.MILLISECONDS);
				}
				lockB.lock();
				if (!result.hasB()) {
					conditionB.await(TIMEOUT, TimeUnit.MILLISECONDS);
				}
				result.setD(service.d(result.getC() + result.getB()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lockC.unlock();
				lockB.unlock();
			}
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
		ExecutorService executor = Executors.newFixedThreadPool(2);
		try {
			Future<Integer> futureA = executor.submit(() -> service.a());
			Future<Integer> futureB = executor.submit(() -> service.b());
			int a = futureA.get();
			Future<Integer> futureC = executor.submit(() -> service.c(a));
			int b = futureB.get();
			int c = futureC.get();
			Future<Integer> futureD = executor.submit(() -> service.d(c + b));
			int d = futureD.get();
			
			System.out.format("a=%d b=%d c=%d d=%d\n", a, b, c, d);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
	}
	
	public void executeWithCompletableFutures() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		try {
			
			CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> service.a(), executor);
			CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> service.b(), executor);
			CompletableFuture<Integer> futureC = futureA.thenApplyAsync(a -> service.c(a), executor);
			CompletableFuture<Integer> futureD = futureC.thenCombineAsync(futureB, (c, b) -> service.d(c + b), executor);
			
			int a = futureA.get();
			int b = futureB.get();
			int c = futureC.get();
			int d = futureD.get();
			
			System.out.format("a=%d b=%d c=%d d=%d\n", a, b, c, d);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
	}
}
