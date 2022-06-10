<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<!DOCTYPE html>
			<fmt:setLocale value="${language }" />
			<fmt:setBundle basename="pagecontent" />
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
				<title>
					<fmt:message key="title.register" />
				</title>
				<link rel="stylesheet" type="text/css" href="css/form.css">
			</head>

			<body>
				<jsp:include page="../common/header.jsp" />

				<form method="POST" action="controller" class="register-frm">
					<input type="hidden" name="command" value="register" />
					<h1>
						<fmt:message key="header.form.register" />
					</h1>
					<h4>
						<fmt:message key="header.form.fill" />
					</h4>
					<table>
						<tr>
							<td>
								<fmt:message key="label.user.username" />
							</td>
							<td><input type="text" name="userName" required="required" value="${user.userName }"
									title="<fmt:message key=" error.empty" />"></td>
							<td class="error-cell">${userNameError }</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="label.user.login" />
							</td>
							<td><input type="text" name="login" required="required" title="<fmt:message key="
									error.empty" />" value="${user.login }"></td>
							<td class="error-cell">${loginError }</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="label.user.password" />
							</td>
							<td><input type="password" name="password" required="required" title="<fmt:message key="
									error.password.input" />"></td>
							<td class="error-cell">${passwordError }</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="label.user.password.confirm" />
							</td>
							<td><input type="password" name="passwordConfirm" required="required"
									title="<fmt:message key=" error.password.confirm" />"></td>
							<td class="error-cell">${passwordConfirmError }</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="label.user.age" />
							</td>
							<td><select name="age" class="age">
									<c:forEach var="selectAge" begin="18" end="100">
										<option value="${selectAge}" <c:if test="${selectAge eq user.age }">
											selected="selected" </c:if>>${selectAge }
										</option>
									</c:forEach>
								</select></td>
							<td class="error-cell">${ageError }</td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="register-btn" value="<fmt:message key="
									button.register" />" />
						</tr>
					</table>

				</form>


				<jsp:include page="../common/footer.jsp" />

			</body>

			</html>