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
		<table class="text-center">
			<thead>
				<td>Codice Fiscale</td>
				<td>Nome</td>
				<td>Cognome</td>
				<td>Data di Nascita</td>
				<td>Handicap</td>
				<td>Email</td>
				<td>Password</td>
				<td>Modifica</td>
			</thead>
			<tr>
				<td>${profile.cf}</td>
				<td>${profile.firstName}</td>
				<td>${profile.secondName}</td>
				<td>${profile.birthD}</td>
				<c:choose> 
					<c:when test="${profile.handicap}">
						<td>Si</td>
					</c:when>
					<c:otherwise>
						<td>No</td>
					</c:otherwise>
				</c:choose>
				<td>${profile.email}</td>
				<td>**********</td>
				<td>[<a href="<c:url value="/users/${profile.cf}/edit" />">+</a>]</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<h4>Elenco utenti: ${numUsers}</h4> 
		<table class="text-center">
			<thead>
				<td>Codice Fiscale</td>
				<td>Nome</td>
				<td>Cognome</td>
				<td>Data di Nascita</td>
				<td>Handicap</td>
				<td>Email</td>
				<td>Password</td>
				<td>Ruolo</td>
				<td>Modifica</td>
				<td>Elimina</td>
			</thead>
			<c:forEach items="${allUsers}" var="u">
				<tr>
					<td>${u.cf}</td>
					<td>${u.firstName}</td>
					<td>${u.secondName}</td>
					<td>${u.birthD}</td>
					<c:choose> 
						<c:when test="${u.handicap}">
							<td>Si</td>
						</c:when>
						<c:otherwise>
							<td>No</td>
						</c:otherwise>
					</c:choose>
					<td>${u.email}</td>
					<td>**********</td>
					<td>${u.access.roleName}</td>
					<td>[<a href="<c:url value="/users/${u.cf}/edit" />">+</a>]</td>
					<td>[<a href="<c:url value="/users/${u.cf}/delete" />">X</a>]</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<a href="<c:url value="/users/add" />">Aggiungi nuovo utente</a>
		<a href="<c:url value="/users/link/choose" />">Modifica ruolo utenti</a>
	</c:otherwise>
</c:choose>