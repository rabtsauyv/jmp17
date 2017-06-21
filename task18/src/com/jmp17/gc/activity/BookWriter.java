package com.jmp17.gc.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import com.jmp17.gc.model.Book;

public class BookWriter {
	
	private static final int MAX_TITLE_ID = 1_000_000_000;
	private static final int MAX_TEXT = 100;
	private static final int MAX_LINKS = 100_000;
	
	private static final int MAX_BOOKS = 1_01;
	private static final int MIN_BOOKS = 1_00;
	
	private TextGenerator textGen = new TextGenerator();
	public void setTextGenerator(TextGenerator tg) {
		textGen = tg != null ? tg : new TextGenerator();
	}

	//book - contains strings + links to other books
	
	public List<Book> writeSomeBooks() {
		List<Book> books = new ArrayList<>();
		int booksAmount = getRandom().nextInt(MIN_BOOKS, MAX_BOOKS);
		Stream.iterate(0, i -> i+1)
			.limit(booksAmount)
			.map(i -> createNewBook())
			.forEach(book -> {
				writeText(book);
				books.add(book);
			});
		linkBooks(books);
		
		return books;
	}
	
	private void linkBooks(List<Book> books) {
		for (Book book : books) {
			int linkAmount = getRandom().nextInt(MAX_LINKS);
			for (int i = 0; i < linkAmount; i++) {
				int linkedIndex = getRandom().nextInt(books.size());
				book.addRelated(books.get(linkedIndex));
			}
		}
	}
	
	//--------------------------------
	
	private Book createNewBook() {
		Book book = new Book();
		int titleId = getRandom().nextInt(MAX_TITLE_ID);
		book.setTitle("title #" + titleId);
		
		return book;
	}
	
	private void writeText(Book book) {
		int textAmount = getRandom().nextInt(MAX_TEXT);
		for (int i = 0; i < textAmount; i++) {
			book.addText(textGen.getText());
		}
	}

	//--------------------------------
	
	private ThreadLocalRandom getRandom() {
		return ThreadLocalRandom.current();
	}
}
