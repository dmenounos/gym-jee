<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="base.jsp" %>
<t:template>
	<div class="page login-page">

		<div class="banner d-none d-xl-block">
			<img src="${pageContext.request.contextPath}/resources/images/img03.jpg" alt="" />
			<div class="container">
				<h1>Σύνδεση Χρήστη</h1>
			</div>
		</div>

		<div class="container main-container">
			<div class="row">
				<div class="col-xl-4">
					<h2>Είναι εύκολο!</h2>
					<p>Βάλε τα στοιχεία σου και γράψου στα μοναδικά προγράμματα του γυμναστηρίου μας.</p>
				</div>
				<div class="col-xl-8">
					<form method="post" action="${pageContext.request.contextPath}/controllers/login">
						<c:if test="${not empty errorMessage}">
							<div class="alert alert-danger" role="alert">${errorMessage}</div>
						</c:if>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label class="sr-only" for="email">Email</label>
									<input type="email" class="form-control ${not empty emailValidation ? 'is-invalid' : ''}" id="email" name="email" value="${email}" placeholder="Email" />
									<c:if test="${not empty emailValidation}">
										<small class='form-text'>${emailValidation}</small>
									</c:if>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group"   >
									<label class="sr-only" for="password">Password</label>
									<input type="password" class="form-control ${not empty passwordValidation ? 'is-invalid' : ''}" id="password" name="password" value="${password}" placeholder="Password" />
									<c:if test="${not empty passwordValidation}">
										<small class='form-text'>${passwordValidation}</small>
									</c:if>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button type="submit" class="btn btn-primary">Υποβολή</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>
</t:template>
