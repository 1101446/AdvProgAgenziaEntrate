<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/institution/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="bankAccount">
        	<table>
				<c:if test="${empty bankAccount.IBAN }">
               		<tr>
                    	<td><form:label path="IBAN">IBAN</form:label></td>
                    	<td><form:input path="IBAN"/></td>
                	</tr>
				</c:if>
               <tr>
                    <td><form:label path="bankName">Ente di credito</form:label></td>
                    <td><form:input path="bankName"/></td>
               </tr>
				<tr>
                    <td><form:label path="billDate">Data Saldo</form:label></td>
                    <td><form:input path="billDate"/></td>
                </tr>
				<tr>
                    <td><form:label path="balance">Saldo</form:label></td>
                    <td><form:input path="balance"/></td>
                </tr>
				<tr>
					<td><form:label path="user_id"/></td>
					<td><form:select path="user_id"/></td>
				</tr>
				<tr>
					<td><form:hidden path="IBAN" /></td>
				</tr>
				
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
		</form:form>