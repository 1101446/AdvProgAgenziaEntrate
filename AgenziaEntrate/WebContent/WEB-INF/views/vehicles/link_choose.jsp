<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<form method="POST" action="<c:url value="/vehicles/link" />">
	<table>
		<c:choose>
			<c:when test="${update}">
				<tr>
					<td>Veicolo</td>
					<td>${vehicle.brand} - ${vehicle.model} - ${vehicle.vehicleRegistration}</td>
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
            		<td><input name="vehicle" type="hidden" value="${vehicle.id}"/></td>
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
					<td>Veicolo</td>
					<td>
						<select class="form-control" name="vehicle">
						<c:forEach items="${vehicles}" var="v">
							<option value="${v.id}">${v.brand} - ${v.model} - ${v.vehicleRegistration}</option> 
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
					<td><label name="endOfYear">Data Saldo</label></td>
					<td><input class="form-control" name="endOfYear" type="date" value="31/12/00"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td><label path="price">Prezzo</label></td>
			<td><input class="form-control" name="price" type="number" min="0" value="${userVehicle.price}" /></td>
		</tr>
		<tr>
            <td><input name="update" type="hidden" value="${update}"/></td>
        </tr>
	</table>
	<input type="submit" value="Salva"/>
</form>