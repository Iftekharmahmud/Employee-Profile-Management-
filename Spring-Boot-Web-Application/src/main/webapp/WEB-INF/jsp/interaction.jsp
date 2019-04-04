<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
	${HEADER}
	
	<table>
	<c:forEach items="${dataMap}" var="dMap" varStatus="status">
		<tr>
			<td>${dMap.key}</td>
			<td><input name="eMap['${dMap.key}']" value="${dMap.value}"/></td>
		</tr>
	</c:forEach>
	</table>
	

<%@ include file="common/footer.jspf"%>