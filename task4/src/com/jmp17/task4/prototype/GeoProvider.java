package com.jmp17.task4.prototype;

public interface GeoProvider {

	/**
	 * Expensive operation to call some map service.
	 * 
	 * @param city
	 * @param street
	 * @return true if given city-street combination exists, false otherwise.
	 */
	boolean validateStreet(String city, String street);
}
