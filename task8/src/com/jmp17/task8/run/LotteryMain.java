package com.jmp17.task8.run;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Multimap;
import com.jmp17.task8.service.Buyer;
import com.jmp17.task8.service.LotteryService;

public class LotteryMain {

	public static void main(String[] args) {
		LotteryService service = new LotteryService();
		service.init(100);
		
		Buyer b1 = new Buyer(service, "Bob");
		Buyer b2 = new Buyer(service, "John");
		
		Thread t1 = new Thread(b1);
		Thread t2 = new Thread(b2);
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Map<String, String> dbTickets = service.getTicketsMap();
		Multimap<String, String> localTickets = b1.getTicketsAsMap();
		localTickets.putAll(b2.getTicketsAsMap());
		Set<Entry<String, String>> dbEntrySet = dbTickets.entrySet();
		int errorCount = 0;
		for (Entry<String, String> t : localTickets.entries()) {
			if (!dbEntrySet.contains(t)) {
				errorCount++;
				System.out.println("Error: " + t.getKey() + " -- local:" + t.getValue() + ",    db:" + dbTickets.get(t.getKey()));
			}
		}
		System.out.println("Total errors: " + errorCount);
	}
}
