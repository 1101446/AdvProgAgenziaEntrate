<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>
<div class="text-center">
	<form class="form-signin" name='login' action="<c:url value="/login" />" method='POST'>
  		<h1 class="h3 mb-3 font-weight-normal">Accedi</h1>
  		<label for="username" class="sr-only">Indirizzo Email</label>
  		<input type="email" id="username" name="username" class="form-control" placeholder="Indirizzo email" required autofocus>
  		<label for="password" class="sr-only">Password</label>
  		<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
 		<button class="btn btn-lg btn-primary btn-block" type="submit">Accedi</button>
	</form>
	
	<a class="navbar-brand" href="<c:url value="/registration" />">Registrati</a> 
</div>