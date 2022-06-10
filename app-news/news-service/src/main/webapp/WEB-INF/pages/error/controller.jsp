<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<!DOCTYPE>
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			</head>

			<body>
				<table style="margin: 0 auto; border-spacing: 20px; font-size: 20px;">
					<tr>
						<td style="" colspan="2">
							<spring:message code="exception.header" />
						</td>
					</tr>
					<tr>
						<td style="font-weight: bold;">
							<spring:message code="error.requestUrl" /> :
						</td>
						<td style="color: red;">
							<c:out value="${url}" />
						</td>
					</tr>
					<tr>
						<td style="font-weight: bold;">
							<spring:message code="exception.message" /> :
						</td>
						<td style="color: red;">
							<c:out value="${exception.message }" />
						</td>
					</tr>
				</table>
				<c:forEach items="${exception.stackTrace}" var="ste">
					<c:out value="${ste}" />
					<br />
				</c:forEach>
			</body>

			</html>