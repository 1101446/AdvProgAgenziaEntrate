<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/institution/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="bankAccount">
        	<table>
             	<tr>
                   	<td><form:label path="IBAN">IBAN</form:label></td>
                   	<td><form:input path="IBAN" class="form-control" /></td>
               	</tr>
				<tr>
                   	<td><form:label path="billDate">Data Saldo</form:label></td>
                   	<td><form:input path="billDate" class="form-control" placeholder="gg/mm/aa" /></td>
               </tr>
               <tr>
                    <td><form:label path="bankName">Ente di credito</form:label></td>
                    <td><form:input path="bankName" class="form-control" /></td>
               </tr>			
				<tr>
                    <td><form:label path="balance">Saldo</form:label></td>
                    <td><form:input path="balance" class="form-control" /></td>
                </tr>
				<tr>
					<td><label path="userId">Assegna conto corrente a utente</label></td>
					<td>
						<select class="form-control" name="userId">
							<option value="noUser" selected>----------</option>
							<c:forEach items="${users}" var="u">
								<option value="${u.cf}">${u.cf} - ${u.firstName} ${u.secondName}</option> 
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
                    <td><input name="update" type="hidden" value="${update}"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Aggiungi"/></td>
                </tr>
            </table>
		</form:form>