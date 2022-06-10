<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
				<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
					<!DOCTYPE>
					<html>

					<head>
						<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
						<script type="text/javascript">
							function deleteComment(message) {
								if (!confirm(message)) {
									event.preventDefault();
								}
								return true;
							}
						</script>
					</head>

					<body>
						<div style="margin: 10px;">
							<a href="${pageContext.request.contextPath}${sourcePage }">
								<spring:message code="link.back" />
							</a>
						</div>
						<table class="news-object">
							<tr>
								<td colspan="2" style="font-weight: bold;">
									<c:out value="${newsEntity.title}" />
								</td>
								<td style="width: 20%">( by
									<c:out value="${newsEntity.author.name}" default="50" /> )
								</td>
								<td style="width: 20%;">
									<fmt:formatDate value="${newsEntity.creationDate}" dateStyle="short" />
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<c:out value="${newsEntity.fullText}" />
								</td>
							</tr>

							<c:forEach items="${newsEntity.comments }" var="comment">
								<tr>
									<td colspan="1"
										style="padding-left: 25px; padding-bottom: 0px; border-spacing: 0px;">
										<div>
											<fmt:formatDate value="${comment.creationDate}" dateStyle="short" />
										</div>

										<div
											style="background-color: #E8E8E8; position: relative; padding-top: 5px; padding-bottom: 5px;">
											<div style="margin: 10px">
												<c:out value="${comment.text }" />
											</div>
											<form action="${pageContext.request.contextPath}/comment/delete"
												onsubmit="deleteComment('<spring:message code="
												question.comment.delete" />')">
											<input type="hidden" name="newsId" value="${comment.news.id}">
											<input type="hidden" name="commentId" value="${comment.id}">
											<input type="submit" value="X"
												style="position: absolute; top: 0; right: 0;">
											</form>
										</div>
									</td>
								</tr>
							</c:forEach>

							<form:form action="${pageContext.request.contextPath}/comment/create" commandName="comment"
								method="post">
								<tr>
									<td colspan="1">
										<form:textarea path="text" rows="5" maxlength="100" style="width: 100%;"
											required="required" />
										<form:hidden path="news.id" value="${newsEntity.id}" />
									</td>
								</tr>
								<tr>
									<td style="width: 30%;"><input type="submit" class="submit-btn"
											style="float: right;" value="<spring:message code=" button.comment.add" />">
									</td>
								</tr>
							</form:form>
						</table>

						<table style="width: 100%; padding: 20px 20px 20px 20px;">
							<tr>
								<c:if test="${not empty previousId }">
									<td><a href="<c:url value=" /news/previous/${newsEntity.id}" />"
										class="link-15">
										<spring:message code="link.news.previous" /></a>
									</td>
								</c:if>
								<c:if test="${not empty nextId }">
									<td><a href="<c:url value=" /news/next/${newsEntity.id}" />"
										style="float: right;" class=link-15>
										<spring:message code="link.news.next" />
										</a>
									</td>
								</c:if>
							</tr>
						</table>
					</body>

					</html>