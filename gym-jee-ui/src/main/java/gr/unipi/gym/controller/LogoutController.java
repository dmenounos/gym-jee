package gr.unipi.gym.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.unipi.gym.Constants;

/**
 * Ελεκτής αποσύνδεσης χρήστη.
 */
@WebServlet(Constants.LOGOUT_CONTROLLER)
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().invalidate();

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
