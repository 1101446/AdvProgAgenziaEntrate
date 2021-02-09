<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/isees/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="ISEE">
        	<table>
             	<tr>
                   	<td><form:label path="yearOfValidity">Anno Validità</form:label></td> 
                   	<td><form:input path="yearOfValidity"/></td>
               	</tr>
				<tr>
                   	<td><form:label path="valueOfISEE">Valore</form:label></td>
                   	<td><form:input path="valueOfISEE" /></td>
               </tr>
				<tr>
					<td><label path="userId">Assegna ISEE a utente</label></td>
					<td>
						<select name="userId">
							<option value="noUser" selected>----------</option>
							<c:forEach items="${users}" var="u">
								<option value="${u.cf}">${u.cf} - ${u.firstName} ${u.secondName}</option> 
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
                   	<td><form:hidden path="id" /></td>
               </tr>
                <tr>
                    <td><input type="submit" value="Aggiungi"/></td>
                </tr>
            </table>
		</form:form>