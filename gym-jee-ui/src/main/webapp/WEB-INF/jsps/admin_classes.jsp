<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="base.jsp" %>
<t:template>
	<div class="page admin-classes-page">

		<div id="form-modal" class="modal" tabindex="-1" role="dialog" aria-labelledby="modal-title">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="modal-title">Τμήμα</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post">
						<div class="modal-body">
							<input type="hidden" name="id" value="${id}" />
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="name">Όνομα</label>
										<input type="text" class="form-control ${not empty nameValidation ? 'is-invalid' : ''}" name="name" value="${name}" placeholder="Όνομα" />
										<c:if test="${not empty nameValidation}">
											<small class='form-text'>${nameValidation}</small>
										</c:if>
									</div>
									<div class="form-group">
										<label for="day">Ημέρα</label>
										<input type="text" class="form-control ${not empty dayValidation ? 'is-invalid' : ''}" name="day" value="${day}" placeholder="Ημέρα" />
										<c:if test="${not empty dayValidation}">
											<small class='form-text'>${dayValidation}</small>
										</c:if>
									</div>
									<div class="form-group">
										<label for="program_id">Πρόγραμμα</label>
										<select class="form-control ${not empty programIdValidation ? 'is-invalid' : ''}" name="program_id">
											<c:forEach items="${programs}" var="program">
												<option value='${program.key}' ${program.key eq programId ? "selected" : ""}>${program.value.name}</option>
											</c:forEach>
										</select>
										<c:if test="${not empty programIdValidation}">
											<small class='form-text'>${programIdValidation}</small>
										</c:if>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="start">Ώρα έναρξη</label>
										<input type="text" class="form-control ${not empty startValidation ? 'is-invalid' : ''}" name="start" value="${start}" placeholder="Ώρα έναρξης" />
										<c:if test="${not empty startValidation}">
											<small class='form-text'>${startValidation}</small>
										</c:if>
									</div>
									<div class="form-group">
										<label for="end">Ώρα ήξης</label>
										<input type="text" class="form-control ${not empty endValidation ? 'is-invalid' : ''}" name="end" value="${end}" placeholder="Ώρα λήξης" />
										<c:if test="${not empty endValidation}">
											<small class='form-text'>${endValidation}</small>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Κλείσιμο</button>
							<button type="submit" class="btn btn-primary">Υποβολή</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<c:if test="${param['action'] eq 'create' or param['action'] eq 'update'}">
			<script>$('#form-modal').modal('show')</script>
		</c:if>

		<div class="container main-container">
			<div class="row">
				<div class="col-md-12">
					<h3 class="float-left">Τμήματα</h3>
					<a class="btn btn-primary float-right" href='?action=create'>
						Εισαγωγή Τμήματος
					</a>
					<table class="table table-bordered">
						<thead>
							<tr>
								<td>ID</td>
								<td>Όνομα</td>
								<td>Ημέρα</td>
								<td>Εκκίνηση</td>
								<td>Λήξη</td>
								<td>Πρόγραμμα</td>
								<td></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${classes}" var="klass">
								<tr>
									<td>${klass.id}</td>
									<td>${klass.name}</td>
									<td>${klass.day}</td>
									<td>${klass.start}</td>
									<td>${klass.end}</td>
									<td>
										<c:if test="${not empty klass.program}">
											${programs[klass.program.id].name}
										</c:if>
									</td>
									<td>
										<a href='?action=update&id=${klass.id}'>
											<i class="fas fa-edit" title="Επεξεργασία τμήματος"></i>
										</a>
										<a href='?action=delete&id=${klass.id}'>
											<i class="fas fa-trash" title="Διαγραφή τμήματος"></i>
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
</t:template>
