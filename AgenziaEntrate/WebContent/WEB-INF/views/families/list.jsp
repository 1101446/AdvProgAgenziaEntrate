<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<h4>Elenco famiglie: ${numFamilies}</h4> 

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>
<table class="text-center">
	<thead>
		<td>ID</td>
		<td>Nome ruolo</td>
		<td>Gerarchia</td>
		<td>Capo Famiglia</td>
		<td>Modifica</td>
		<td>Elimina</td>
	</thead>
	<c:forEach items="${allFamilies}" var="f">
		<tr>
			<td>${f.id}</td>
			<td>${f.user.firstName} ${f.user.secondName}</td>
			<td>${f.hierarchy}</td>
			<td>${f.houseHolder}</td>
			<td>[<a href="<c:url value="/families/${f.id}/${f.user.cf}/edit/" />">+</a>]</td>
			<td>[<a href="<c:url value="/families/${f.id}/${f.user.cf}/delete/" />">X</a>]</td>
		</tr>
	</c:forEach>
</table>
<hr/>
<a href="<c:url value="/families/add" />">Aggiungi nuova relazione</a>