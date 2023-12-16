<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
				<fmt:setLocale value="${language }" />
				<fmt:setBundle basename="pagecontent" />
				<html>

				<head>
					<title>
						<fmt:message key="title.magazine.list" />
					</title>
					<link rel="stylesheet" type="text/css" href="css/form.css">
					<script src="js/jquery.min.js"></script>
					<script type="text/javascript">
						$(document).ready(function () {
							$("#delete-frm").submit(function () {
								if (window.confirm("Вы действительно хотите удалить журнал?")) {
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
					<c:if test="${empty sequence }">
						<c:set var="sequence" value="0" />
					</c:if>
					<table class="magazine-table">
						<caption>
							<fmt:message key="label.magazine.list" />
						</caption>
						<tr>
							<th>
								<fmt:message key="label.number" />
							</th>
							<th class="title">
								<fmt:message key="label.magazine.title" />
							</th>
							<th>
								<fmt:message key="label.magazine.annotation" />
							</th>
							<th class="periodicity">
								<fmt:message key="label.magazine.periodicity" />
							</th>
							<c:if test="${role eq 'admin' }">
								<th>
									<fmt:message key="header.action" />
								</th>
								<th>
									<fmt:message key="header.action" />
								</th>
							</c:if>
						</tr>
						<c:forEach var="mag" items="${magList}" varStatus="status">
							<c:url value="controller" var="magUrl">
								<c:param name="command" value="show_mag_item" />
								<c:param name="mag_id" value="${mag.id}" />
							</c:url>
							<tr>
								<td>
									<c:out value="${status.count + sequence * perPage }"></c:out>
								<td class="title"><a href="${magUrl }">
										<c:out value="${ mag.title }" />
									</a></td>
								<td class="annotation">
									<c:out value="${ mag.annotation }" />
								</td>
								<td class="periodicity">
									<c:out value="${ mag.period.periodicity}" />
								</td>
								<c:if test="${role eq 'admin' }">
									<td>
										<form action="controller" method="post">
											<input type="hidden" name="command" value="select_magazine">
											<input type="hidden" name="mag_id" value="${mag.id }"> <input type="submit"
												class="admin-btn" value="<fmt:message
							key=" button.update" />">
										</form>
									</td>
									<td>
										<form action="controller" method="post" id="delete-frm">
											<input type="hidden" name="command" value="delete_magazine">
											<input type="hidden" name="mag_id" value="${mag.id }"> <input type="submit"
												class="admin-btn" value="<fmt:message
							key=" button.delete" />">
										</form>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
					<hr />

					<c:url value="controller" var="magPrevUrl">
						<c:param name="command" value="show_mag_list" />
						<c:param name="sequence" value="${sequence-1}" />
					</c:url>

					<c:url value="controller" var="magNextUrl">
						<c:param name="command" value="show_mag_list" />
						<c:param name="sequence" value="${sequence+1}" />
					</c:url>

					<div class="pagination">
						<c:choose>
							<c:when test="${sequence gt 0}">
								<a href="${magPrevUrl }" class="page">
									<fmt:message key="footer.previous" />
								</a>
							</c:when>
							<c:otherwise>
								<a href="${magPrevUrl }" class="page" onclick="return false">
									<fmt:message key="footer.previous" />
								</a>
							</c:otherwise>
						</c:choose>

						<c:forEach begin="0" end="${pageCount-1 }" var="i">
							<c:url value="controller" var="magPageUrl">
								<c:param name="command" value="show_mag_list" />
								<c:param name="sequence" value="${i}" />
							</c:url>
							<c:choose>
								<c:when test="${sequence eq i}">
									<a href="${magPageUrl }" class="page active" onclick="return false">
										<c:out value="${ i+1 }" />
									</a>
								</c:when>
								<c:otherwise>

									<a href="${magPageUrl }" class="page">
										<c:out value="${ i+1 }" />
									</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${sequence lt pageCount-1}">
								<a href="${magNextUrl }" class="page">
									<fmt:message key="footer.next" />
								</a>
							</c:when>
							<c:otherwise>
								<a href="${magNextUrl }" class="page" onclick="return false">
									<fmt:message key="footer.next" />
								</a>
							</c:otherwise>
						</c:choose>
					</div>

					<jsp:include page="../common/footer.jsp" />
				</body>

				</html>