package de.exxcellent.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public interface DataReader {
	public ArrayList<HashMap<String, String>> parseIntoArrayList(String fileName) throws IOException;
}