package gr.unipi.gym.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unipi.gym.Constants;
import gr.unipi.gym.model.GymUser;

public class AuthFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	private String loginForm;
	private String userRole;

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.debug("init: ");
		loginForm = config.getInitParameter("loginForm");
		userRole  = config.getInitParameter("userRole");
	}

	@Override
	public void destroy() {
		logger.debug("destroy: ");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request   = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		boolean isAuthenticated = isAuthenticated(request);
		boolean isAuthorized    = isAuthorized(request);

		if (isAuthenticated) {
			if (isAuthorized) {
				chain.doFilter(req, res);
				return;
			}
			else {
				logger.debug("Not logged-in user; Sending forbiden error code!");
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}

		boolean isGetRequest    = "GET".equals(request.getMethod());
		boolean hasLoginFormURI = hasLoginFormURI();
		boolean isLoginFormURI  = isLoginFormURI(request);

		if (isGetRequest && hasLoginFormURI && !isLoginFormURI) {
			RequestDispatcher loginFormResource = request.getRequestDispatcher(loginForm);

			if (loginFormResource == null) {
				throw new RuntimeException("Configured loginForm " + loginForm + " does not exist.");
			}

			logger.debug("Keeping origin Request URI " + request.getRequestURI());
			request.getSession().setAttribute(Constants.LOGIN_ORIGIN_URI_ATTR, request.getRequestURI());

			logger.debug("Keeping origin Query String " + request.getQueryString());
			request.getSession().setAttribute(Constants.LOGIN_ORIGIN_QS_ATTR, request.getQueryString());

			logger.debug("Not logged-in user; Redirecting to the login form!");
			response.sendRedirect(getLoginFormURI(request));
			return;
		}
		else {
			logger.debug("Not logged-in user; Sending forbiden error code!");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
	}

	private boolean hasUserRole() {
		return userRole != null && !userRole.isEmpty();
	}

	private boolean isAuthenticated(HttpServletRequest request) {
		GymUser user = (GymUser) getUser(request);
		return  user != null;
	}

	private boolean isAuthorized(HttpServletRequest request) {
		if (!hasUserRole()) {
			return true;
		}

		GymUser user = (GymUser) getUser(request);
		return  user != null && userRole.equals(user.getRole());
	}

	private Object getUser(HttpServletRequest request) {
		return request.getSession().getAttribute(Constants.USER_ATTR);
	}

	private boolean hasLoginFormURI() {
		return loginForm != null && !loginForm.isEmpty();
	}

	private boolean isLoginFormURI(HttpServletRequest request) {
		if (!hasLoginFormURI()) {
			return false;
		}

		String requestURI = request.getRequestURI();
		String loginFormURI = getLoginFormURI(request);
		return requestURI.equals(loginFormURI);
	}

	private String getLoginFormURI(HttpServletRequest request) {
		return request.getContextPath() + loginForm;
	}
}
