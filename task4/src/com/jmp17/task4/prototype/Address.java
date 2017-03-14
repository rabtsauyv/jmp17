package com.jmp17.task4.prototype;

public class Address implements Cloneable {
	
	private static GeoProvider geoProvider = new GeoProviderStub();

	private String city;
	private String street;
	private int house;
	private boolean valid;
	
	public static void setGeoProvider(GeoProvider geoProvider) {
		Address.geoProvider = geoProvider;
	}
	
	public Address() {
		System.out.println("creating new Address object");
	}
	
	public Address(String city, String street) {
		this();
		this.city = city;
		this.street = street;
		validate();
	}
	
	public Address copy() throws CloneNotSupportedException {
		System.out.println("copying existing Address object");
		return (Address) clone();
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
		validate();
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
		validate();
	}
	public int getHouse() {
		return house;
	}
	public void setHouse(int house) {
		this.house = house;
	}
	public boolean isValid() {
		return valid;
	}
	
	private void validate() {
		valid = geoProvider != null ? geoProvider.validateStreet(city, street) : false;
	}
	
	@Override
	public String toString() {
		return city + ", " + street.charAt(0) + house;
	}
}
