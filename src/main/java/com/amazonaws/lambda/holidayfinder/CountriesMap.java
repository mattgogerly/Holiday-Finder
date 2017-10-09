package com.amazonaws.lambda.holidayfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class CountriesMap {
	private Map<String, String> countries;
	private Random rand;
	
	public CountriesMap() {
		countries = new HashMap<>();
		initialiseMap();
	}
	
	private void initialiseMap() {
		String[] countryCodes = Locale.getISOCountries();
		
		for (String code : countryCodes) {
			Locale x = new Locale("", code);
			countries.put(x.getDisplayCountry(), x.getDisplayLanguage());
		}
	}
	
	public Map<String, String> getCountryMap() {
		return countries;
	}
	
	public String getRandCountry() {
		List<String> keys = new ArrayList<String>(countries.keySet());
		
		rand = new Random();
		return keys.get(rand.nextInt(keys.size()));	
	}
	
}
