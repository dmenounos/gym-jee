<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="base.jsp" %>
<t:template>
	<div class="page home-page">

		<div class="jumbotron">
			<div class="container">
				<h1>Γυμναστήριο Java</h1>
			</div>
		</div>

		<div class="container main-container">
			<div class="row">
				<div class="col-sm-4 d-none d-sm-block">
					<img src="${pageContext.request.contextPath}/resources/images/pic1.jpg" class="img-fluid" alt="" />
				</div>
				<div class="col-sm-4">
					<img src="${pageContext.request.contextPath}/resources/images/pic2.jpg" class="img-fluid" alt="" />
				</div>
				<div class="col-sm-4 d-none d-sm-block">
					<img src="${pageContext.request.contextPath}/resources/images/pic3.jpg" class="img-fluid" alt="" />
				</div>
			</div>
		</div>

	</div>
</t:template>
