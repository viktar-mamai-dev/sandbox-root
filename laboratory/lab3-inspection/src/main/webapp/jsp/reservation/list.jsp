<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
				<%@ taglib prefix="customtags" tagdir="/WEB-INF/tags" %>
					<fmt:setBundle basename="pagecontent" />
					<html>

					<head>
						<title>
							<fmt:message key="title.res.list" />
						</title>
						<link rel="stylesheet" type="text/css" href="css/form.css">
						<script src="js/jquery.min.js"></script>
						<script type="text/javascript">
							$(document).ready(function () {
								$("#delete-frm").submit(function () {
									if (window.confirm("Вы действительно хотите удалить заказ?")) {
										return true;
									}
									return false;
								});
								$("#update-frm").submit(function () {
									if (window.confirm("Вы действительно хотите обновить заказ?")) {
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

						<c:set var="isModerator" value="${role eq 'moderator' }"></c:set>
						<c:choose>
							<c:when test="${fn:length(resList) gt 0}">
								<table class="magazine-table">
									<caption>
										<fmt:message key="header.res.list" />
									</caption>
									<tr>
										<th>
											<fmt:message key="label.res.user" />
										</th>
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
										<c:if test="${isModerator }">
											<th>
												<fmt:message key="header.action" />
											</th>
										</c:if>
									</tr>
									<c:forEach var="res" items="${resList }" varStatus="status">
										<tr style="background-color: green;">
											<td>${res.user.userName }</td>
											<td>${res.subscription.magazine.title }</td>
											<td>${res.subscription.index }</td>
											<td>${res.subscription.price }</td>
											<td>${res.count }</td>
											<td class="status">${res.status.description }</td>
											<c:if test="${isModerator}">
												<td valign="middle">
													<c:if test="${res.status.description eq 'debt' }">
														<form action="controller" method="post" id="delete-frm">
															<input type="hidden" name="command"
																value="delete_reservation">
															<input type="hidden" name="res_id" value="${res.id }">
															<input type="submit" class="admin-btn" value="<fmt:message
							key=" button.delete" />">
														</form>
													</c:if>
													<c:if test="${res.status.description eq 'unpaid' }">
														<form action="controller" method="post" id="update-frm">
															<input type="hidden" name="command"
																value="update_reservation">
															<input type="hidden" name="res_id" value="${res.id }">
															<input type="hidden" name="status_id" value="2"> <input
																type="submit" class="admin-btn" value="<fmt:message
							key=" button.res.makepaid" />">
														</form>
													</c:if>
												</td>
											</c:if>
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