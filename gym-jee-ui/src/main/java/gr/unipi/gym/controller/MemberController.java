package gr.unipi.gym.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.unipi.gym.Constants;
import gr.unipi.gym.UIException;
import gr.unipi.gym.model.GymUser;
import gr.unipi.gym.service.UserService;

/**
 * Ελεκτής σελίδας προφίλ χρήστη.
 */
@WebServlet(Constants.MEMBER_CONTROLLER)
public class MemberController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	/**
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			GymUser user = getSessionUser(request);

			user = userService.findById(user.getId());

			// Μεταφέρουμε τα δεδομένα στο request scope.
			request.setAttribute("fname", user.getFname());
			request.setAttribute("lname", user.getLname());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("age", user.getAge());
			request.setAttribute("sex", user.getSex());

			// Μεταφέρουμε τη ροή στη σελίδα που θα δείξουμε.
			RequestDispatcher rd = request.getRequestDispatcher(Constants.MEMBER_PAGE);
			rd.forward(request, response);
		}
		catch (UIException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Χειρίζεται την ροή υποβολής της φόρμας.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			GymUser user = getSessionUser(request);

			// Παίρνουμε τα δεδομένα που μας στέλνει ο χρήστης.
			String fname     = (String) request.getParameter("fname");
			String lname     = (String) request.getParameter("lname");
			String email     = (String) request.getParameter("email");
			String age       = (String) request.getParameter("age");
			String sex       = (String) request.getParameter("sex");

			// Μεταφέρουμε τα δεδομένα στο request scope.
			request.setAttribute("fname", fname);
			request.setAttribute("lname", lname);
			request.setAttribute("email", email);
			request.setAttribute("age", age);
			request.setAttribute("sex", sex);

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

			if (!validation) {
				// Διακόπτουμε τη ροή γιατί τα δεδομένα δεν είναι έγκυρα.
				RequestDispatcher rd = request.getRequestDispatcher(Constants.MEMBER_PAGE);
				rd.forward(request, response);
				return;
			}

			// Ενημέρωση υποβληθέντων στοιχείων χρήστη
			userService.updateUserData(user.getId(), email, fname, lname, age, sex);
			user = userService.findById(user.getId());

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
		return request.getContextPath() + Constants.MEMBER_CONTROLLER;
	}

	private GymUser getSessionUser(HttpServletRequest request) {
		GymUser user = (GymUser) request.getSession().getAttribute(Constants.USER_ATTR);

		if (user == null) {
			throw new UIException("Δεν υπάρχει χρήστης");
		}

		return user;
	}
}
