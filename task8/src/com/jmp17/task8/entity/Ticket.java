package com.jmp17.task8.entity;

import java.util.Objects;

public class Ticket {

	private int id;
	private String number;
	private String buyerId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append("_").append(number);
		if (buyerId != null) {
			sb.append(" (").append(buyerId).append(")");
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Ticket)) {
			return false;
		}
		Ticket t = (Ticket) o;
		return id == t.id && 
				Objects.equals(number, t.number) && 
				Objects.equals(buyerId, t.buyerId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, number, buyerId);
	}
}
