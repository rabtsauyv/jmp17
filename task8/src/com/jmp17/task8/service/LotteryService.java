package com.jmp17.task8.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jmp17.task8.dao.LotteryDao;
import com.jmp17.task8.dao.LotteryDaoImpl;
import com.jmp17.task8.entity.Ticket;

public class LotteryService {

	private final LotteryDao dao = new LotteryDaoImpl();

	public void init(int amount) {
		try {
			dao.initTable(amount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String buyTicket(String buyer) {
		try {
			Ticket ticket = dao.getAvailableTicket();
			if (ticket == null) {
				return "";
			}
			dao.buyTicket(ticket.getId(), buyer);
			return ticket.getNumber();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void printTicketsByOwner(String owner) {
	}

	public void printTickets() {
		int offset = 0;
		int limit = 1000;
		List<Ticket> list = new ArrayList<>();
		do {
			try {
				list = dao.getTickets(offset, limit);
				printTickets(list);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				offset += limit;
			}

		} while (!list.isEmpty());
	}

	private void printTickets(List<Ticket> list) {
		for (Ticket ticket : list) {
			System.out.println(ticket);
		}
	}

	public Map<String, String> getTicketsMap() {
		List<Ticket> dbTickets = new ArrayList<>();
		try {
			dbTickets = dao.getTickets(0, 10000);
			Map<String, String> map = dbTickets.stream().collect(Collectors.toMap(Ticket::getNumber, Ticket::getBuyerId));
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
