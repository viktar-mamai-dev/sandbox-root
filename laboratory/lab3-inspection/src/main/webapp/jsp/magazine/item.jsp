<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
				<%@ taglib prefix="customtags" tagdir="/WEB-INF/tags" %>
					<fmt:setBundle basename="pagecontent" />
					<html>

					<head>
						<title>
							<fmt:message key="title.magazine.item" />
						</title>
						<link rel="stylesheet" type="text/css" href="css/form.css">
						<script src="js/jquery.min.js"></script>
						<script type="text/javascript">
							$(document).ready(function () {
								$("#delete-frm").submit(function () {
									if (window.confirm("Вы действительно хотите удалить подписку?")) {
										return true;
									}
									return false;
								});
							});
						</script>
					</head>

					<body>
						<jsp:include page="../common/header.jsp" />
						<c:if test="${not empty successMessage}">
							<div id="success-message">${successMessage }</div>
						</c:if>
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
						<c:choose>
							<c:when test="${fn:length(mag.subs) gt 0}">
								<h2 align="center">
									<fmt:message key="header.magazine.sub" />
								</h2>
								<c:forEach var="sub" items="${mag.subs}" varStatus="status">
									<div style="margin: 0 auto; padding: 10px; margin-left: 30%; display: block;">
										<table
											style="font-size: 1.2em; border: 1px solid black; background-color: rgb(180, 180, 200); width: 700px">
											<tr>
												<td class="left">
													<fmt:message key="label.sub.index" />
												</td>
												<td class="right">
													<c:out value="${ sub.index }" />
												</td>
												<td align="center" width="20%"
													style="padding: 20px; border-left: 1px solid black; vertical-align: middle;"
													rowspan="3">
													<c:if test="${role eq 'admin' }">
														<form action="controller" method="post">
															<input type="hidden" name="command"
																value="select_subscription"> <input type="hidden"
																name="sub_id" value="${sub.id }"> <input type="submit"
																class="admin-btn" value="<fmt:message key=" button.update" />">
														</form>
														<form action="controller" method="post" id="delete-frm">
															<input type="hidden" name="command"
																value="delete_subscription"> <input type="hidden"
																name="sub_id" value="${sub.id }"><input type="hidden"
																name="mag_id" value="${mag.id }"> <input type="submit"
																class="admin-btn" value="<fmt:message key=" button.delete" />">
														</form>
													</c:if>
													<c:if test="${role eq 'reader' }">
														<form action="controller" method="post">
															<input type="hidden" name="command" value="new_reservation">
															<input type="hidden" name="sub_id" value="${sub.id }"><input
																type="hidden" name="mag_id" value="${mag.id }"> <input
																type="submit" class="admin-btn" value="<fmt:message key=" button.res.add" />">
														</form>
													</c:if>
												</td>
											</tr>
											<tr>
												<td class="left">
													<fmt:message key="label.sub.duration" />
												</td>
												<td class="right">
													<c:out value="${ sub.duration}" />
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

										</table>

									</div>

								</c:forEach>
							</c:when>
							<c:otherwise>
								<h2 align="center">
									<fmt:message key="header.magazine.empty" />
								</h2>
							</c:otherwise>
						</c:choose>
						<customtags:form-back />

						<jsp:include page="../common/footer.jsp" />
					</body>

					</html>