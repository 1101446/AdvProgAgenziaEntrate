<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>${appName} - Accesso</h2>

<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>

<form name='login' action="<c:url value="/login" />" method='POST'>
	<table>
		<tr>
			<td>Email:</td>
			<td><input type='text' name='username' value=''></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type='password' name='password' /></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>
	<%--
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         --%>
</form>

<h2> Registrazione </h2>

<form name='registration' action="<c:url value="/registration" />" method='POST'>
	<table>
		<tr>
			<td>Nome:</td>
			<td><input type='text' name='firstName' value=''></td>
		</tr>
		<tr>
			<td>Cognome:</td>
			<td><input type='text' name='secondName' value=''></td>
		</tr>
		<tr>
			<td>Codice Fiscale:</td>
			<td><input type='text' name='cf' value=''></td>
		</tr>
		<tr>
			<td>Data di Nascita:</td>
			<td><input type='date' name='birthD' value=''></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type='text' name='email' value=''></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type='password' name='password' /></td>
		</tr>
		<tr>
			<td>Ripeti password:</td>
			<td><input type='password' name='repeatPassword' /></td>
		</tr>
		<tr>
			<td>Disabilità:</td>
			<td>
				<input type="radio" id="si" name="handicap" value="true">
  				<label for="si">Si</label><br>
  				<input type="radio" id="no" name="handicap" value="false">
  				<label for="no">No</label><br>
  			</td>
		</tr>
		<tr>
			<td><input type='hidden' name='access' value="UTENTE"/></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>
	<%--
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         --%>
</form>