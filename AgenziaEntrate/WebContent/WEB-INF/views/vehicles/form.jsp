<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/vehicles/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="vehicle">
        	<table>
				<tr>
                   	<td><form:label path="brand">Marca</form:label></td>
                   	<td><form:input path="brand"/></td>
               </tr>
               <tr>
                    <td><form:label path="model">Modello</form:label></td>
                    <td><form:input path="model"/></td>
               </tr>
				
				<tr>
                    <td><form:label path="vehicleRegistration">Targa</form:label></td>
                    <td><form:input path="vehicleRegistration"/></td>
                </tr>
				<tr>
					<td><form:hidden path="id" /></td>
				</tr>
				<tr>
					<td><label path="userId">Utente</label></td>
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