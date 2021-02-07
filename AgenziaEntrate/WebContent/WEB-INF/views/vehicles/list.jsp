<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
Elenco veicoli: ${numVehicles}. 

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>
<table class="text-center">
	<thead>
		<td>ID</td>
		<td>Marca</td>
		<td>Modello</td>
		<td>Targa</td>
		<td>Modifica</td>
		<td>Elimina</td>
	</thead>
	<c:forEach items="${vehicles}" var="v">
		<tr>
			<td>${v.id}</td>
			<td>${v.brand}</td>
			<td>${v.model}</td>
			<td>${v.vehicleRegistration}</td>
			<td>[<a href="<c:url value="/vehicles/${v.id}/edit/" />">+</a>]</td>
			<td>[<a href="<c:url value="/vehicles/${v.id}/delete/" />">X</a>]</td>
		</tr>
	</c:forEach>
</table>
<hr/>
<a href="<c:url value="/vehicles/add" />">Aggiungi nuovo veicolo</a>
<a href="<c:url value="/vehicles/link/choose" />">Aggiungi intestatario</a>