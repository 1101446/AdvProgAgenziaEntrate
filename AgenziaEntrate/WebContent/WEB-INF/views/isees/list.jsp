<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>
<c:choose>
	<c:when test="${isUser}">
	</c:when>
	<c:otherwise>
		<h4>Elenco isee: ${numISEEs}</h4>
		<table>
			<thead>
				<td>Id</td>
				<td>Anno di validità</td>
				<td>Valore</td>
				<td>Intestatari</td>
				<td>Modifica</td>
				<td>Elimina</td>
			</thead>
			<c:forEach items="${allISEEs}" var="i">
				<tr>
					<td>${i.id}</td>
					<td>${i.yearOfValidity}</td>
					<td>${i.valueOfISEE}</td>
					<td>
						<ul>
							<c:forEach items="${i.associatedUsers}" var="u">
								<li>${u.cf} - ${u.firstName} ${u.secondName} [<a href="<c:url value="/isees/${i.id}/user/${u.cf}/unlink" />">-/-</a>]</li>
							</c:forEach>
						</ul>
					</td>
					<td>[<a href="<c:url value="/isees/${i.id}/edit" />">+</a>]</td>
					<td>[<a href="<c:url value="/isees/${i.id}/delete" />">X</a>]</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<a href="<c:url value="/isees/add" />">Aggiungi isee</a>
		<a href="<c:url value="/isees/link/choose" />">Aggiungi intestatario</a>
	</c:otherwise>
</c:choose>