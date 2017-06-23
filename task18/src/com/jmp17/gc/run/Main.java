package com.jmp17.gc.run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jmp17.gc.activity.BookWriter;
import com.jmp17.gc.activity.TextGenerator;
import com.jmp17.gc.model.Book;

public class Main {

	/*
	 * -XX:+UseSerialGC
	 * -XX:+UseParallelGC
	 * -XX:+UseConcMarkSweepGC
	 * -XX:+UseG1GC
	 */
	
	private static final int CAPACITY = 50;
	
	private BookWriter writer = new BookWriter();
	private List<List<Book>> lists = new ArrayList<>();
	
	public static void main(String[] args) throws InterruptedException {
		Main app = new Main();
		if (Arrays.asList(args).contains("-useNewStrings")) {
			app.writer.setTextGenerator(new TextGenerator(true));
		}
		
		for (int i = 0; i < CAPACITY; i++) {
			app.lists.add(null);
		}
		
		Thread.sleep(10_000); // to have enough time for JVisualVM to connect
		for (int i = 0; i < 8; i++) {
			System.out.println("iter " + i);
			app.oneIteration();
		}
	}
	
	private void oneIteration() {
		for (int i = 0; i < CAPACITY; i++) {
			List<Book> books = writer.writeSomeBooks();
			lists.set(i, books);
		}
	}
}
