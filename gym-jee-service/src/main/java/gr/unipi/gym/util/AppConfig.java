package gr.unipi.gym.util;

import java.util.Properties;

public class AppConfig {

	private static final Properties properties;

	static {
		properties = AppUtils.readConfig();
	}

	/** Η διεύθυνση για τη σύνδεση με τη βάση δεδομένων. */
	public static final String jdbc_url = properties.getProperty("jdbc_url");

	/** Ο χρήστης για τη σύνδεση με τη βάση δεδομένων. */
	public static final String jdbc_username = properties.getProperty("jdbc_username");

	/** Ο κωδικός για τη σύνδεση με τη βάση δεδομένων. */
	public static final String jdbc_password = properties.getProperty("jdbc_password");
}
