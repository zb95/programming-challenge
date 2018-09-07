package de.exxcellent.challenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.net.URL;
import java.lang.Integer;
import java.util.function.Function;

public class DataTest {

	private final static String FILE_PREFIX = "/de/exxcellent/challenge/";
	private final static String SIMPLE_CSV_FILE = FILE_PREFIX + "testDataSimple.csv";
	final String HEADER_1 = "Header1";
	final String HEADER_2 = "Header2";
	private Data dat;
	
	@Before
	public void initialize(){
		URL url = getClass().getResource(SIMPLE_CSV_FILE);
		
		dat = new Data();
		dat.parseFile(url.getPath());
	}
	
	@Test 
	public void getEntryShouldReturnNullIfNoFileHasBeenParsed() {
		Data dat = new Data();
		Assert.assertNull(dat.getEntry(HEADER_1, 0));
	}
	
	@Test 
	public void getEntryShouldReturnNullForWrongHeader() {
		final String WRONG_HEADER = "Wrong Header";
		Assert.assertNull(dat.getEntry(WRONG_HEADER, 0));
	}
	
	@Test 
	public void getEntryShouldReturnCorrectValue() {
		Assert.assertEquals("1", dat.getEntry(HEADER_1, 0));
	}
	
	@Test 
	public void countEntriesShouldReturnZeroIfNoFileHasBeenParsed() {
		Data dat = new Data();
		Assert.assertEquals(0, dat.countEntries());
	}
	
	@Test 
	public void countEntriesShouldReturnCorrectValue() {
		Assert.assertEquals(2, dat.countEntries());
	}
	
	@Test
	public void testMaxFunctionForHeader(){
		Function<Data, Function<String, String>> maxOfData = 
			d -> header -> {
				int maxFound = Integer.MIN_VALUE;
				for (int i = 0; i < d.countEntries(); i++) {
					int val = Integer.parseInt(d.getEntry(header, i));
					maxFound = val > maxFound ? val : maxFound;
				}
				return Integer.toString(maxFound);
			};
			
		Function<String, String> maxUnderHeader = maxOfData.apply(dat);
		
		Assert.assertEquals("3", maxUnderHeader.apply(HEADER_1));
		Assert.assertEquals("4", maxUnderHeader.apply(HEADER_2));
	}
}