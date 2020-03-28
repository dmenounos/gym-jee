<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="base.jsp" %>
<t:template>
	<div class="page member-page">

		<div class="container main-container">
			<div class="row">
				<div class="col-xl-4 d-none d-xl-block">
					<c:if test="${not empty sex}">
						<img src='${pageContext.request.contextPath}/resources/images/user_${fn:toLowerCase(sex)}.png' alt='' class="user-icon" />
					</c:if>
				</div>
				<div class="col-xl-8">
					<form method="post">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label for="fname">Όνομα</label>
									<input type="text" class="form-control ${not empty fnameValidation ? 'is-invalid' : ''}" id="fname" name="fname" value="${fname}" placeholder="Όνομα" />
									<c:if test="${not empty fnameValidation}">
										<small class='form-text'>${fnameValidation}</small>
									</c:if>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="lname">Επώνυμο</label>
									<input type="text" class="form-control ${not empty lnameValidation ? 'is-invalid' : ''}" id="lname" name="lname" value="${lname}" placeholder="Επώνυμο" />
									<c:if test="${not empty lnameValidation}">
										<small class='form-text'>${lnameValidation}</small>
									</c:if>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label for="email">Email</label>
									<input type="email" class="form-control ${not empty emailValidation ? 'is-invalid' : ''}" id="email" name="email" value="${email}" placeholder="Email" />
									<c:if test="${not empty emailValidation}">
										<small class='form-text'>${emailValidation}</small>
									</c:if>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label for="age">Ηλικία</label>
									<input type="text" class="form-control" name="age" value="${age}" placeholder="Ηλικία">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="radio">
									<label>
										<input type="radio" name="sex" value="MALE" ${sex eq "MALE" ? "checked" : "" }>
										Άνδρας
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="sex" value="FEMALE" ${sex eq "FEMALE" ? "checked" : ""}>
										Γυναίκα
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<button type="submit" class="btn btn-primary">Ενημέρωση Στοιχείων</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>
</t:template>
