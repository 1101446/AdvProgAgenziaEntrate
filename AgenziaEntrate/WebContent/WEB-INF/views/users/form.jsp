<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('UTENTE')" var="isUser" />    
	<c:url value="/users/save" var="action_url" />
    <form:form method="POST" action="${action_url}" modelAttribute="user">
	<c:choose>
		<c:when test="${isUser}">
			<sec:authentication property="principal.username" var="username"/>
			<c:choose>
				<c:when test="${user.email == username}">
        			<table>
						<tr>	
							<td>Nome</td>
							<td>${user.firstName}</td>
						</tr>
						<tr>
							<td>Cognome</td>
							<td>${user.secondName}</td>
						</tr>
						<tr>	
							<td>Codice Fiscale</td>
							<td>${user.cf}</td>
						</tr>
						<tr>	
							<td><form:label path="birthD">Data di Nacita:</form:label></td>
							<td><form:input path="birthD" class="form-control"/></td>
						</tr>
						<tr>	
							<td>Email</td>
							<td>${user.email}</td>
						</tr>
						<tr>	
							<td><form:label path="password" >Password:</form:label></td>
							<td><form:input path="password" class="form-control" type="password" /></td>
						</tr>
						<tr>	
							<td><label>Disabilità:</label></td>
							<td>
								<input type="radio" id="si" name="isHandicap" value="true" />
  								<label for="si">Si</label>
  								<input type="radio" id="no" name="isHandicap" value="false" />
  								<label for="no">No</label>
  							</td>
						</tr>
						<tr>
							<td><form:hidden path="firstName" /></td>
						</tr>
						<tr>
							<td><form:hidden path="secondName" /></td>
						</tr>
						<tr>
							<td><form:hidden path="cf" /></td>
						</tr>
						<tr>	
							<td><form:hidden path="email" /></td>
						</tr>
						<tr>
							<td><input type="hidden" name="accessId" value="${user.access.id}"/></td>
						</tr>
						<tr>
							<td><input type="hidden" name="updateUser" value="true" /></td>
						</tr>
						<tr>
              	      		<td><input type="submit" value="Salva" /></td>
                		</tr>
            		</table>
				</c:when>
				<c:otherwise>
					<table>
						<tr>	
							<td>Nome</td>
							<td>Ti ho</td>
						</tr>
						<tr>
							<td>Cognome</td>
							<td>scoperto hacker cattivo</td>
						</tr>
						<tr>	
							<td>Codice Fiscale</td>
							<td>Sei una brutta persona se fai queste cose</td>
						</tr>
						<tr>	
							<td>Data di nascita</td>
							<td>Non dovresti averla</td>
						</tr>
						<tr>	
							<td>Email cosi ti posso rintracciare per bene</td>
							<td><input class="form-control "type="email"/></td>
						</tr>
						<tr>	
							<td>Password</td>
							<td><input class="form-control" type="password" /></td>
						</tr>
						<tr>	
							<td><label>Sei una brutta persona?</label></td>
							<td>
								<input type="radio" id="si" value="true" />
  								<label for="si">Si</label>
  								<input type="radio" id="no" value="true" />
  								<label for="no">Si</label>
  							</td>
						</tr>
						<tr>
							<td>Dai su sto scherzando, vada avanti, non cincischi</td>
						</tr>
						<tr>
							<td>Grazie mille per essere passato</td>
							<td><a class="navbar-brand" href="https://www.youtube.com/watch?v=aS7RXAsq3NI" />DISTRUGGI IL SITO</a></td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<table>
				<c:choose>
					<c:when test="${user.cf == null}">
						<tr>	
							<td><form:label path="cf">Codice fiscale:</form:label></td>
							<td><form:input path="cf" class="form-control" placeholder="Codice fiscale" type='text'  /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>	
							<td>Codice fiscale:</td>
							<td>${user.cf}</td>
							<td><form:hidden path="cf" /></td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<td><form:label path="firstName">Nome:</form:label></td>
					<td><form:input path="firstName" class="form-control" placeholder="Nome" type='text'/></td>
				</tr>
				<tr>
					<td><form:label path="secondName">Cognome:</form:label></td>
					<td><form:input path="secondName" class="form-control" placeholder="Cognome" type='text'/></td>
				</tr>
				<tr>	
					<td><form:label path="birthD">Data di nascita:</form:label></td>
					<td><form:input path="birthD" class="form-control" /></td>
				</tr>
				<tr>	
					<td><form:label path="email">Email:</form:label></td>
					<td><form:input path="email" class="form-control" placeholder="Email" type='email'/></td>
				</tr>
				<tr>	
					<td><form:label path="password" >Password:</form:label></td>
					<td><form:input path="password" class="form-control" type="password" /></td>
				</tr>
				<tr>	
					<td><label>Disabilità:</label></td>
					<td>
						<input type="radio" id="si" name="isHandicap" value="true" />
  						<label for="si">Si</label>
  						<input type="radio" id="no" name="isHandicap" value="false" />
  						<label for="no">No</label>
  					</td>
				</tr>
				<tr>
					<td><label>Permesso</label></td>
					<td>
						<select class="form-control" name="accessId" >
						<c:forEach items="${allAccess}" var="a">
							<option value="${a.id}">${a.roleName}</option> 
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
                    <td><input type="submit" value="Salva"/></td>
                </tr>
				<tr>
					<td><input type="hidden" name="updateUser" value="false"/></td>
				</tr>
            </table>
		</c:otherwise>
	</c:choose>
</form:form>