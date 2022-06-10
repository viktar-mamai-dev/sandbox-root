<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var oldTagName;
	$(function() {
		setDefaultOptions();
		$(".edit-link").click(function() {
			setDefaultOptions();

			$(this).parents(".row").find(".passive-link").show();
			$(this).hide();
			$(this).parents(".row").find(".name").removeAttr('disabled');
			oldTagName = $(this).parents(".row").find(".name").val();
			return false;
		});
		$(".cancel-link").click(function() {
			setDefaultOptions();
			$(this).parents(".row").find(".name").val(oldTagName);
			return false;
		});
	});
	function setDefaultOptions() {
		$(".passive-link").hide();
		$(".edit-link").show();
		$(".name").attr('disabled', 'disabled');
		$("#new-tag").val('');
	}
	function updateTag(message) {
		if (!confirm(message)) {
			event.preventDefault();
		}
		return true;
	}
	function deleteTag(message) {
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


	<c:if test="${not empty successMessage}">
		<div class="success-message-div">
			<div>
				<c:out value="${successMessage}" />
			</div>
		</div>
	</c:if>


	<table class="center-table">
		<c:forEach items="${tagList }" var="tag">
			<tr class="row">
				<td style="text-align: center;"><spring:message
						code="label.tag.name" /></td>
				<td><form:form commandName="tagEntity" method="POST"
						style="margin-bottom : auto;"
						action="${pageContext.request.contextPath}/tag/update">
						<form:hidden path="id" value="${tag.id }" />
						<form:hidden path="version" value="${tag.version }" />
						<form:input path="name" value="${tag.name }" disabled="disabled"
							maxlength="30" required="required" cssClass="name"
							style="margin-right : 20px;" />
						<input type="submit" value="<spring:message code="link.update" />"
							class="linkButton passive-link">
						<input type="submit" value="<spring:message code="link.edit" />"
							class="linkButton edit-link">
					</form:form></td>
				<td>
					<form action="${pageContext.request.contextPath}/tag/delete"
						style="margin-bottom: auto;" method="POST">
						<input type="hidden" name="id" value="${tag.id }"> <input
							type="submit" value="<spring:message code="link.delete" />"
							class="linkButton passive-link"
							onclick="deleteTag('<spring:message code="question.tag.delete" />')">
					</form>
				</td>
				<td><input type="submit"
					value="<spring:message code="link.cancel" />"
					class="linkButton passive-link cancel-link"></td>

			</tr>
		</c:forEach>
		<tr class="bottom-row">
			<td><spring:message code="label.tag.add" /></td>
			<td><form:form method="post" style="margin-bottom : auto;"
					action="${pageContext.request.contextPath}/tag/create"
					commandName="tagEntity">
					<form:input path="name" style="margin-right : 20px;" maxlength="30"
						required="required" id="new-tag" />
					<input type="submit" class="linkButton"
						value="<spring:message code="link.save" />" />
				</form:form></td>
		</tr>
	</table>
</body>
</html>