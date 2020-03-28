package gr.unipi.gym.service;

import java.util.List;

import javax.ejb.Local;

import gr.unipi.gym.model.GymProgram;

@Local
public interface ProgramService {

	/**
	 * Αποθηκεύει τα στοιχεία ενός προγράμματος στη βάση δεδομένων.
	 */
	void saveProgramData(Integer id, String name, String description, Integer cost);

	/**
	 * Αναζητά όλα τα προγράμματα στη βάση δεδομένων.
	 */
	List<GymProgram> findAll();

	/**
	 * Αναζητά ένα πρόγραμμα στη βάση δεδομένων με id.
	 */
	GymProgram findById(Integer id);
}
