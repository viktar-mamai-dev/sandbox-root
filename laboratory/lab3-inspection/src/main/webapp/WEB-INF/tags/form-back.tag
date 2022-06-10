<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="pagecontent" />

<form action="controller" method="post" class="list-frm">
	<button name="command" value="show_mag_list">
		<fmt:message key="button.back.list" />
	</button>
</form>