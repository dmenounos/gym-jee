package gr.unipi.gym.controller;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.unipi.gym.Constants;
import gr.unipi.gym.model.GymUser;
import gr.unipi.gym.service.UserService;

/**
 * Ελεκτής σύνδεσης χρήστη.
 */
@WebServlet(Constants.REGISTER_CONTROLLER)
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	/**
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// Μεταφέρουμε τη ροή στη σελίδα που θα δείξουμε.
			RequestDispatcher rd = request.getRequestDispatcher(Constants.REGISTER_PAGE);
			rd.forward(request, response);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Χειρίζεται την ροή υποβολής της φόρμας.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");

			// Παίρνουμε τα δεδομένα που μας στέλνει ο χρήστης.
			String fname     = (String) request.getParameter("fname");
			String lname     = (String) request.getParameter("lname");
			String email     = (String) request.getParameter("email");
			String password1 = (String) request.getParameter("password1");
			String password2 = (String) request.getParameter("password2");

			// Μεταφέρουμε τα δεδομένα στο request scope.
			request.setAttribute("fname", fname);
			request.setAttribute("lname", lname);
			request.setAttribute("email", email);
			request.setAttribute("password1", password1);
			request.setAttribute("password2", password2);

			boolean validation = true;

			// Εκτελούμε έλεγχους εγκυρότητας των δεδομένων.
			if (fname == null || fname.trim().isEmpty()) {
				request.setAttribute("fnameValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (lname == null || lname.trim().isEmpty()) {
				request.setAttribute("lnameValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (email == null || email.trim().isEmpty()) {
				request.setAttribute("emailValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (password1 == null || password1.trim().isEmpty() || password2 == null || password2.trim().isEmpty()) {
				request.setAttribute("passwordValidation", "Τα πεδία είναι υποχρεωτικά.");
				validation = false;
			}
			else if (!password1.equals(password2)) {
				request.setAttribute("passwordValidation", "Ο κωδικός πρέπει να είναι ίδιος.");
				validation = false;
			}

			if (!validation) {
				// Διακόπτουμε τη ροή γιατί τα δεδομένα δεν είναι έγκυρα.
				RequestDispatcher rd = request.getRequestDispatcher(Constants.REGISTER_PAGE);
				rd.forward(request, response);
				return;
			}

			// Δημιουργία λογαριασμού χρήστη
			Integer userId = userService.createUserData(fname, lname, email, password1);
			GymUser user = userService.findById(userId);

			request.getSession().setAttribute(Constants.USER_ATTR, user);

			// Μεταφέρουμε τη ροή στη σελίδα που θα δείξουμε.
			String redirectUri = getRedirectURI(request);
			response.sendRedirect(redirectUri);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getRedirectURI(HttpServletRequest request) {
		return request.getContextPath() + Constants.HOME_CONTROLLER;
	}
}
