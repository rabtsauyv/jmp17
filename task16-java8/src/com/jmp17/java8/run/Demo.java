package com.jmp17.java8.run;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.jmp17.java8.model.Person;

public class Demo {

	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.demoStreamsAndLambdas();
		demo.demoJs();
	}
	
	public void demoJs(){
		try {
			System.out.println("----------------------------- eval js string ------");
			demoJsString();
			System.out.println("----------------------------- eval js file ------");
			demoJsFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void demoJsString() throws ScriptException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval("print('Hello js world')");
		printJsResult(engine.eval("parseInt('12')"));
		printJsResult(engine.eval("parseInt('hi')"));
		printJsResult(engine.eval("parseInt(36)"));
		try {
			printJsResult(engine.eval("parseint('12')"));
		} catch (ScriptException e) {
			System.out.println("ScriptException: " + e.getMessage());
		}
	}
	
	private void printJsResult(Object result) {
		if (result != null) {
			System.out.println(result.getClass());
		}
		System.out.println(result);
	}
	
	public void demoJsFile() throws ScriptException, FileNotFoundException, NoSuchMethodException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(new FileReader("resources/someJsFile.js"));
		
		Invocable inv = (Invocable) engine;
		
		System.out.println();
		System.out.println("Calling js functions from java:");
		printJsResult(inv.invokeFunction("myFunction", "x"));
		printJsResult(inv.invokeFunction("myFunction", 25));
		printJsResult(inv.invokeFunction("myFunction", new ArrayList<>()));
		printJsResult(inv.invokeFunction("myFunction", new Object()));
		printJsResult(inv.invokeFunction("myFunction", new HashMap<>()));

		System.out.println();
		printJsResult(inv.invokeFunction("increment", 6));
		printJsResult(inv.invokeFunction("increment", 51));
		printJsResult(inv.invokeFunction("increment", -2.5));
		printJsResult(inv.invokeFunction("increment", "34"));
		printJsResult(inv.invokeFunction("increment"));
	}

	public void demoStreamsAndLambdas() {
		Person p1 = new Person("John", 22);
		Person p2 = new Person("Vasya", 27);
		Person p3 = new Person("Alex", 32);
		Person p4 = new Person("Petr", 19);
		
		List<Person> personList = Arrays.asList(p1, p2, p3, p4);
		
		Comparator<Person> comparatorByAge = Comparator.comparing(Person::getAge);
		Comparator<Person> comparatorByName = (person1, person2) -> person1.getName().compareTo(person2.getName());
		
		System.out.println("Sorted by age");
		personList.stream().sorted(comparatorByAge).forEach(System.out::println);
		System.out.println();
		System.out.println("Sorted by name");
		personList.stream().sorted(comparatorByName).forEach(System.out::println);
		
		
		System.out.println();
		System.out.println("Filtered");
		personList.stream().parallel()
			.peek(System.out::println)
			.map(Person::getName)
			.filter(s -> s.length() <= 4)
			.forEach(System.out::println);
		
		System.out.println();
		System.out.println("Filtered and sorted");
		personList.stream().parallel()
			.peek(System.out::println)
			.map(Person::getName)
			.filter(s -> s.length() <= 4)
			.sorted()
			.forEach(System.out::println);
		
		System.out.println();
		System.out.println("Stats");
		System.out.println(personList.stream().mapToInt(Person::getAge).summaryStatistics());
		
		BinaryOperator<Person> getOlder = (person1, person2) -> person1.getAge() > person2.getAge() ? person1 : person2;
		
		System.out.println();
		System.out.println("Oldest person");
		Optional<Person> oldest = Stream.of(p1, null, null, p3, p2, null, p4).filter(Objects::nonNull).reduce(getOlder);
		printOptionalPerson(oldest);
		
		System.out.println();
		System.out.println("Oldest of empty");
		Optional<Person> oldest2 = new ArrayList<Person>().stream().reduce(getOlder);
		printOptionalPerson(oldest2);
		
		System.out.println(Stream.of("it", "will", "be", "a", "single", "string").reduce((x, y) -> x + " " + y));
		System.out.println(Stream.of("it", "will", "be", "a", "single", "string").collect(Collectors.joining(" ")));
	}
	
	private void printOptionalPerson(Optional<Person> person) {
		System.out.println("is present: " + person.isPresent());
		System.out.println("if present:");
		person.ifPresent(System.out::println);
		System.out.println("or else:");
		System.out.println(person.orElse(new Person("nobody", 0)));
	}
}
