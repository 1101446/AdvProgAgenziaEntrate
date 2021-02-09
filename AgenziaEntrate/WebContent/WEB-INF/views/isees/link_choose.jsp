<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<form method="POST" action="<c:url value="/isees/link" />">
	<table>
		<tr>
			<td>ISEE</td>
			<td>
				<select name="isee">
					<c:forEach items="${isees}" var="i">
						<option value="${i.id}">${i.yearOfValidity} - ${i.valueOfISEE}</option> 
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Utente</td>
			<td>
				<select name="user">
				<c:forEach items="${users}" var="u">
					<option value="${u.cf}">${u.cf} - ${u.firstName} ${u.secondName}</option> 
				</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<input type="submit" value="Salva"/>
</form>