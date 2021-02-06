<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/roles/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="access">
        	<table>
				<tr>
                   	<td><form:label path="roleName">Nome Ruolo</form:label></td>
                   	<td><form:input path="roleName"/></td>
               </tr>
               <tr>
                    <td><form:label path="priority">Livello priorità</form:label></td>
                    <td><form:input path="priority"/></td>
               </tr>
				
				<tr>
                    <td><form:label path="description">Descrizione</form:label></td>
                    <td><form:input path="description"/></td>
                </tr>
				<tr>
					<td><form:hidden path="id" /></td>
				</tr>
				<tr>
					<td>
						<select name="userId">
						<option value="">----------</option>
						<c:forEach items="${users}" var="u">
							<option value="${u.cf}">${u.cf} - ${u.firstName} ${u.secondName}</option> 
						</c:forEach>
						</select>
					</td>
                </tr>
				<tr>
                    <td><input type="submit" value="Aggiungi"/></td>
                </tr>
            </table>
		</form:form>