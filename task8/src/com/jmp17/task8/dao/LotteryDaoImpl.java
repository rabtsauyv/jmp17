package com.jmp17.task8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import com.jmp17.task8.entity.Ticket;

public class LotteryDaoImpl implements LotteryDao {
	
	private final BasicDataSource ds;
	
	public LotteryDaoImpl() {
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/lottery");
		ds.setUsername("lottery_app");
		ds.setPassword("lottery_pass");
//		ds.setDefaultTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	}
	
	@Override
	public void initTable(int amount) throws SQLException {
		String insert = " INSERT INTO lottery_tickets (Ticket_number, Buyer_id) VALUES (?, ?) ";
		String delete = " DELETE FROM lottery_tickets ";
		String autoIncrement = " ALTER TABLE lottery_tickets AUTO_INCREMENT = 1 ";
		String numberTemplate = "TIC-%05d";
		
		try(Connection con = getConnection()) {
			try(Statement st = con.createStatement(); PreparedStatement ps = con.prepareStatement(insert)) {
				st.executeUpdate(delete);
				st.executeUpdate(autoIncrement);
				
				for (int i = 1; i <= amount; i++) {
					ps.setString(1, String.format(numberTemplate, i));
					ps.setNull(2, Types.VARCHAR);
					ps.addBatch();
				}
				ps.executeBatch();
			}
		}
	}

	@Override
	public Ticket getAvailableTicket() throws SQLException {
		String query = " SELECT * FROM lottery_tickets WHERE Buyer_id IS NULL LIMIT 1 ";
		Ticket ticket = null;
		try(Connection con = getConnection();) {
			try(PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {
				try(ResultSet rs = ps.executeQuery();) {
					if(rs.next()) {
						ticket = new Ticket();
						ticket.setId(rs.getInt(1));
						ticket.setNumber(rs.getString(2));
						ticket.setBuyerId(rs.getString(3));
					}
				}
			}
		}
		
		return ticket;
	}

	@Override
	public void buyTicket(int id, String buyer) throws SQLException {
		String query = " UPDATE lottery_tickets SET Buyer_id = ? WHERE Id = ? ";
		try(Connection con = getConnection();) {
			try(PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);) {
				ps.setString(1, buyer);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		}
	}

	@Override
	public List<Ticket> getTicketsByOwner(String owner) throws SQLException {
		String query = " SELECT * FROM lottery_tickets WHERE Buyer_id LIKE '?' ";
		List<Ticket> list = new ArrayList<>();
		try(Connection con = getConnection();) {
			try(PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {
				ps.setString(1, owner);
				try(ResultSet rs = ps.executeQuery();) {
					while(rs.next()) {
						Ticket ticket = new Ticket();
						ticket.setId(rs.getInt(1));
						ticket.setNumber(rs.getString(2));
						ticket.setBuyerId(rs.getString(3));
						list.add(ticket);
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public List<Ticket> getTickets(int offset, int amount) throws SQLException {
		String query = " SELECT * FROM lottery_tickets LIMIT ? OFFSET ? ";
		List<Ticket> list = new ArrayList<>();
		try(Connection con = getConnection();) {
			try(PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {
				ps.setInt(1, amount);
				ps.setInt(2, offset);
				try(ResultSet rs = ps.executeQuery();) {
					while(rs.next()) {
						Ticket ticket = new Ticket();
						ticket.setId(rs.getInt(1));
						ticket.setNumber(rs.getString(2));
						ticket.setBuyerId(rs.getString(3));
						list.add(ticket);
					}
				}
			}
		}
		
		return list;
	}
	
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
