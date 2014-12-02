<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<label>Login</label>

<form:form commandName="loginForm" id="loginform" method="post"
			action="dologin">
<form:errors></form:errors>			
<form:errors path="userid"/>
<form:input path="userid" maxlength="50"/>
<form:errors path="password"/>
<form:password path="password" maxlength="20"/>
<button  type="submit">Login</button>
</form:form>



