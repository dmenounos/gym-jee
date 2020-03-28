package gr.unipi.gym.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unipi.gym.model.GymClass;
import gr.unipi.gym.model.GymProgram;
import gr.unipi.gym.util.DB;

@Stateless
public class ClassServiceImpl implements ClassService {

	private static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Inject
	private DB db;

	@Override
	public void saveClassData(Integer id, String name, String day, String start, String end, Integer programId) {
		logger.debug("saveClassData: id={}, name={}, day={}, start={}, end={}, programId={}", id, name, start, end, programId);

		try {
			db.openConnection();

			String sql = null;

			if (id == null) {
				sql = "INSERT INTO gym_class (name, day, start, end, program_id) VALUES (?, ?, ?, ?, ?)";
				db.executeUpdate(sql, name, day, start, end, programId);
			}
			else {
				sql = "UPDATE gym_class SET name = ?, day = ?, start = ?, end = ?, program_id = ? WHERE id = ?";
				db.executeUpdate(sql, name, day, start, end, programId, id);
			}
		}
		finally {
			db.closeConnection();
		}
	}

	@Override
	public List<GymClass> findAll() {
		logger.debug("findAll: ");

		try {
			db.openConnection();

			String sql = "SELECT id, name, day, start, end, program_id FROM gym_class";

			List<Map<String, Object>> results = db.executeSelect(sql);
			List<GymClass> objects = new ArrayList<>(results.size());

			for (Map<String, Object> result : results) {
				objects.add(mapGymClass(result));
			}

			return objects;
		}
		finally {
			db.closeConnection();
		}
	}

	@Override
	public GymClass findById(Integer id) {
		logger.debug("findAll: id={}", id);

		try {
			db.openConnection();

			String sql = "SELECT id, name, day, start, end, program_id FROM gym_class WHERE id = ?";

			List<Map<String, Object>> results = db.executeSelect(sql, id);

			if (results.size() == 1) {
				return mapGymClass(results.get(0));
			}

			return null;
		}
		finally {
			db.closeConnection();
		}
	}

	private GymClass mapGymClass(Map<String, Object> row) {
		GymClass gymClass = new GymClass();
		gymClass.setId((Integer) row.get("id"));
		gymClass.setName((String) row.get("name"));
		gymClass.setDay((String) row.get("day"));
		gymClass.setStart((String) row.get("start"));
		gymClass.setEnd((String) row.get("end"));

		Integer programId = (Integer) row.get("program_id");

		if (programId != null) {
			GymProgram program = new GymProgram();
			program.setId(programId);
			gymClass.setProgram(program);
		}

		return gymClass;
	}
}
