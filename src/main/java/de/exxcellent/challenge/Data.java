package de.exxcellent.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.io.IOException;

public class Data {
	private ArrayList<HashMap<String, String>> data;
	private CSVReader reader;
	
	public Data() {
		this.reader = new CSVReader();
	} 
	
	public void parseFile(String fileName) throws IOException{
		this.data = reader.parseIntoArrayList(fileName);
	}
	
	public String computeValue(Function<ArrayList<HashMap<String, String>>, String> f) {
		return f.apply(this.data);
	}
}