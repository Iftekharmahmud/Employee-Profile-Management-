<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
	<table>
	<tr>
		<th style="width:30%" bgcolor="YellowGreen">Gender</th>
		
		<th style="width:70%" bgcolor="YellowGreen">Employee Count</th>
	</tr>
	
	
	<c:forEach items="${employeeMap}" var="eMap" varStatus="status">
		<tr>
			<td>${eMap.key}</td>
			<td><input name="eMap['${eMap.key}']" value="${eMap.value}"/></td>
		</tr>
	</c:forEach>
	

</table>

<%@ include file="common/footer.jspf"%>