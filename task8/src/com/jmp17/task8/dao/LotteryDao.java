package com.jmp17.task8.dao;

import java.sql.SQLException;
import java.util.List;

import com.jmp17.task8.entity.Ticket;

public interface LotteryDao {

	void initTable(int amount) throws SQLException;
	Ticket getAvailableTicket() throws SQLException;
	void buyTicket(int id, String buyer) throws SQLException;
	List<Ticket> getTicketsByOwner(String owner) throws SQLException;
	List<Ticket> getTickets(int offset, int amount) throws SQLException;
}
