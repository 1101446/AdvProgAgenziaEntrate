<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<form method="POST" action="<c:url value="/institution/link" />">
	<table>
		<tr>
			<td>Contro Corrente</td>
			<td>
				<select name="bankAccount">
					<c:forEach items="${bankAccounts}" var="bk">
						<option value="${bk.IBAN}--${bk.billDate}">${bk.IBAN} - ${bk.billDate}</option> 
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