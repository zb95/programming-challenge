package de.exxcellent.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CSVReader implements DataReader{
	public ArrayList<HashMap<String, String>> parseIntoArrayList(String fileName){
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			ArrayList<HashMap<String, String>> arr = 
					new ArrayList<HashMap<String, String>>();
			String line;
			if ((line = br.readLine()) != null) {
				String[] headers = line.split(",");
				for (int i = 0; i < headers.length; i++) {
					headers[i] = headers[i].trim();
				}
				
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					if (headers.length != values.length) {
						throw new IOException("CSV entry must have as many columns as header!");
					}
					
					HashMap<String, String> map = new HashMap<String, String>();
					for (int i = 0; i < values.length; i++) {
						map.put(headers[i], values[i].trim());
					}
					arr.add(map);
				}
			}
			return arr;
		} catch (IOException e) {
			System.err.println("Error parsing file: " + e.getMessage());
		}
		return null;
	}
}
