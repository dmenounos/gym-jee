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
@WebServlet(Constants.LOGIN_CONTROLLER)
public class LoginController extends HttpServlet {

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
			RequestDispatcher rd = request.getRequestDispatcher(Constants.LOGIN_PAGE);
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
			String email = (String) request.getParameter("email");
			String password = (String) request.getParameter("password");

			// Μεταφέρουμε τα δεδομένα στο request scope.
			request.setAttribute("email", email);
			request.setAttribute("password", password);

			boolean validation = true;

			// Εκτελούμε έλεγχους εγκυρότητας των δεδομένων.
			if (email == null || email.trim().isEmpty()) {
				request.setAttribute("emailValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (password == null || password.trim().isEmpty()) {
				request.setAttribute("passwordValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (!validation) {
				// Διακόπτουμε τη ροή γιατί τα δεδομένα δεν είναι έγκυρα.
				RequestDispatcher rd = request.getRequestDispatcher(Constants.LOGIN_PAGE);
				rd.forward(request, response);
				return;
			}

			// Επαλήθευση υποβληθέντων στοιχείων χρήστη.
			GymUser user = userService.findByEmailAndPassword(email, password);

			if (user == null) {
				request.setAttribute("errorMessage", "Τα στοιχεία που δώσατε δεν είναι σωστά.");

				// Διακόπτουμε τη ροή γιατί τα αποτελέσματα δεν είναι έγκυρα.
				RequestDispatcher rd = request.getRequestDispatcher(Constants.LOGIN_PAGE);
				rd.forward(request, response);
				return;
			}

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
		String originURI = (String) request.getSession().getAttribute(Constants.LOGIN_ORIGIN_URI_ATTR);
		String originQS = (String) request.getSession().getAttribute(Constants.LOGIN_ORIGIN_QS_ATTR);

		if (originURI != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(originURI);

			if (originQS != null) {
				sb.append("?" + originQS);
			}

			return sb.toString();
		}

		return request.getContextPath() + Constants.HOME_CONTROLLER;
	}
}
