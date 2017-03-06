package com.jmp17.task4.prototype;

public class GeoProviderStub implements GeoProvider {

	@Override
	public boolean validateStreet(String city, String street) {
		System.out.println("Performing expensive operation...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Performing expensive operation... - done");
		
		return city != null 
				&& street != null 
				&& city.length() > 1 
				&& street.length() > 1;
	}
}
