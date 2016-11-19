package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvFileReader {

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";

	// Student attributes index
	private static final int STUDENT_ID_IDX = 0;

	public static void readCsvFile(String fileName) {

		BufferedReader fileReader = null;

		try {
			String line = "";

			// Create the file reader
			fileReader = new BufferedReader(new FileReader(fileName));

			// Read the CSV file header to skip it
			fileReader.readLine();

			// Read the file line by line starting from the second line
			while ((line = fileReader.readLine()) != null) {
				// Get all tokens available in line
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {

					String jobid = tokens[STUDENT_ID_IDX];
					String api = tokens[1];
					System.out.println("THE JOB ID in this row is..." + jobid);
					System.out.println("THE api call is.." + api);
				}
			}

		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		CsvFileReader cs = new CsvFileReader();
		cs.readCsvFile("TestCSV.csv");
	}

}