package gr.unipi.gym;

public interface Constants {

	String HOME_CONTROLLER            = "/controllers/home";
	String REGISTER_CONTROLLER        = "/controllers/register";
	String LOGIN_CONTROLLER           = "/controllers/login";
	String LOGOUT_CONTROLLER          = "/controllers/logout";
	String MEMBER_CONTROLLER          = "/controllers/member";
	String ADMIN_CLASSES_CONTROLLER   = "/controllers/admin_classes";
	String ADMIN_PROGRAMS_CONTROLLER  = "/controllers/admin_programs";

	String HOME_PAGE                 = "/WEB-INF/jsps/home.jsp";
	String REGISTER_PAGE             = "/WEB-INF/jsps/register.jsp";
	String LOGIN_PAGE                = "/WEB-INF/jsps/login.jsp";
	String MEMBER_PAGE               = "/WEB-INF/jsps/member.jsp";
	String ADMIN_CLASSES_PAGE        = "/WEB-INF/jsps/admin_classes.jsp";
	String ADMIN_PROGRAMS_PAGE       = "/WEB-INF/jsps/admin_programs.jsp";

	String USER_ATTR                 = "user";
	String LOGIN_ORIGIN_URI_ATTR     = "login-redirect-uri";
	String LOGIN_ORIGIN_QS_ATTR      = "login-redirect-qs";
}
