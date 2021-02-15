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
					<c:choose>
						<c:when test="${update}" >
							<td>${family.id}</td>
							<td><input name="id" type="hidden" value="${family.id}"/></td>
						</c:when>
						<c:otherwise>
							<td><input name="id" class="form-control" type="number" min="1"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td><label name="userId">Utente</td>
					<c:choose>
						<c:when test="${update}" >
							<td>${family.user.firstName} ${family.user.secondName}</td>
							<td><input type="hidden" name="userId" value="${family.user.cf}"></td>
						</c:when>
						<c:otherwise>
							<td>
								<select name="userId">
									<c:forEach items="${users}" var="u">
										<option value="${u.cf}">${u.cf} - ${u.firstName} ${u.secondName}</option>
									</c:forEach>
								</select>
							</td>
						</c:otherwise>
					</c:choose>
                </tr>
				<tr>
                   	<td><label name="hierarchy">Gerarchia</label></td>
					<td>
						<select name="hierarchy">
							<option value="Genitore">Genitore</option>
							<option value="Figlio">Figlio</option>
						</select>
					</td>
               </tr>
               <tr>
                    <td><label name="houseHolder">Capo famiglia</label></td>
                    <td><input name="houseHolder" class="form-control" type="text" value="${family.houseHolder}"/></td>
               </tr>
				<tr>
                    <td><input name="update" type="hidden" value="${update}"/></td>
               </tr>
			   <tr>
                    <td><input type="submit" value="Aggiungi"/></td>
                </tr>
            </table>
		</form>