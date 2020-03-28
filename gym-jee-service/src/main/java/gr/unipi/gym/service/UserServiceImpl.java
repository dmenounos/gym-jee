package gr.unipi.gym.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unipi.gym.model.GymUser;
import gr.unipi.gym.util.DB;

@Stateless
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private DB db;

	@Override
	public Integer createUserData(String fname, String lname, String email, String password) {
		logger.debug("createUserData: fname={}, lname={}, email={}, password={}", fname, lname, email, password);

		try {
			db.openConnection();

			String sql = "INSERT INTO gym_user (role, fname, lname, email, password) VALUES ('user', ?, ?, ?, ?)";

			return db.executeUpdate(sql, fname, lname, email, password);
		}
		finally {
			db.closeConnection();
		}
	}

	@Override
	public void updateUserData(Integer id, String email, String fname, String lname, String age, String sex) {
		logger.debug("updateUserData: id={}, email={}, fname={}, lname={}, age={}, sex={}", id, email, fname, lname, age, sex);

		try {
			db.openConnection();

			String sql = "UPDATE gym_user SET email = ?, fname = ?, lname = ?, age = ?, sex = ? WHERE id = ?";

			db.executeUpdate(sql, email, fname, lname, age, sex, id);
		}
		finally {
			db.closeConnection();
		}
	}

	@Override
	public GymUser findById(Integer id) {
		logger.debug("findById: id={}", id);

		try {
			db.openConnection();

			String sql = "SELECT id, role, username, fname, lname, email, age, sex FROM gym_user WHERE id = ?";

			List<Map<String, Object>> results = db.executeSelect(sql, id);

			if (results.size() == 1) {
				return mapUser(results.get(0));
			}

			return null;
		}
		finally {
			db.closeConnection();
		}
	}

	@Override
	public GymUser findByEmailAndPassword(String email, String password) {
		logger.debug("findByEmailAndPassword: email={}, password={}", email, password);

		try {
			db.openConnection();

			String sql = "SELECT id, role, username, fname, lname, email, age, sex FROM gym_user WHERE email = ? AND password = ?";

			List<Map<String, Object>> results = db.executeSelect(sql, email, password);

			if (results.size() == 1) {
				return mapUser(results.get(0));
			}

			return null;
		}
		finally {
			db.closeConnection();
		}
	}

	@Override
	public GymUser findByUsernameAndPassword(String username, String password) {
		logger.debug("findByUsernameAndPassword: username={}, password={}", username, password);

		try {
			db.openConnection();

			String sql = "SELECT id, role, username, fname, lname, email, age, sex FROM gym_user WHERE username = ? AND password = ?";

			List<Map<String, Object>> results = db.executeSelect(sql, username, password);

			if (results.size() == 1) {
				return mapUser(results.get(0));
			}

			return null;
		}
		finally {
			db.closeConnection();
		}
	}

	private GymUser mapUser(Map<String, Object> row) {
		GymUser gymUser = new GymUser();
		gymUser.setId((Integer) row.get("id"));
		gymUser.setRole((String) row.get("role"));
		gymUser.setUsername((String) row.get("username"));
		gymUser.setPassword((String) row.get("password"));
		gymUser.setEmail((String) row.get("email"));
		gymUser.setFname((String) row.get("fname"));
		gymUser.setLname((String) row.get("lname"));
		gymUser.setAge((String) row.get("age"));
		gymUser.setSex((String) row.get("sex"));
		return gymUser;
	}
}
