<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/roles/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="access">
        	<table>
				<tr>
					<td><form:label path="firstName" class="sr-only">Nome:</form:label></td>
					<td><form:input path="firstName" class="form-control" placeholder="Nome" type='text'/></td>
				</tr>
				<tr>
					<td><form:label path="secondName" class="sr-only">Cognome:</form:label></td>
					<td><form:input path="secondName" class="form-control" placeholder="Cognome" type='text'/></td>
				</tr>
				<tr>	
					<td><form:label path="cf" class="sr-only">Codice fiscale:</form:label><td>
					<td><form:input path="cf" class="form-control" placeholder="Codice fiscale" type='text'  /></td>
				</tr>
				<tr>	
					<td><form:label path="birthD" class="sr-only">Data di nascita:</form:label></td>
					<td><form:input path="birthD" class="form-control" type='date' /></td>
				</tr>
				<tr>	
					<td><form:label path="email" class="sr-only">Email:</form:label><td>
					<td><form:input path="email" class="form-control" placeholder="Email" type='email'/></td>
				</tr>
				<tr>	
					<td><form:label path="password" class="sr-only">Password:</form:label><td>
					<td><form:input path="password" class="form-control" type='password' /></td>
				</tr>
				<tr>	
					<td><form:label path="handicap" class="sr-only">Disabilità:</form:label></td>
					<td><form:input path="handicap" /></td>
				</tr>
            </table>
		</form:form>