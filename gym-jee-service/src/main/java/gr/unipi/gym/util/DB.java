package gr.unipi.gym.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class DB {

	private static final Logger logger = LoggerFactory.getLogger(DB.class);

	private Connection connection;

	/**
	 * Βοηθητική: Εκτελεί μια εντολή SQL.
	 */
	public void executeStatement(String sql) {
		logger.debug("execute: sql={}", sql);

		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		}
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Βοηθητική: Εκτελεί μια εντολή SQL τύπου INSERT, UPDATE, DELETE.
	 */
	public int executeUpdate(String sql, Object... params) {
		logger.debug("executeUpdate: sql={}, params={}", sql, params);

		try {
			// Δημιουργούμε ένα PreparedStatement με την εντολή SQL.
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// Περνάμε τις παραμέτρους στο statement.
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}

			// Εκτελούμε το statement.
			statement.executeUpdate();

			// Αποθηκεύουμε το primary key που δημιουργήθηκε.
			ResultSet keys = statement.getGeneratedKeys();
			int pk =  keys.next() ? keys.getInt(1) : 0;

			statement.close();
			return pk;
		}
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Βοηθητική: Εκτελεί μια εντολή SQL τύπου SELECT.
	 */
	public List<Map<String, Object>> executeSelect(String sql, Object... params) {
		logger.debug("executeSelect: sql={}, params={}", sql, params);

		try {
			// Δημιουργούμε ένα PreparedStatement με την εντολή SQL.
			PreparedStatement statement = connection.prepareStatement(sql);

			// Περνάμε τις παραμέτρους στο statement.
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}

			// Εκτελούμε το statement.
			ResultSet resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();

			// Μετατρέπουμε το ResultSet σε List<Map<String, Object>>.
			// Η list περιέχει όλες τις εγγραφές. Το map είναι η κάθε εγγραφή.
			List<Map<String, Object>> results = new ArrayList<>();

			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					row.put(metaData.getColumnName(i + 1), resultSet.getObject(i + 1));
				}
				results.add(row);
			}

			statement.close();
			return results;
		}
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Βοηθητική: Ανοίγει τη σύνδεση με τη βάση δεδομένων.
	 */
	public void openConnection() {
		logger.debug("openConnection: ");

		try {
			connection = DriverManager.getConnection(AppConfig.jdbc_url, AppConfig.jdbc_username, AppConfig.jdbc_password);
		}
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Βοηθητική: Κλείνει τη σύνδεση με τη βάση δεδομένων.
	 */
	public void closeConnection() {
		logger.debug("closeConnection: ");

		try {
			connection.close();
		}
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
