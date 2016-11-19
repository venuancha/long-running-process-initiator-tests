package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class LoadProperties {

	private static LoadProperties instance;
	private static Properties prop = new Properties();

	public static LoadProperties getInstance() {

		if (instance == null) {
			instance = new LoadProperties();
		}

		return instance;
	}

	public Properties loadProperties() {

		InputStream input;
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			input = new FileInputStream(s
					+ "/src/test/resources/config.properties");
			// load a properties file
			prop.load(input);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}
