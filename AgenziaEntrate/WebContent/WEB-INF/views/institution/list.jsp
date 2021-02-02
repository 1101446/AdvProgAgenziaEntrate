<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
Elenco conti correnti: ${numInstruments}. 

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>

<h1>Elenco</h1>
<table>
	<thead>
		<td>IBAN</td>
		<td>Ente</td>
		<td>Data saldo</td>
		<td>Saldo</td>
		<td>Modifica</td>
		<td>Elimina</td>
		<td>Intestatari</td>
	</thead>
	<c:forEach items="${bankAccounts}" var="ba">
		<tr>
			<td>${ba.IBAN}</td>
			<td>${ba.bankName}</td>
			<td>${ba.billDate}</td>
			<td>${ba.balance}</td>
			<td>
				<ul>
					<c:forEach owner="${ba.owner}" var="o">
						<li>${o.firstName} ${o.secondName} [<a href="<c:url value="/institution/${ba.IBAN}/${ba.billDate}/user/${o.cf}/unlink/" />?next=/institution/list/">-/-</a>]</li>
					</c:forEach>
				</ul>
			</td>
			<td>[<a href="<c:url value="/institution/${ba.IBAN}/${ba.billDate}/edit/" />">+</a>]</td>
			<td>[<a href="<c:url value="/institution/${ba.IBAN}/${ba.billDate}/delete/" />">X</a>]</td>
		</tr>
	</c:forEach>
</table>
<hr/>
<a href="<c:url value="/institution/add" />">Aggiungi conto corrente</a>
<!-- Chiedere come implemetare parte della form per inserire utenti -->