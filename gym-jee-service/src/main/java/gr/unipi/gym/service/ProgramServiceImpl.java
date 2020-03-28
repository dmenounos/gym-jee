package gr.unipi.gym.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unipi.gym.model.GymProgram;
import gr.unipi.gym.util.DB;

@Stateless
public class ProgramServiceImpl implements ProgramService {

	private static final Logger logger = LoggerFactory.getLogger(ProgramServiceImpl.class);

	@Inject
	private DB db;

	@Override
	public void saveProgramData(Integer id, String name, String description, Integer cost) {
		logger.debug("saveProgramData: id={}, name={}, description={}, cost={}", id, name, description, cost);

		try {
			db.openConnection();

			String sql = null;

			if (id == null) {
				sql = "INSERT INTO gym_program (name, description, cost) VALUES (?, ?, ?)";
				db.executeUpdate(sql, name, description, cost);
			}
			else {
				sql = "UPDATE gym_program SET name = ?, description = ?, cost = ? WHERE id = ?";
				db.executeUpdate(sql, name, description, cost, id);
			}
		}
		finally {
			db.closeConnection();
		}
	}

	@Override
	public List<GymProgram> findAll() {
		logger.debug("findAll: ");

		try {
			db.openConnection();

			String sql = "SELECT id, name, description, cost FROM gym_program";

			List<Map<String, Object>> results = db.executeSelect(sql);
			List<GymProgram> objects = new ArrayList<>(results.size());

			for (Map<String, Object> result : results) {
				objects.add(mapGymProgram(result));
			}

			return objects;
		}
		finally {
			db.closeConnection();
		}
	}

	@Override
	public GymProgram findById(Integer id) {
		logger.debug("findById: id={}", id);

		try {
			db.openConnection();

			String sql = "SELECT id, name, description, cost FROM gym_program WHERE id = ?";

			List<Map<String, Object>> results = db.executeSelect(sql, id);

			if (results.size() == 1) {
				return mapGymProgram(results.get(0));
			}

			return null;
		}
		finally {
			db.closeConnection();
		}
	}

	private GymProgram mapGymProgram(Map<String, Object> row) {
		GymProgram gymProgram = new GymProgram();
		gymProgram.setId((Integer) row.get("id"));
		gymProgram.setName((String) row.get("name"));
		gymProgram.setDescription((String) row.get("description"));
		gymProgram.setCost((Integer) row.get("cost"));
		return gymProgram;
	}
}
