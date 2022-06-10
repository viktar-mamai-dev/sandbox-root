<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<!DOCTYPE html>
		<fmt:setLocale value="${language }" />
		<fmt:setBundle basename="pagecontent" />
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>
				<fmt:message key="title.login" />
			</title>
			<link rel="stylesheet" type="text/css" href="css/form.css">
		</head>

		<body>
			<jsp:include page="../common/header.jsp" />

			<form method="POST" action="controller" class="register-frm">
				<h1>
					<fmt:message key="header.form.login" />
				</h1>

				<input type="hidden" name="command" value="login" />
				<table>
					<tr>
						<td>
							<fmt:message key="label.user.login" />
						</td>
						<td><input type="text" name="login" value="${login }" required="required"></td>

					</tr>
					<tr>
						<td>
							<fmt:message key="label.user.password" />
						</td>
						<td><input type="password" name="password" required="required"></td>
					</tr>
					<tr>
						<td colspan="2" class="error-cell">${loginError }</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="register-btn" value="<fmt:message key="button.login" />">
					</tr>
				</table>
			</form>
			<jsp:include page="../common/footer.jsp" />

		</body>

		</html>