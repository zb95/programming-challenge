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
	
	public int countEntries() {
		return 0;
	}
	
	public String getEntry(String header, int row) {
		return null;
	}
	
	public void parseFile(String fileName) throws IOException{
		this.data = reader.parseIntoArrayList(fileName);
	}
}