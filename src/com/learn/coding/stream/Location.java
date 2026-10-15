package com.learn.coding.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Location {

	String country;
	String state;

	@Override
	public String toString() {
		return "Location [country=" + country + ", state=" + state + "]";
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public Location(String country, String state) {
		this.country = country;
		this.state = state;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Location> list = new ArrayList<Location>();
		list.add(new Location("India", "Delhi"));
		list.add(new Location("India", "Dehradun"));
		list.add(new Location("India", "Bangalore"));
		list.add(new Location("India", "Nagpur"));
		list.add(new Location("India", "Pune"));
		list.add(new Location("India", "Hyderabad"));
		list.add(new Location("India", "Haridwar"));

		list.add(new Location("Canada", "Toronto"));
		list.add(new Location("Canada", "Vancouver"));
		list.add(new Location("Canada", "Montreal"));
		list.add(new Location("Canada", "Calgary"));
		list.add(new Location("Canada", "Ottawa"));
		list.add(new Location("Canada", "Edmonton"));
		list.add(new Location("Canada", "Quebec City"));

		// US cities
		list.add(new Location("US", "New York"));
		list.add(new Location("US", "Los Angeles"));
		list.add(new Location("US", "Chicago"));
		list.add(new Location("US", "Houston"));
		list.add(new Location("US", "San Francisco"));
		list.add(new Location("US", "Miami"));
		list.add(new Location("US", "Boston"));

		Map<String, List<String>> transformedByCountry = list.stream().collect(Collectors
				.groupingBy(Location::getCountry, Collectors.mapping(Location::getState, Collectors.toList())));

		System.out.println(transformedByCountry);

	}

}
