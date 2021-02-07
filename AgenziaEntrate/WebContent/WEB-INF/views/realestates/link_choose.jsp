<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<form method="POST" action="<c:url value="/realestates/link" />">
	<table>
		<tr>
			<td>Immobile</td>
			<td>
				<select name="realestates">
					<c:forEach items="${realestates}" var="re">
						<option value="${re.id}">${re.address} CAP ${re.CAP} Paese ${re.country}</option> 
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
		<tr>
			<td><label name="endOfYear">Data Saldo</label></td>
			<td><input name="endOfYear" type="date"/></td>
		</tr>
		<tr>
			<td><label path="price">Prezzo</label></td>
			<td><input name="price" type="num"/></td>
		</tr>
	</table>
	<input type="submit" value="Salva"/>
</form>