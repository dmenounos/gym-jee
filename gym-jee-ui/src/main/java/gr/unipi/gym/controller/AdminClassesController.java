package gr.unipi.gym.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.unipi.gym.Constants;
import gr.unipi.gym.UIException;
import gr.unipi.gym.model.GymClass;
import gr.unipi.gym.model.GymProgram;
import gr.unipi.gym.service.ClassService;
import gr.unipi.gym.service.ProgramService;

/**
 * Ελεκτής σελίδας διαχείρισης τμημάτων.
 */
@WebServlet(Constants.ADMIN_CLASSES_CONTROLLER)
public class AdminClassesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClassService classService;

	@Inject
	private ProgramService programService;

	/**
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Map<Integer, GymProgram> programs = programService.findAll()
				.stream().collect(Collectors.toMap(GymProgram::getId, Function.identity()));

			List<GymClass> classes = classService.findAll();

			request.setAttribute("programs", programs);
			request.setAttribute("classes", classes);

			// Παίρνουμε τα δεδομένα που μας στέλνει ο χρήστης.
			String id = (String) request.getParameter("id");

			if (id != null && !id.trim().isEmpty()) {
				GymClass gymClass = classService.findById(Integer.valueOf(id));

				// Μεταφέρουμε τα δεδομένα στο request scope.
				request.setAttribute("id", gymClass.getId());
				request.setAttribute("name", gymClass.getName());
				request.setAttribute("day", gymClass.getDay());
				request.setAttribute("start", gymClass.getStart());
				request.setAttribute("end", gymClass.getEnd());
				request.setAttribute("programId", gymClass.getProgram().getId());
			}

			// Μεταφέρουμε τη ροή στη σελίδα που θα δείξουμε.
			RequestDispatcher rd = request.getRequestDispatcher(Constants.ADMIN_CLASSES_PAGE);
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

			Map<Integer, GymProgram> programs = programService.findAll()
				.stream().collect(Collectors.toMap(GymProgram::getId, Function.identity()));

			List<GymClass> classes = classService.findAll();

			request.setAttribute("programs", programs);
			request.setAttribute("classes", classes);

			// Παίρνουμε τα δεδομένα που μας στέλνει ο χρήστης.
			String id = (String) request.getParameter("id");
			String name = (String) request.getParameter("name");
			String day = (String) request.getParameter("day");
			String start = (String) request.getParameter("start");
			String end = (String) request.getParameter("end");
			String programId = (String) request.getParameter("program_id");

			// Μεταφέρουμε τα δεδομένα στο request scope.
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("day", day);
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			request.setAttribute("programId", programId);

			boolean validation = true;

			// Εκτελούμε έλεγχους εγκυρότητας των δεδομένων.
			if (name == null || name.trim().isEmpty()) {
				request.setAttribute("nameValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (day == null || day.trim().isEmpty()) {
				request.setAttribute("dayValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (start == null || start.trim().isEmpty()) {
				request.setAttribute("startValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (end == null || end.trim().isEmpty()) {
				request.setAttribute("endValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (programId == null || programId.trim().isEmpty()) {
				request.setAttribute("programIdValidation", "Το πεδίο είναι υποχρεωτικό.");
				validation = false;
			}

			if (!validation) {
				// Διακόπτουμε τη ροή γιατί τα δεδομένα δεν είναι έγκυρα.
				RequestDispatcher rd = request.getRequestDispatcher(Constants.ADMIN_CLASSES_PAGE);
				rd.forward(request, response);
				return;
			}

			// Ενημέρωση στοιχείων τμήματος
			Integer dataId = id != null && !id.trim().isEmpty() ? Integer.valueOf(id) : null;
			classService.saveClassData(dataId, name, day, start, end, Integer.valueOf(programId));

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
		return request.getContextPath() + Constants.ADMIN_CLASSES_CONTROLLER;
	}
}
