<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
<sec:authorize access="hasRole('ENTE_CREDITO')" var="isBank" />
<sec:authorize access="hasRole('CATASTO')" var="isRealEstate" />
<sec:authorize access="hasRole('MOTORIZZAZIONE')" var="isVehicle" />
<sec:authorize access="hasRole('ENTRATE')" var="isStaff" />
<sec:authorize access="hasRole('UTENTE')" var="isUser" />
<sec:authorize access="isAuthenticated()" var="isAuth" />
<h1>${appName}</h1>
<c:if test="${isAuth}"> Benvenuto/a <sec:authentication property="principal.username" /> </c:if>
 
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="<c:url value="/" />">Home</a>
  <c:if test="${! isAuth}">
  	<a class="navbar-brand" href="<c:url value="/" />">Chi siamo</a> 
  	<a class="navbar-brand" href="<c:url value="/" />">Contatti</a>
  	<a class="navbar-brand" href="<c:url value="/login" />">Login</a>
  	<a class="navbar-brand" href="<c:url value="/registration" />">Registrati</a>
  </c:if>
  <c:if test="${isAuth}">
  	<sec:authentication property="principal.username" var="username"/>
  	<c:choose>  
  		<c:when test="${isAdmin}">
  			<a class="navbar-brand" href="<c:url value="/roles/list" />">Permessi</a>
  			<a class="navbar-brand" href="<c:url value="/institution/list" />">Conti Correnti</a>
  			<a class="navbar-brand" href="<c:url value="/realestates/list" />">Immobili</a>
  			<a class="navbar-brand" href="<c:url value="/vehicles/list" />">Veicoli</a>
  			<a class="navbar-brand" href="<c:url value="/isees/list" />">Cronologia ISEE</a>
  			<a class="navbar-brand" href="<c:url value="/families/list" />">Elenco Famiglie</a>
  			<a class="navbar-brand" href="<c:url value="/users/list" />">Utenti</a>
  		</c:when>
  		<c:when test="${isBank}">
  			<a class="navbar-brand" href="<c:url value="/institution/list" />">Conti Correnti</a>
  		</c:when>
  		<c:when test="${isRealEstate}">
  			<a class="navbar-brand" href="<c:url value="/realestates/list" />">Immobili</a>
  		</c:when>
  		<c:when test="${isVehicle}">
  			<a class="navbar-brand" href="<c:url value="/vehicles/list" />">Veicoli</a>
  		</c:when>
  		<c:when test="${isStaff}">
  			<a class="navbar-brand" href="<c:url value="/isees/list" />">Cronologia ISEE</a>
  			<a class="navbar-brand" href="<c:url value="/users/list" />">Utenti</a>
  		</c:when>
  		<c:when test="${isUser}">
  			<form method="POST" id="userForm" action="<c:url value="/users/profile" />">
  				<input type="hidden" name="email" value="${username}" />
  				<input type="submit" class="navbar-brand" value="Profilo" />
  			</form>
  			<form method="POST" id="userForm" action="<c:url value="/institution/profile" />">
  				<input type="hidden" name="email" value="${username}" />
  				<input type="submit" class="navbar-brand" value="Conti Correnti" />
  			</form>
  			<form method="POST" action="<c:url value="/realestates/profile" />">
  				<input type="hidden" name="email" value="${username}" />
  				<input type="submit" class="navbar-brand" value="Immobili" />
  			</form>
  			<form method="POST" action="<c:url value="/vehicles/profile" />">
  				<input type="hidden" name="email" value="${username}" />
  				<input type="submit" class="navbar-brand" value="Veicoli" />
  			</form>
  			<form method="POST" action="<c:url value="/families/profile" />">
  				<input type="hidden" name="email" value="${username}" />
  				<input type="submit" class="navbar-brand" value="Famiglia" />
  			</form>
  			<form method="POST" action="<c:url value="/isees/profile" />">
  				<input type="hidden" name="email" value="${username}" />
  				<input type="submit" class="navbar-brand" value="Cronologia ISEE" />
  			</form>
  		</c:when>
  		<c:otherwise>
  			<a class="navbar-brand" href="<c:url value="/users/list" />">Utenti</a>
  		</c:otherwise>
  	</c:choose>
  	<a class="navbar-brand" href="<c:url value="/logout" />">Logout</a>
  </c:if>
</nav> 
<hr/>