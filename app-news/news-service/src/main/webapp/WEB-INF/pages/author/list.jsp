<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
			<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
				<!DOCTYPE>
				<html>

				<head>
					<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
					<script type="text/javascript">
						$(function () {
							setDefaultOptions();
							$(".edit-link").click(function () {
								setDefaultOptions();

								$(this).parents(".row").find(".passive-link").show();
								$(this).hide();
								$(this).parents(".row").find(".name").removeAttr('disabled');
								return false;
							});

							$(".cancel-link").click(function () {
								setDefaultOptions();
								$(this).parents(".row").find(".name").val(oldTagName);
								return false;
							});
						});
						function setDefaultOptions() {
							$(".passive-link").hide();
							$(".edit-link").show();
							$(".name").attr('disabled', 'disabled');
							$("#new-author").val('');
						}
						function updateAuthor(message) {
							if (!confirm(message)) {
								event.preventDefault();
							}
							return true;
						}
						function expireAuthor(message) {
							if (!confirm(message)) {
								event.preventDefault();
							}
							return true;
						}
					</script>
				</head>

				<body>

					<c:if test="${not empty errorMessage}">
						<div class="error-message-div">
							<div>
								<c:out value="${errorMessage}" />
							</div>
						</div>
					</c:if>

					<div class="success-message-div">
						<c:if test="${not empty successMessage}">
							<div>
								<c:out value="${successMessage}" />
							</div>
						</c:if>
					</div>

					<table class="center-table">
						<c:forEach items="${authorList }" var="author">
							<c:if test="${empty author.expiredDate}">
								<tr class="row">
									<td style="text-align: center;">
										<spring:message code="label.author.name" />
									</td>
									<td>
										<form:form commandName="authorEntity" method="POST"
											style="margin-bottom : auto;"
											action="${pageContext.request.contextPath}/author/update">
											<form:hidden path="id" value="${author.id }" />
											<form:hidden path="version" value="${author.version }" />
											<form:input path="name" value="${author.name }" required="required"
												maxlength="30" disabled="disabled" cssClass="name"
												style="margin-right : 20px;" />
											<input type="submit" value="<spring:message code=" link.update" />"
											class="linkButton passive-link"
											onclick="updateAuthor('
											<spring:message code="message.confirm" />')">
											<input type="submit" value="<spring:message code=" link.edit" />"
											class="linkButton edit-link">
										</form:form>
									</td>
									<td>
										<form action="${pageContext.request.contextPath}/author/expired"
											style="margin-bottom: auto;" method="POST">
											<input type="hidden" name="id" value="${author.id }"> <input type="submit"
												value="<spring:message code=" link.expired" />"
											class="linkButton passive-link"
											onclick="expireAuthor('
											<spring:message code="question.author.expire" />')">
										</form>
									</td>
									<td><input type="submit" value="<spring:message code=" link.cancel" />"
										class="linkButton passive-link cancel-link"></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr class="bottom-row">
							<td>
								<spring:message code="label.author.add" />
							</td>
							<td>
								<form:form method="post" style="margin-bottom : auto;"
									action="${pageContext.request.contextPath}/author/create"
									commandName="authorEntity">
									<form:input path="name" style="margin-right : 20px;" required="required"
										maxlength="30" id="new-author" />
									<input type="submit" class="linkButton" value="<spring:message code=" link.save" />"
									/>
								</form:form>
							</td>
						</tr>
					</table>
				</body>

				</html>