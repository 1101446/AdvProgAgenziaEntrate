<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('UTENTE')" var="isUser" />    
		<c:url value="/users/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="user">
        	<table>
				<tr>
					<td><form:label path="firstName">Nome:</form:label></td>
					<td><form:input path="firstName" class="form-control" placeholder="Nome" type='text'/></td>
				</tr>
				<tr>
					<td><form:label path="secondName">Cognome:</form:label></td>
					<td><form:input path="secondName" class="form-control" placeholder="Cognome" type='text'/></td>
				</tr>
				<tr>	
					<td><form:label path="cf">Codice fiscale:</form:label></td>
					<td><form:input path="cf" class="form-control" placeholder="Codice fiscale" type='text'  /></td>
				</tr>
				<tr>	
					<td><form:label path="birthD">Data di nascita:</form:label></td>
					<td><form:input path="birthD" class="form-control" /></td>
				</tr>
				<tr>	
					<td><form:label path="email">Email:</form:label></td>
					<td><form:input path="email" class="form-control" placeholder="Email" type='email'/></td>
				</tr>
				<tr>	
					<td><form:label path="password" >Password:</form:label></td>
					<td><form:input path="password" class="form-control" type="password" /></td>
				</tr>
				<tr>	
					<td><label>Disabilità:</label></td>
					<td>
						<input type="radio" id="si" name="isHandicap" value="true" />
  						<label for="si">Si</label>
  						<input type="radio" id="no" name="isHandicap" value="false" />
  						<label for="no">No</label>
  					</td>
				</tr>
				<tr>
				<c:choose>
					<c:when test="${isUser}">
						<td><input type="hidden" name="accessId" value="${user.access.getId()}"/></td>
					</c:when>
					<c:otherwise>
						<td><label>Permesso</label></td>
						<td>
							<select name="accessId" >
							<c:forEach items="${allAccess}" var="a">
								<option value="${a.id}">${a.roleName}</option> 
							</c:forEach>
							</select>
						</td>
					</c:otherwise>
				</c:choose>
				</tr>
				<tr>
                    <td><input type="submit" value="Salva"/></td>
                </tr>
            </table>
		</form:form>