package gr.unipi.gym.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.unipi.gym.Constants;
import gr.unipi.gym.UIException;
import gr.unipi.gym.model.GymProgram;
import gr.unipi.gym.service.ProgramService;

/**
 * Ελεκτής σελίδας διαχείρισης προγραμμάτοων.
 */
@WebServlet(Constants.ADMIN_PROGRAMS_CONTROLLER)
public class AdminProgramsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramService programService;

	/**
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<GymProgram> programs = programService.findAll();

			request.setAttribute("programs", programs);

			// Παίρνουμε τα δεδομένα που μας στέλνει ο χρήστης.
			String id = (String) request.getParameter("id");

			if (id != null && !id.trim().isEmpty()) {
				GymProgram gymProgram = programService.findById(Integer.valueOf(id));

				// Μεταφέρουμε τα δεδομένα στο request scope.
				request.setAttribute("id", gymProgram.getId());
				request.setAttribute("name", gymProgram.getName());
				request.setAttribute("description", gymProgram.getDescription());
				request.setAttribute("cost", gymProgram.getCost());
			}

			// Μεταφέρουμε τη ροή στη σελίδα που θα δείξουμε.
			RequestDispatcher rd = request.getRequestDispatcher(Constants.ADMIN_PROGRAMS_PAGE);
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

			List<GymProgram> programs = programService.findAll();

			request.setAttribute("programs", programs);

			// Παίρνουμε τα δεδομένα που μας στέλνει ο χρήστης.
			String id = (String) request.getParameter("id");
			String name = (String) request.getParameter("name");
			String description = (String) request.getParameter("description");
			String cost = (String) request.getParameter("cost");

			// Μεταφέρουμε τα δεδομένα στο request scope.
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("description", description);
			request.setAttribute("cost", cost);

			boolean validation = true;

			// Εκτελούμε έλεγχους εγκυρότητας των δεδομένων.
			if (name == null || name.trim().isEmpty()) {
				request.setAttribute("nameValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (description == null || description.trim().isEmpty()) {
				request.setAttribute("descriptionValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (cost == null || cost.trim().isEmpty()) {
				request.setAttribute("costValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (!validation) {
				// Διακόπτουμε τη ροή γιατί τα δεδομένα δεν είναι έγκυρα.
				RequestDispatcher rd = request.getRequestDispatcher(Constants.ADMIN_PROGRAMS_PAGE);
				rd.forward(request, response);
				return;
			}

			// Ενημέρωση στοιχείων τμήματος
			Integer dataId = id != null && !id.trim().isEmpty() ? Integer.valueOf(id) : null;
			programService.saveProgramData(dataId, name, description, Integer.valueOf(cost));

			// Μεταφέρουμε τη ροή στη σελίδα που θα δείξουμε.
			String redirectUri = getRedirectURI(request);
			response.sendRedirect(redirectUri);
		}
		catch (UIException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getRedirectURI(HttpServletRequest request) {
		return request.getContextPath() + Constants.ADMIN_PROGRAMS_CONTROLLER;
	}
}
