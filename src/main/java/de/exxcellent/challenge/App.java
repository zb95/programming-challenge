package de.exxcellent.challenge;

import java.util.function.Function;
import java.net.URL;
import java.io.IOException;
/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {
	private final static String FILE_PREFIX = "/de/exxcellent/challenge/";
	private final static String WEATHER_FILE = FILE_PREFIX + "weather.csv";
	private final static String FOOTBALL_FILE = FILE_PREFIX + "football.csv";
	
	@FunctionalInterface
	interface FourParFunction<One, Two, Three, Four, R> {
		public R apply(One one, Two two, Three three, Four four);
	}

    public static void main(String... args) throws IOException{
		
		FourParFunction<Data, String, String, String, String> minSpread = 
			(d, returnHeader, diffHeader1, diffHeader2) -> {
				int minDiffFound = Integer.MAX_VALUE;
				String relatedField = "";
				for (int i = 0; i < d.countEntries(); i++) {
					int val1 = Integer.parseInt(d.getEntry(diffHeader1, i));
					int val2 = Integer.parseInt(d.getEntry(diffHeader2, i));
					int diff = Math.abs(val1 - val2);
					if (diff < minDiffFound) {
						minDiffFound = diff;
						relatedField = d.getEntry(returnHeader, i);
					}
				}
				return relatedField;
			};
			
		String weatherPath = App.class.getResource(WEATHER_FILE).getPath();
		String footballPath = App.class.getResource(FOOTBALL_FILE).getPath();
		
		Data weatherData = new Data(null, weatherPath);
		Data footballData = new Data(null, footballPath);
		
		String dayWithSmallestTempSpread = 
			minSpread.apply(weatherData, "Day", "MxT", "MnT");
        String teamWithSmallesGoalSpread = 
			minSpread.apply(footballData, "Team", "Goals", "Goals Allowed");
			
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallesGoalSpread);
    }
}
