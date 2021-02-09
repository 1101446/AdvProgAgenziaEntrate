<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<h4>Elenco utenti: ${numUsers}</h4> 

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>
<table class="text-center">
	<c:choose>
		<c:when test="${isUser}">
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
				<td>${profile.password}</td>
				<td>[<a href="<c:url value="/users/${u.cf}/edit" />">+</a>]</td>
			</tr>
		</c:when>
		<c:otherwise>
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
		</c:otherwise>
	</c:choose>
</table>
<hr/>
<a href="<c:url value="/users/add" />">Aggiungi nuovo utente</a>
<a href="<c:url value="/users/link/choose" />">Modifica ruolo utenti</a>