<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('UTENTE')" var="isUser" />

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>
<c:choose>
	<c:when test="${isUser}">
		<h4>Cronologia ISEE effettuati</h4>
		<table>
			<thead>
				<td>Anno di validità</td>
				<td>Valore</td>
				<td>Intestatari</td>
			</thead>
			<c:forEach items="${profileISEEs}" var="i">
				<tr>
					<td>${i.isee.yearOfValidity}</td>
					<td>${i.isee.valueOfISEE}</td>
					<td>
						${i.user.cf} - ${i.user.firstName} ${i.user.secondName}</li>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<h4>Elenco isee: ${numISEEs}</h4>
		<table>
			<thead>
				<td>Id</td>
				<td>Anno di validità</td>
				<td>Valore</td>
				<td>Modifica</td>
				<td>Elimina</td>
			</thead>
			<c:forEach items="${allISEEs}" var="i">
				<tr>
					<td>${i.id}</td>
					<td>${i.yearOfValidity}</td>
					<td>${i.valueOfISEE}</td>
					<td>[<a href="<c:url value="/isees/${i.id}/edit" />">+</a>]</td>
					<td>[<a href="<c:url value="/isees/${i.id}/delete" />">X</a>]</td>
				</tr>
			</c:forEach>
		</table>
		<h4>Elenco isee associati: ${numUserISEEs}</h4>
		<table>
			<thead>
				<td>Id</td>
				<td>Anno di validità</td>
				<td>Valore</td>
				<td>Intestatari</td>
			</thead>
			<c:forEach items="${allUserISEEs}" var="ui">
				<tr>
					<td>${ui.isee.id}</td>
					<td>${ui.isee.yearOfValidity}</td>
					<td>${ui.isee.valueOfISEE}</td>
					<td>
						${ui.user.cf} - ${ui.user.firstName} ${ui.user.secondName} [<a href="<c:url value="/isees/${ui.isee.id}/user/${ui.user.cf}/unlink" />">-/-</a>]
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<a href="<c:url value="/isees/add" />">Aggiungi isee</a>
		<a href="<c:url value="/isees/link/choose" />">Aggiungi intestatario</a>
	</c:otherwise>
</c:choose>