<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@ taglib prefix="customtags" tagdir="/WEB-INF/tags" %>
				<!DOCTYPE html>
				<fmt:setLocale value="${language }" />
				<fmt:setBundle basename="pagecontent" />
				<html>

				<head>
					<meta http-equiv="Content-Type" content="text/html; utf-8">
					<title>
						<fmt:message key="title.sub.add" />
					</title>
					<link rel="stylesheet" type="text/css" href="css/form.css">
				</head>

				<body>
					<jsp:include page="../common/header.jsp" />

					<form action="controller" method="post" class="register-frm">
						<h1>
							<fmt:message key="header.sub.add" />
						</h1>
						<input type="hidden" name="command" value="add_subscription">
						<table>
							<tr>
								<td>
									<fmt:message key="label.magazine" />
								</td>
								<td>
									<customtags:select name="mag_id" itemList="${magList }"
										defaultId="${sub.magazine.id }" clazz="period-select" />
								</td>
							<tr>
								<td>
									<fmt:message key="label.sub.index" />
								</td>
								<td><input type="number" name="index" value="${sub.index }" required="required"
										min="10000" max="99999"></td>
								<td>${indexError }</td>
							</tr>
							<tr>
								<td>
									<fmt:message key="label.sub.duration" />
								</td>
								<td><input type="text" name="duration" value="${sub.duration }" required="required">
								</td>
								<td>${durationError }</td>
							</tr>
							<tr>
								<td>
									<fmt:message key="label.sub.price" />
								</td>
								<td><input type="number" name="price" value="${sub.price }" min="10" max="100000000"
										required="required"></td>
								<td>${priceError }</td>
							</tr>
							<tr>
								<td><input type="submit" value="<fmt:message key=" button.save" />" class="register-btn"
									/></td>
							</tr>
						</table>

					</form>

					<customtags:form-back />

					<jsp:include page="../common/footer.jsp" />
				</body>

				</html>