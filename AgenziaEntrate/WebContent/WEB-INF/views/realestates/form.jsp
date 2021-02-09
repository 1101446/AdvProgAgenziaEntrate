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
					<td><form:hidden path="id" /></td>
				</tr>
			</table>
		<h3>Inserimento proprietario</h3>
			<table>
				<tr>
					<td><label name="userId">Assegna a utente</label></td>
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
					<td><label name="endOfYear">Data Saldo</label></td>
                   	<td><input name="endOfYear" type="date" value="01/01/90" /></td>
				</tr>
				<tr>
					<td><label name="price">Valore</label></td>
                   	<td><input name="price" type="number" value="0" /></td>
				</tr>
                <tr>
                    <td><input type="submit" value="Salva"/></td>
                </tr>
            </table>
		</form:form>