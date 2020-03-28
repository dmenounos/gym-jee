package gr.unipi.gym.service;

import java.util.List;

import javax.ejb.Local;

import gr.unipi.gym.model.GymClass;

@Local
public interface ClassService {

	/**
	 * Αποθηκεύει τα στοιχεία ενός τμήματος στη βάση δεδομένων.
	 */
	void saveClassData(Integer id, String name, String day, String start, String end, Integer programId);

	/**
	 * Αναζητά όλα τα τμήματα στη βάση δεδομένων.
	 */
	List<GymClass> findAll();

	/**
	 * Αναζητά ένα τμήμα στη βάση δεδομένων με id.
	 */
	GymClass findById(Integer id);
}
