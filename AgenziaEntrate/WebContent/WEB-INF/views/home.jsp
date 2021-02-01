<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<p>Adesso sono le: ${serverTime}.</p>
<ul>
	<li>login: <a href="<c:url value="/user/login/" />">Login</a>
	<li>Registrazione: <a href="<c:url value="/user/login/" />">registrati</a> 
</ul>
