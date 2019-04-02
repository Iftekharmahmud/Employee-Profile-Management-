<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<form:form method="post" commandName="emp">
		<form:hidden path="id" />
			
			<fieldset class="form-group">
				<form:label path="empName">Employee Name</form:label>
				<form:input path="empName" type="text" class="form-control"
					required="required" />
				<form:errors path="empName" cssClass="text-warning" />
			</fieldset>
	
	
			<fieldset class="form-group">
				<form:label path="dob">Date of birth (dd/MM/yyyy):</form:label>
				<form:input path="dob" type="text" class="form-control" required="required"/>
				<form:errors path="dob" cssClass="text-warning" />
			</fieldset>
			
			
			<fieldset class="form-group">
				<form:label path="gender">Gender</form:label>
				<form:radiobutton path="gender" value="M" label="Male" />
                <form:radiobutton path="gender" value="F" label="Female" />
			</fieldset>
			
	
			<fieldset class="form-group">
				<form:label path="note">Note</form:label>
				<form:textarea path="note" rows="5" cols="30" class="form-control" required="required" />
				<form:errors path="note" cssClass="text-warning" />
			</fieldset>	
		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf"%>