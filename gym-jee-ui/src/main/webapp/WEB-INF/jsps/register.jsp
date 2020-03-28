<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="base.jsp" %>
<t:template>
	<div class="page register-page">

		<div class="banner d-none d-xl-block">
			<img src="${pageContext.request.contextPath}/resources/images/img01.jpg" alt="" />
			<div class="container">
				<h1>Εγγραφή Χρήστη</h1>
			</div>
		</div>

		<div class="container main-container">
			<div class="row">
				<div class="col-xl-4">
					<h2>Γίνε Μέλος</h2>
					<p>Απόλαυσε τα μοναδικά πλεονεκτήματα που σου προσφέρει η εγγραφή μέλους στο γυμναστήριό μας.</p>
				</div>
				<div class="col-xl-8">
					<form method="post" action="${pageContext.request.contextPath}/controllers/register">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label class="sr-only" for="fname">Όνομα</label>
									<input type="text" class="form-control ${not empty fnameValidation ? 'is-invalid' : ''}" id="fname" name="fname" value="${fname}" placeholder="Όνομα" />
									<c:if test="${not empty fnameValidation}">
										<small class='form-text'>${fnameValidation}</small>
									</c:if>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group <?=field_status($this->lname_validation);?>">
									<label class="sr-only" for="lname">Επώνυμο</label>
									<input type="text" class="form-control ${not empty lnameValidation ? 'is-invalid' : ''}" id="lname" name="lname" value="${lname}" placeholder="Επώνυμο" />
									<c:if test="${not empty lnameValidation}">
										<small class='form-text'>${lnameValidation}</small>
									</c:if>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group <?=field_status($this->email_validation);?>">
									<label class="sr-only" for="email">Email</label>
									<input type="email" class="form-control ${not empty emailValidation ? 'is-invalid' : ''}" id="email" name="email" value="${email}" placeholder="Email" />
									<c:if test="${not empty emailValidation}">
										<small class='form-text'>${emailValidation}</small>
									</c:if>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group <?=field_status($this->password_validation);?>">
									<label class="sr-only" for="password_1">Κωδικός</label>
									<input type="password" class="form-control ${not empty passwordValidation ? 'is-invalid' : ''}" id="password1" name="password1" value="${password1}" placeholder="Κωδικός" />
									<c:if test="${not empty passwordValidation}">
										<small class='form-text'>${passwordValidation}</small>
									</c:if>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group <?=field_status($this->password_validation);?>">
									<label class="sr-only" for="password_2">Επιβεβαίωση κωδικού</label>
									<input type="password" class="form-control ${not empty passwordValidation ? 'is-invalid' : ''}" id="password2" name="password2" value="${password2}" placeholder="Επιβεβαίωση κωδικού" />
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
