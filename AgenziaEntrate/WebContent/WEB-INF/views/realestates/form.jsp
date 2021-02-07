<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/realestates/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="realEstate">
        	<table>
             	<tr>
                   	<td><form:label path="address">Indirizzo</form:label></td>
                   	<td><form:input path="address"/></td>
               	</tr>
				<tr>
                   	<td><form:label path="CAP">CAP</form:label></td>
                   	<td><form:input path="CAP"/></td>
               	</tr>
               	<tr>
                    <td><form:label path="country">Stato</form:label></td>
                    <td><form:input path="country"/></td>
               	</tr>
			   	<tr>
			</table>
			<table>
				<tr>
					<td><label path="userId">Utente</label></td>
					<td>	
						<select name="user">
								<option value="">----------</option>
							<c:forEach items="${users}" var="u">
								<option value="${u}">${u.cf} - ${u.firstName} ${u.secondName})</option> 
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><input type="hidden" name="realEstate" value="${realEstate.id}" /></td>
				</tr>
				<tr>
					<td><label name="endOfYear">Data Saldo</label></td>
                   	<td><input type="date"/></td>
				</tr>
				<tr>
					<td><label name="price">Prezzo</label></td>
                   	<td><input type="num" /></td>
				</tr>
				<tr>
					<td><form:hidden path="id" /></td>
				</tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
		</form:form>