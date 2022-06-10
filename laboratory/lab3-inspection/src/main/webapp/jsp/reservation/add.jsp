<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<fmt:setBundle basename="pagecontent" />
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<title>
					<fmt:message key="title.res.add" />
				</title>
				<link rel="stylesheet" type="text/css" href="css/form.css">
			</head>

			<body>
				<jsp:include page="../common/header.jsp" />

				<form action="controller" method="post">
					<input type="hidden" name="command" value="add_reservation"> <input type="hidden" name="sub_id"
						value="${sub.id }">
					<table class="item-table">
						<caption>
							<fmt:message key="header.sub.info" />
						</caption>
						<tr>
							<td rowspan="4" style="padding: 5px">
								<c:if test="${not empty mag.location }">
									<img src="img/${mag.location }" style="display: block;" />
								</c:if> <span id="title">
									<c:out value="${ mag.title }" />
								</span>
							</td>
							<td class="left">
								<fmt:message key="label.sub.index" />
							</td>
							<td class="right">
								<c:out value="${sub.index }" />
							</td>
						</tr>
						<tr>
							<td class="left">
								<fmt:message key="label.sub.price" />
							</td>
							<td class="right">
								<c:out value="${ sub.price}" />
							</td>
						</tr>
						<tr>
							<td class="left">
								<fmt:message key="label.res.count" />
							</td>
							<td class="right"><select name="count" class="age">
									<c:forEach begin="1" end="5" step="1" var="subCount">
										<option value="${subCount }">${subCount }</option>
									</c:forEach>
								</select></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="register-btn" value="<fmt:message key="
									button.save" />" />
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