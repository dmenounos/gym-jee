<%@ tag pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%-- HELPERS FOR URLS --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="servletPath" value="${pageContext.request.servletPath}" />

<%-- SET CUSTOM LOCALE --%>
<c:if test="${not empty param.lang}">
	<c:set var="lang" value="${param.lang}" scope="session" />
	<fmt:setLocale value="${lang}" scope="session" />
</c:if>

<%-- SET DEFAULT LOCALE --%>
<c:if test="${empty lang and not empty initParam['javax.servlet.jsp.jstl.fmt.locale']}">
	<c:set var="lang" value="${initParam['javax.servlet.jsp.jstl.fmt.locale']}" scope="session" />
	<fmt:setLocale value="${lang}" scope="session" />
</c:if>

<fmt:setBundle basename="messages" />

<c:set var="template_title">
	<fmt:message key="template_title" />
</c:set>

<%-- PAGE TEMPLATE --%>
<!DOCTYPE html>
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

		<title>${template_title}</title>

		<!-- Bootstrap CSS, JS + dependencies -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous" />
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

		<link href="${contextPath}/resources/css/style.css" rel="stylesheet" />

	</head>
	<body>

		<t:navigation>
			<jsp:attribute name="title">
				<a class="navbar-brand" href="${contextPath}/">
					<img src="${contextPath}/resources/images/gym-logo-2.png" alt="" title="${template_title}" class="d-none d-sm-inline" />
					<span class="d-inline d-sm-none">${template_title}</span>
				</a>
			</jsp:attribute>
			<jsp:attribute name="links">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item ${servletPath eq '/WEB-INF/jsps/home.jsp' ? 'active' : ''}">
						<a class="nav-link" href="${contextPath}/controllers/home?lang=${lang}">
							<fmt:message key="navigation_home" />
						</a>
					</li>
					<c:choose>
						<c:when test="${sessionScope.user.role eq 'admin'}">
							<li class="nav-item ${servletPath eq '/WEB-INF/jsps/admin_programs.jsp' ? 'active' : ''}">
								<a class="nav-link" href="${contextPath}/controllers/admin_programs?lang=${lang}">
									<fmt:message key="navigation_admin_programs" />
								</a>
							</li>
							<li class="nav-item ${servletPath eq '/WEB-INF/jsps/admin_classes.jsp' ? 'active' : ''}">
								<a class="nav-link" href="${contextPath}/controllers/admin_classes?lang=${lang}">
									<fmt:message key="navigation_admin_classes" />
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="${contextPath}/controllers/logout">
									<fmt:message key="navigation_logout" />
								</a>
							</li>
						</c:when>
						<c:when test="${sessionScope.user.role eq 'user'}">
							<li class="nav-item ${servletPath eq '/WEB-INF/jsps/member.jsp' ? 'active' : ''}">
								<a class="nav-link" href="${contextPath}/controllers/member?lang=${lang}">
									<fmt:message key="navigation_member" />
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="${contextPath}/controllers/logout">
									<fmt:message key="navigation_logout" />
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item ${servletPath eq '/WEB-INF/jsps/login.jsp' ? 'active' : ''}">
								<a class="nav-link" href="${contextPath}/controllers/login?lang=${lang}">
									<fmt:message key="navigation_login" />
								</a>
							</li>
							<li class="nav-item ${servletPath eq '/WEB-INF/jsps/register.jsp' ? 'active' : ''}">
								<a class="nav-link" href="${contextPath}/controllers/register?lang=${lang}">
									<fmt:message key="navigation_register" />
								</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
				<form id="lang-menu">
					<select name="lang">
						<option value="el" ${lang eq 'el' ? 'selected' : ''}>Ελληνικά</option>
						<option value="en" ${lang eq 'en' ? 'selected' : ''}>English</option>
					</select>
				</form>
			</jsp:attribute>
		</t:navigation>

		<jsp:doBody />

		<footer class="footer">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 contact">
						<p class="address">Phone : <span class="m_10">210 1234567</span></p>
						<p class="address">Email : <span class="m_10">info@mycompany.com</span></p>
					</div>
					<div class="col-sm-4 d-none d-sm-block text-right valid-html">
						<a href="https://validator.w3.org/" target="_blank" title="Valid HTML">
							<img src="${contextPath}/resources/images/HTML5.png" alt="" />
						</a>
					</div>
				</div>
			</div>
		</footer>

		<script>
			$(function() {
				$('#lang-menu select').on('change', function() {
					$('#lang-menu').trigger('submit');
				});
			});
		</script>

	</body>
</html>
