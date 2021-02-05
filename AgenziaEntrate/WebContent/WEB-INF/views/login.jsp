<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>
<div class="text-center">
<form class="form-signin" name='login' action="<c:url value="/login" />" method='POST'>
  	<h1 class="h3 mb-3 font-weight-normal">Accedi</h1>
  	<label for="username" class="sr-only">Indirizzo Email</label>
  	<input type="email" id="username" name="username" class="form-control" placeholder="Indirizzo email" required autofocus>
  	<label for="password" class="sr-only">Password</label>
  	<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
 	<button class="btn btn-lg btn-primary btn-block" type="submit">Accedi</button>
<hr>
<h2> Registrazione </h2>

<form class="form-signin" name='registration' action="<c:url value="/registration" />" method='POST'>
		<label class="sr-only">Nome:</label>
		<input class="form-control" placeholder="Nome" type='text' name='firstName' value=''>
		<label class="sr-only">Cognome:</label>
		<input class="form-control" placeholder="Cognome" type='text' name='secondName' value=''>
		<label class="sr-only">Codice fiscale:</label>
		<input class="form-control" placeholder="Codice fiscale" type='text' name='cf' value=''>
		<label class="sr-only">Data di nascita:</label>
		<input class="form-control" type='text' name='birthD' value=''>
		<label class="sr-only">Email:</label>
		<input class="form-control" placeholder="Email" type='email' name='email' value=''>
		<label class="sr-only">Password:</label>
		<input class="form-control" type='password' name='password' value=''>
		<label class="sr-only">Disabilità:</label>
			<input type="radio" id="si" name="handicap" value="true">
  			<label for="si">Si</label><br>
  			<input type="radio" id="no" name="handicap" value="false">
  			<label for="no">No</label><br>
		<input type='hidden' name='access' value="UTENTE"/>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Registrati</button>
</form>
</div>