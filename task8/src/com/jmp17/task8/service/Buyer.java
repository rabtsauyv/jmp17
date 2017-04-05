package com.jmp17.task8.service;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Buyer implements Runnable {
	
	private final LotteryService service;
	private final String name;
	
	private List<String> tickets = new ArrayList<>();
	
	public Buyer(LotteryService service, String name) {
		this.service = service;
		this.name = name;
	}

	@Override
	public void run() {
		String ticketNumber;
		boolean noMoreTickets = false;
		int success = 0, fail = 0, failInARow = 0;
		while(failInARow < 10 && !noMoreTickets) {
			ticketNumber = service.buyTicket(name);
			if (ticketNumber != null) {
				if (ticketNumber.isEmpty()) {
					noMoreTickets = true;
					continue;
				}
				success++;
				failInARow = 0;
				tickets.add(ticketNumber);
			} else {
				fail++;
				failInARow++;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(name).append("]").append(" successful purchases: ").append(success)
			.append(", failed purchases: ").append(fail);
		System.out.println(sb.toString());
	}

	public String getName() {
		return name;
	}

	public List<String> getTickets() {
		return tickets;
	}
	
	public Multimap<String, String> getTicketsAsMap() {
		Multimap<String, String> map = ArrayListMultimap.create();
		for (String t : tickets) {
			map.put(t, name);
		}
		return map;
	}
}
