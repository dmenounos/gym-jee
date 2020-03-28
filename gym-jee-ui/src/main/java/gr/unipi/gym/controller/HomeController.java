package gr.unipi.gym.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.unipi.gym.Constants;

/**
 * Ελεκτής αρχικής σελίδας.
 */
@WebServlet(Constants.HOME_CONTROLLER)
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// Μεταφέρουμε τη ροή στη σελίδα που θα δείξουμε.
			RequestDispatcher rd = request.getRequestDispatcher(Constants.HOME_PAGE);
			rd.forward(request, response);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
