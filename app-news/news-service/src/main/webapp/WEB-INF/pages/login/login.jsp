<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
			<!DOCTYPE>
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			</head>

			<body>
				<c:url value="/j_spring_security_check" var="loginUrl" />
				<form action="${loginUrl}" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<table style="margin: 0 auto; margin-top: 200px; margin-bottom: 20px;">
						<tr>
							<td><label for="username">
									<spring:message code="label.user.username" />
								</label></td>
							<td><input type="text" id="username" name="j_username" required="required" autofocus></td>
						</tr>
						<tr>
							<td><label for="username">
									<spring:message code="label.user.password" />
								</label></td>
							<td><input type="password" id="password" name="j_password" required></td>
						</tr>
						<tr>
							<td colspan="2"><input style="float: right;" class="submit-btn" type="submit" value="
								<spring:message code="button.login" />"></td>
						</tr>
					</table>

					<div style="height: 20px; margin: 0 auto; margin-bottom: 70px; font-size: 20px; 
					color: red; border: 1 px solid red; text-align: center;">
						<c:if test="${not empty badCredentials}">
							<span>${badCredentials}</span>
						</c:if>
						<c:if test="${not empty logoutMessage}">
							<span>${logoutMessage}</span>
						</c:if>
					</div>
				</form>

			</body>

			</html>