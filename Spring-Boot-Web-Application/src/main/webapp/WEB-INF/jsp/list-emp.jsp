<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Your Employees are</caption>
			<thead>
				<tr>
					<th>Employee Name</th>
					<th>Date of birth</th>
					<th>Gender</th>
					<th>Note</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="emp">
					<tr>
						<td>${emp.empName}</td>
						<td><fmt:formatDate value="${emp.dob}" pattern="dd/MM/yyyy"/></td>
						<td>${emp.gender}</td>
						<td>${emp.note}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-emp?id=${emp.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-emp?id=${emp.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-emp">Add a Employee</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>