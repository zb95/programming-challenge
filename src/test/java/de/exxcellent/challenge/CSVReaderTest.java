package de.exxcellent.challenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class CSVReaderTest {

	private final static String FILE_PREFIX = "/de/exxcellent/challenge/";
	private final static String EMPTY_CSV_FILE = FILE_PREFIX + "testDataEmpty.csv";
	private final static String SIMPLE_CSV_FILE = FILE_PREFIX + "testDataSimple.csv";
	private final static String CSV_FILE_WITH_SPACES = FILE_PREFIX + "testDataWithSpaces.csv";
	private final static String MALFORMED_CSV_FILE = FILE_PREFIX + "testDataMalformed.csv";
	
	@Test
	public void emptyCSVFileShouldBeParsedAsEmptyArrayList() throws IOException{
		URL url = getClass().getResource(EMPTY_CSV_FILE);
		ArrayList<HashMap<String, String>> dataArray = CSVReader.parseIntoArrayList(url.getPath());
		Assert.assertTrue(dataArray.isEmpty());
	}
	
	@Test
	public void simpleCSVFileShouldBeParsed() throws IOException{
		final String HEADER_1 = "Header1";
		final String HEADER_2 = "Header2";
		URL url = getClass().getResource(SIMPLE_CSV_FILE);
		ArrayList<HashMap<String, String>> dataArray = CSVReader.parseIntoArrayList(url.getPath());
		Assert.assertEquals("1", dataArray.get(0).get(HEADER_1));
		Assert.assertEquals("2", dataArray.get(0).get(HEADER_2));
		Assert.assertEquals("3", dataArray.get(1).get(HEADER_1));
		Assert.assertEquals("4", dataArray.get(1).get(HEADER_2));
	}
	
	@Test
	public void CSVFileWithSpacesShouldBeParsed() throws IOException{
		final String HEADER_1 = "Header 1";
		final String HEADER_2 = "Header 2";
		URL url = getClass().getResource(CSV_FILE_WITH_SPACES);
		ArrayList<HashMap<String, String>> dataArray = CSVReader.parseIntoArrayList(url.getPath());
		Assert.assertEquals("1", dataArray.get(0).get(HEADER_1));
		Assert.assertEquals("2", dataArray.get(0).get(HEADER_2));
		Assert.assertEquals("3", dataArray.get(1).get(HEADER_1));
		Assert.assertEquals("4", dataArray.get(1).get(HEADER_2));
	}
	
	@Test
	public void MalformedCSVFileShouldThrowException() {
		try {
			URL url = getClass().getResource(MALFORMED_CSV_FILE);
			ArrayList<HashMap<String, String>> dataArray = CSVReader.parseIntoArrayList(url.getPath());
			Assert.fail("Expected an IOException to be thrown");
		} catch (IOException ioEx) {
			Assert.assertEquals("CSV entry must have as many columns as header!", ioEx.getMessage());
		}
		
	}
}