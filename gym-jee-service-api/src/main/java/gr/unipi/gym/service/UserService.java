package gr.unipi.gym.service;

import javax.ejb.Local;

import gr.unipi.gym.model.GymUser;

@Local
public interface UserService {

	/**
	 * Αποθηκεύει τα στοιχεία του χρήστη στη βάση δεδομένων.
	 */
	Integer createUserData(String fname, String lname, String email, String password);

	/**
	 * Αποθηκεύει τα στοιχεία του χρήστη στη βάση δεδομένων.
	 */
	void updateUserData(Integer id, String email, String fname, String lname, String age, String sex);

	/**
	 * Αναζητά ένα χρήστη στη βάση δεδομένων με id.
	 */
	GymUser findById(Integer id);

	/**
	 * Αναζητά ένα χρήστη στη βάση δεδομένων με email και password.
	 */
	GymUser findByEmailAndPassword(String email, String password);

	/**
	 * Αναζητά ένα χρήστη στη βάση δεδομένων με username και password.
	 */
	GymUser findByUsernameAndPassword(String username, String password);
}
