package gr.unipi.gym.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtils {

	/** Η διευθυνση του αρχείου διαμόρφωσης της εφαρμογής. */
	public static final String CONFIG_PATH = "/config.properties";

	/**
	 * Διαβάζει την παραμετροποίηση.
	 */
	public static Properties readConfig() {
		try {
			InputStream is = AppConfig.class.getResourceAsStream(CONFIG_PATH);
			Properties properties = new Properties();
			properties.load(is);
			return properties;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
