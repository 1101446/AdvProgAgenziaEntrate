<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<form method="POST" action="<c:url value="/realestates/link" />">
	<table>
		<c:choose>
			<c:when test="${update}">
				<tr>
					<td>Immobile</td>
					<td>${realEstate.address} CAP ${realEstate.CAP} Paese ${realEstate.country}</td>
				</tr>
				<tr>
					<td>Utente</td>
					<td>${user.cf} - ${user.firstName} ${user.secondName}</td>
				</tr>
				<tr>
					<td>Data Saldo</td>
					<td>${endOfYear}</td>
				</tr>
				<tr>
            		<td><input name="realEstate" type="hidden" value="${realEstate.id}"/></td>
       			</tr>
				<tr>
           			<td><input name="user" type="hidden" value="${user.cf}"/></td>
        		</tr>
				<tr>
					<td><input name="endOfYear" type="hidden" value="${endOfYear}" /></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>Immobile</td>
					<td>
						<select class="form-control" name="realEstate">
							<c:forEach items="${realEstates}" var="re">
								<option value="${re.id}">${re.address} CAP ${re.CAP} Paese ${re.country}</option> 
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Utente</td>
					<td>
						<select class="form-control" name="user">
						<c:forEach items="${users}" var="u">
							<option value="${u.cf}">${u.cf} - ${u.firstName} ${u.secondName}</option> 
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label name="endOfYear" >Data Saldo</label></td>
					<td><input name="endOfYear" class="form-control" value="31/12/00"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td><label path="price">Prezzo</label></td>
			<td><input name="price" class="form-control" type="number" min="0" value="${userRealEstate.price}"/></td>
		</tr>
		<tr>
            <td><input name="update" type="hidden" value="${update}"/></td>
        </tr>
	</table>
	<input type="submit" value="Salva"/>
</form>