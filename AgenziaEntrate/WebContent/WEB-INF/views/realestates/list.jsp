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
		<h4>Elenco immobili: ${numRealEstates}</h4>
		<table class="text-center">
			<thead>
				<td>ID</td>
				<td>Indirizzo</td>
				<td>CAP</td>
				<td>Paese</td>
				<td>Modifica</td>
				<td>Elimina</td>
			</thead>
			<c:forEach items="${realEstates}" var="re">
				<tr>
					<td>${re.id}</td>
					<td>${re.address}</td>
					<td>${re.CAP}</td>
					<td>${re.country}</td>
					<td>[<a href="<c:url value="/realestates/${re.id}/edit" />">+</a>]</td>
					<td>[<a href="<c:url value="/realestates/${re.id}/delete" />">X</a>]</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<h4>Immobili associati: ${numUserRealEstates}</h4>
		<table class="text-center">
			<thead>
				<td>Utente</td>
				<td>Immobile</td>
				<td>Data Saldo</td>
				<td>Valore</td>
				<td>Modifica</td>
				<td>Elimina</td>
			</thead>
			<c:forEach items="${userRealEstates}" var="ure">
				<tr>
					<td>${ure.user.cf}</td>
					<td>${ure.realEstate.address} ${ure.realEstate.CAP} ${ure.realEstate.country}</td>
					<td>${ure.endOfYear}</td>
					<td>${ure.price}</td>
					<td>[<a href="<c:url value="/realestates/${ure.realEstate.id}/user/${ure.user.cf}/endOfYear/${ure.endOfYear}/edit" />">+</a>]</td>
					<td>[<a href="<c:url value="/realestates/${ure.realEstate.id}/user/${ure.user.cf}/endOfYear/${ure.endOfYear}/unlink" />">X</a>]</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<a href="<c:url value="/realestates/add" />">Aggiungi nuovo immobile</a>
		<a href="<c:url value="/realestates/link/choose" />">Aggiungi intestatario</a>
	</c:otherwise>
</c:choose>