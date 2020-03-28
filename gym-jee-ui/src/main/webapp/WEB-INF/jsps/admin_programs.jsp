<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="base.jsp" %>
<t:template>
	<div class="page admin-programs-page">

		<div id="form-modal" class="modal" tabindex="-1" role="dialog" aria-labelledby="modal-title">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="modal-title">Πρόγραμμα</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post">
						<div class="modal-body">
							<input type="hidden" name="id" value="${id}" />
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="name">Όνομα</label>
										<input type="text" class="form-control ${not empty nameValidation ? 'is-invalid' : ''}" name="name" value="${name}" placeholder="Όνομα" />
										<c:if test="${not empty nameValidation}">
											<small class='form-text'>${nameValidation}</small>
										</c:if>
									</div>
									<div class="form-group">
										<label for="description">Περιγραφή</label>
										<textarea class="form-control ${not empty descriptionValidation ? 'is-invalid' : ''}" rows="3" name="description" placeholder="Περιγραφή">${description}</textarea>
										<c:if test="${not empty descriptionValidation}">
											<small class='form-text'>${descriptionValidation}</small>
										</c:if>
									</div>
									<div class="form-group">
										<label for="cost">Κόστος</label>
										<input type="text" class="form-control ${not empty costValidation ? 'is-invalid' : ''}" name="cost" value="${cost}" placeholder="Κόστος" />
										<c:if test="${not empty costValidation}">
											<small class='form-text'>${costValidation}</small>
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
					<h3 class="float-left">Προγράμματα</h3>
					<a class="btn btn-primary float-right" href='?page=programs_admin&action=create'>
						Εισαγωγή Προγράμματος
					</a>
					<table class="table table-bordered">
						<thead>
							<tr>
								<td>ID</td>
								<td>Όνομα</td>
								<td>Περιγραφή</td>
								<td>Κόστος</td>
								<td></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${programs}" var="program">
								<tr>
									<td>${program.id}</td>
									<td>${program.name}</td>
									<td>${program.description}</td>
									<td>${program.cost}</td>
									<td>
										<a href='?action=update&id=${program.id}'>
											<i class="fas fa-edit" title="Επεξεργασία προγράμματος"></i>
										</a>
										<a href='?action=delete&id=${program.id}'>
											<i class="fas fa-trash" title="Διαγραφή προγράμματος"></i>
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
