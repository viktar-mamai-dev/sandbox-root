<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ attribute name="itemList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="defaultId" required="false"%>
<%@ attribute name="clazz" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<select name="${name }" class="${clazz }">
	<c:forEach items="${itemList}" var="item">
		<option value="${item.id }"
			<c:if test="${item.id==defaultId}">selected="selected"</c:if>>${item.report}</option>
	</c:forEach>
</select>
