<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<fmt:setLocale value="${language }" />
			<fmt:setBundle basename="pagecontent" />
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
				<title>
					<fmt:message key="title.sub.update" />
				</title>
				<link rel="stylesheet" type="text/css" href="css/form.css">
			</head>

			<body>

				<jsp:include page="../common/header.jsp" />

				<table class="item-table">
					<caption>
						<fmt:message key="header.magazine.info" />
					</caption>
					<tr>
						<td rowspan="2" style="padding: 5px">
							<c:if test="${not empty mag.location }">
								<img src="img/${mag.location }" style="display: block;" />
							</c:if> <span id="title">
								<c:out value="${ mag.title }" />
							</span>
						</td>
						<td class="left">
							<fmt:message key="label.magazine.annotation" />
						</td>
						<td class="right">
							<c:out value="${ mag.annotation }" />
						</td>
					</tr>
					<tr>
						<td class="left">
							<fmt:message key="label.magazine.periodicity" />
						</td>
						<td class="right">
							<c:out value="${ mag.period.periodicity}" />
						</td>
					</tr>
				</table>

				<form method="post" action="controller" class="register-frm">
					<input type="hidden" name="command" value="update_subscription" /> <input type="hidden"
						name="mag_id" value="${mag.id }" /> <input type="hidden" name="sub_id" value="${sub.id }" />
					<h1>
						<fmt:message key="header.sub.update" />
					</h1>
					<table align="center">
						<tr>
							<td>
								<fmt:message key="label.sub.index" />
							</td>
							<td><input type="text" name="index" value="${sub.index }"></td>
							<td>${indexError }</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="label.sub.duration" />
							</td>
							<td><input type="text" name="duration" value="${sub.duration }"></td>
							<td>${durationError }</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="label.sub.price" />
							</td>
							<td><input type="text" name="price" value="${sub.price }"></td>
							<td>${priceError }</td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="register-btn" value="<fmt:message key="
									button.save" />" /></td>
						</tr>
					</table>
				</form>

				<form action="controller" method="post" class="list-frm">
					<input type="hidden" name="command" value="show_mag_item" /> <input type="hidden" name="mag_id"
						value="${mag.id }" /> <input type="submit" value="<fmt:message
							key=" button.back.magazine" />">
				</form>

				<jsp:include page="../common/footer.jsp" />

			</body>

			</html>