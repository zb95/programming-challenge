package de.exxcellent.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.io.IOException;

public class Data {
	private ArrayList<HashMap<String, String>> entries;
	private CSVReader reader;
	
	public Data() {
		this.reader = new CSVReader();
	} 
	
	public Data(CSVReader reader, String fileName) throws IOException{
		this.reader = reader == null ? new CSVReader() : reader;
		if (fileName != null) {
			parseFile(fileName);
		}
	}
	
	public int countEntries() {
		return entries == null ? 0 : entries.size();
	}
	
	public String getEntry(String header, int row) {
		return entries == null ? null : entries.get(row).get(header);
	}
	
	public void parseFile(String fileName) throws IOException{
		this.entries = reader.parseIntoArrayList(fileName);
	}
}