package de.exxcellent.challenge;

import java.util.ArrayList;
import java.util.HashMap;

public interface DataReader {
	public ArrayList<HashMap<String, String>> parseIntoArrayList(String fileName);
}