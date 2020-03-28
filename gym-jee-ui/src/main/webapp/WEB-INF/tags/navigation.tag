<%@ tag pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%-- FRAGMENT ATTRIBUTES --%>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="links" fragment="true" %>

<%-- SIMPLE ATTRIBUTES --%>
<%@ attribute name="theme" type="String" %>

<c:choose>
	<c:when test="${theme == 'light'}">
		<c:set var="color" value="light" />
	</c:when>
	<c:otherwise>
		<c:set var="color" value="dark" />
	</c:otherwise>
</c:choose>

<%-- TOP LEVEL CONTAINER --%>
<nav class="navbar navbar-expand-sm fixed-top navbar-${color} bg-${color}">
	<div class="container">

		<%-- TOP LEVEL TITLE FRAGMENT --%>
		<jsp:invoke fragment="title" />

		<%-- 1ST LEVEL RESPONSIVE TOGGLE --%>
		<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar" 
			aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<%-- 1ST LEVEL MENU CONTAINER --%>
		<div id="navbar" class="collapse navbar-collapse">

			<%-- 1ST LEVEL LINKS FRAGMENT --%>
			<jsp:invoke fragment="links" />

		</div>

	</div>
</nav>
