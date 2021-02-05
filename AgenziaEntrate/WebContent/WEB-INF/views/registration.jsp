<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2> Registrazione </h2>

<div>
<c:url value="/registration" var="action_url" />
<form:form method='POST' action="<c:url value='${action_url}'/>" modelAttribute="newUser">
		<form:label path="firstName" class="sr-only">Nome:</form:label>
		<form:input path="firstName" class="form-control" placeholder="Nome" type='text'/>
		<form:label path="secondName" class="sr-only">Cognome:</form:label>
		<form:input path="secondName" class="form-control" placeholder="Cognome" type='text'/>
		<form:label path="cf" class="sr-only">Codice fiscale:</form:label>
		<form:input path="cf" class="form-control" placeholder="Codice fiscale" type='text'  />
		<form:label path="birthD" class="sr-only">Data di nascita:</form:label>
		<form:input path="birthD" class="form-control" type='date' />
		<form:label path="email" class="sr-only">Email:</form:label>
		<form:input path="email" class="form-control" placeholder="Email" type='email'/>
		<form:label path="password" class="sr-only">Password:</form:label>
		<form:input path="password" class="form-control" type='password' />
		<form:label path="isHandicap" class="sr-only">Disabilità:</form:label>
			<form:input path="isHandicap" type="radio" id="si" name="handicap" value="true" />
  			<form:label path="isHandicap" for="si">Si</form:label>
  			<form:input path="isHandicap" type="radio" id="no" name="handicap" value="false" />
  			<form:label path="isHandicap" for="no">No</form:label>
		<form:input path="access" type='hidden' name='access' value="${userAccess}"/>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Registrati</button>
</form:form>
</div>