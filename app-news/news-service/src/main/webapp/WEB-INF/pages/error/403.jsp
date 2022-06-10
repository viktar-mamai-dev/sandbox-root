<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
			<!DOCTYPE>
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			</head>

			<body>
				<div style="margin: 0 auto; margin-top: 30px; width: 500px; text-align: center; margin-bottom: 30px;">
					<h2 style="color: red;">
						<spring:message code="error.status.403" />
					</h2>
					<table style="margin: 0 auto; border-spacing: 20px;">
						<tr>
							<c:choose>
								<c:when test="${empty username}">
									<td colspan="2">
										<h2>
											<spring:message code="error.role" />
										</h2>
									</td>
								</c:when>
								<c:otherwise>
									<td>
										<spring:message code="label.user.username" /> :
									</td>
									<td class="label-403">
										<c:out value="${username}"></c:out>
									</td>
						<tr>
							<td>
								<spring:message code="label.user.role" /> :
							</td>
							<td class="label-403">
								<c:out value="${role}"></c:out>
							</td>
						</tr>
						</c:otherwise>
						</c:choose>
						</tr>
					</table>
				</div>
			</body>

			</html>