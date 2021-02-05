<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
<sec:authorize access="hasRole('ENTE_CREDITO')" var="isBank" />
<sec:authorize access="hasRole('CATASTO')" var="isRealEstate" />
<sec:authorize access="hasRole('MOTORIZZAZIONE')" var="isVehicle" />
<sec:authorize access="hasRole('ENTRATE')" var="isStaff" />
<sec:authorize access="hasRole('USER')" var="isUser" />
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
  	<c:if test="${isAdmin}">
  		<a class="navbar-brand" href="<c:url value="/access/list" />">Permessi</a>
  	</c:if>
  	<c:if test="${isAdmin} || ${isBank}">
  		<a class="navbar-brand" href="<c:url value="/institution/list" />">Conti Correnti</a>
  	</c:if>
  	<c:if test="${isAdmin} || ${isRealEstate}">
  		<a class="navbar-brand" href="<c:url value="/realestates/list" />">Immobili</a>
  	</c:if>
  	<c:if test="${isAdmin} || ${isVehicle}">
  		<a class="navbar-brand" href="<c:url value="/vehicles/list" />">Veicoli</a>
  	</c:if>
  	<a class="navbar-brand" href="<c:url value="/users/list" />">Utenti</a>
  	<a class="navbar-brand" href="<c:url value="/logout" />">Logout</a>
  </c:if>
</nav> 
   
<hr/>