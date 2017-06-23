package com.jmp17.gc.model;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private String title;
	private List<String> text = new ArrayList<>();
	private List<Book> related = new ArrayList<>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getText() {
		return text;
	}
	public void addText(String string) {
		if (string != null) {
			text.add(string);
		}
	}
	
	public List<Book> getRelated() {
		return related;
	}
	public void addRelated(Book book) {
		if (book != null) {
			related.add(book);
		}
	}
}
