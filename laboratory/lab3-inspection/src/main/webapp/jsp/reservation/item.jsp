<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
				<%@ taglib prefix="customtags" tagdir="/WEB-INF/tags" %>
					<fmt:setBundle basename="pagecontent" />
					<html>

					<head>
						<title>
							<fmt:message key="title.res.item" />
						</title>
						<link rel="stylesheet" type="text/css" href="css/form.css">
						<script src="js/jquery.min.js"></script>
						<script type="text/javascript">
							$(document).ready(function () {
								$("#delete-frm").submit(function () {
									if (window.confirm("Вы действительно хотите отменить заказ?")) {
										return true;
									}
									return false;
								});
								$(".status").each(function () {
									var status = $(this);
									var row = status.closest("tr");
									if (status.text() == "paid") {
										row.find("td").css("background-color", "LightSeaGreen");
									} else if (status.text() == "debt") {
										row.find("td").css("background-color", "LightCoral");
									}
								});
							});
						</script>
					</head>

					<body>
						<jsp:include page="../common/header.jsp" />

						<c:choose>
							<c:when test="${fn:length(resList) gt 0}">
								<table class="magazine-table">
									<caption>
										<fmt:message key="header.res.item" />
									</caption>
									<tr>
										<th>
											<fmt:message key="label.res.title" />
										</th>
										<th>
											<fmt:message key="label.res.index" />
										</th>
										<th>
											<fmt:message key="label.res.price" />
										</th>
										<th>
											<fmt:message key="label.res.count" />
										</th>
										<th>
											<fmt:message key="label.res.status" />
										</th>
										<th>
											<fmt:message key="header.action" />
										</th>
									</tr>
									<c:forEach var="res" items="${resList }" varStatus="status">
										<c:url value="controller" var="magUrl">
											<c:param name="command" value="show_mag_item" />
											<c:param name="mag_id" value="${res.subscription.magazine.id}" />
										</c:url>
										<tr>
											<td><a href="${magUrl }">
													<c:out value="${ res.subscription.magazine.title }" />
												</a></td>
											<td>${res.subscription.index }</td>
											<td>${res.subscription.price }</td>
											<td>${res.count }</td>
											<td class="status">${res.status.description}</td>
											<td>
												<c:if
													test="${res.status.description eq 'unpaid' or res.status.description eq 'debt' }">
													<form action="controller" method="post" id="delete-frm">
														<input type="hidden" name="command" value="delete_reservation">
														<input type="hidden" name="res_id" value="${res.id }">
														<input type="submit" class="admin-btn" value="<fmt:message
							key=" button.res.cancel" />">
													</form>
												</c:if>
											</td>
										</tr>
									</c:forEach>

								</table>
							</c:when>
							<c:otherwise>
								<h2 align="center">
									<fmt:message key="header.res.empty" />
								</h2>
							</c:otherwise>
						</c:choose>

						<customtags:form-back />

						<jsp:include page="../common/footer.jsp" />

					</body>

					</html>