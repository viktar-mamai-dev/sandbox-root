<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		<!DOCTYPE>
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		</head>

		<body>
			<div style="margin: 0 auto; margin-top: 30px; color: red; width: 50%; margin-bottom: 30px;">
				<h2>
					<spring:message code="error.status.404" />
				</h2>
				<form method="GET" action="news/page/1">
					<input type="submit" style="cursor: pointer; padding: 10px; background-color: #D4D4D4"
						value='<spring:message code="button.back.main"/>' />
				</form>
			</div>
		</body>

		</html>