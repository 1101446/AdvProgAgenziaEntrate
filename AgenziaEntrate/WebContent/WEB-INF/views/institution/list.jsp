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
		<h4>Conti correnti associati</h4>
		<table>
			<thead>
				<td>IBAN</td>
				<td>Ente</td>
				<td>Data saldo</td>
				<td>Saldo</td>
				<td>Intestatari</td>
			</thead>
			<c:forEach items="${profileBankAccounts}" var="ba">
				<tr>
					<td>${ba.bankAccount.IBAN}</td>
					<td>${ba.bankAccount.bankName}</td>
					<td>${ba.bankAccount.billDate}</td>
					<td>${ba.bankAccount.balance}</td>
					<td>
						${ba.user.cf} - ${ba.user.firstName} ${ba.user.secondName}
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<h4>Elenco conti correnti: ${numBankAccounts}</h4>
		<table>
			<thead>
				<td>IBAN</td>
				<td>Ente</td>
				<td>Data saldo</td>
				<td>Saldo</td>
				<td>Modifica</td>
				<td>Elimina</td>
			</thead>
			<c:forEach items="${allBankAccounts}" var="ba">
				<tr>
					<td>${ba.IBAN}</td>
					<td>${ba.bankName}</td>
					<td>${ba.billDate}</td>
					<td>${ba.balance}</td>
					<td>[<a href="<c:url value="/institution/${ba.IBAN}/${ba.billDate}/edit" />">+</a>]</td>
					<td>[<a href="<c:url value="/institution/${ba.IBAN}/${ba.billDate}/delete" />">X</a>]</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<h4>Elenco conti correnti associati: ${numUserBankAccounts}</h4>
		<table>
			<thead>
				<td>IBAN</td>
				<td>Ente</td>
				<td>Data saldo</td>
				<td>Saldo</td>
				<td>Intestatari</td>
			</thead>
			<c:forEach items="${allUserBankAccounts}" var="uba">
				<tr>
					<td>${uba.bankAccount.IBAN}</td>
					<td>${uba.bankAccount.bankName}</td>
					<td>${uba.bankAccount.billDate}</td>
					<td>${uba.bankAccount.balance}</td>
					<td>
						<ul>
							<li>${uba.user.cf} - ${uba.user.firstName} ${uba.user.secondName} [<a href="<c:url value="/institution/${uba.bankAccount.IBAN}/${uba.bankAccount.billDate}/user/${uba.user.cf}/unlink" />">-/-</a>]</li>
						</ul>
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<a href="<c:url value="/institution/add" />">Aggiungi conto corrente</a>
		<a href="<c:url value="/institution/link/choose" />">Aggiungi intestatario</a>
	</c:otherwise>
</c:choose>