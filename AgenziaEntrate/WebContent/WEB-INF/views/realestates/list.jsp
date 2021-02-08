<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
Elenco immobili: ${numRealEstates}. 

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>
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
<a href="<c:url value="/realestates/add" />">Aggiungi nuovo immobile</a>
<a href="<c:url value="/realestates/link/choose" />">Aggiungi intestatario</a>