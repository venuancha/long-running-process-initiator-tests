package util;


import java.io.FileWriter;
import java.io.IOException;

public class CsvFileWriter {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER = "INSTANCE_ID";

	public static void writeCsvFile(String fileName, String instanceId) {

		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new student object list to the CSV file
			// for (Student student : students) {
			fileWriter.append(instanceId);
			fileWriter.append(NEW_LINE_SEPARATOR);
			// }

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out
						.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {

		CsvFileWriter cs = new CsvFileWriter();
		cs.writeCsvFile("D:\\Automation\\C4TestCSV.csv","test123");
	}
}
