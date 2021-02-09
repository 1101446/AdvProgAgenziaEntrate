<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<h4>Elenco ruoli: ${numAccess}</h4>

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>
<table class="text-center">
	<thead>
		<td>ID</td>
		<td>Nome ruolo</td>
		<td>Priorità</td>
		<td>Descrizione</td>
		<td>Modifica</td>
		<td>Elimina</td>
	</thead>
	<c:forEach items="${allAccess}" var="a">
		<tr>
			<td>${a.id}</td>
			<td>${a.roleName}</td>
			<td>${a.priority}</td>
			<td>${a.description}</td>
			<td>[<a href="<c:url value="/roles/${a.id}/edit/" />">+</a>]</td>
			<td>[<a href="<c:url value="/roles/${a.id}/delete/" />">X</a>]</td>
		</tr>
	</c:forEach>
</table>
<hr/>
<a href="<c:url value="/roles/add" />">Aggiungi nuovo ruolo</a>
<a href="<c:url value="/roles/link/choose" />">Modifica ruolo utenti</a>