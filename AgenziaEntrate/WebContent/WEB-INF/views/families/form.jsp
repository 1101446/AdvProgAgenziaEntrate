<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/families/save" var="action_url" />
        <form method="POST" action="${action_url}">
        	<table>
				<tr>
					<td><label name="id">Id</td>
					<td><input name="id" type="number" /></td>
				</tr>
				<tr>
					<td><label name="userId">Utente</td>
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
                   	<td><label name="hierarchy">Gerarchia</label></td>
                   	<td><input name="hierarchy" type="text" /></td>
               </tr>
               <tr>
                    <td><label name="houseHolder">Capo famiglia</label></td>
                    <td><input name="houseHolder" type="text"/></td>
               </tr>
			   <tr>
                    <td><input type="submit" value="Aggiungi"/></td>
                </tr>
            </table>
		</form>