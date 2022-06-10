<%-- <link rel="stylesheet" type="text/css" href="<c:url value=" /resources/css/ui.dropdownchecklist.standalone.css" />
"> --%>
<link rel="stylesheet" type="text/css" href="<c:url value=" /resources/css/jquery-ui-1.8.13.custom.css" />">
<script src="<c:url value=" /resources/js/jquery-1.6.1.min.js" />"
type="text/javascript"></script>
<script src="<c:url value=" /resources/js/jquery-ui-1.8.13.custom.min.js" />"
type="text/javascript"></script>
<script src="<c:url value=" /resources/js/ui.dropdownchecklist-1.4-min.js" />"
type="text/javascript"></script>
<script src="<c:url value=" /resources/js/myscript.js" />"
type="text/javascript"></script>
<script src="<c:url value=" /resources/js/jquery.ui.datepicker-ru.js" />"
type="text/javascript"></script>
<script type="text/javascript">
	$(document)
		.ready(
			function () {
				$("#tag-select")
					.dropdownchecklist(
						{
							firstItemChecksAll: true,
							maxDropHeight: 150,
							width: 200,
							forceMultiple: true,
							icon: {},
							textFormatFunction: function (options) {
								var selectedOptions = options.filter(":selected");
								var countOfSelected = selectedOptions.size();
								var size = options.size();
								switch (countOfSelected) {
									case 0:
										return $("#tag-select").attr('header');
									case 1:
										return selectedOptions.text();
									case options.size():
										return $("#tag-select :first-child").text();
									default:
										return countOfSelected + $("#tag-select").attr('tag-count');
								}
							}
						});
				$("#author-select").dropdownchecklist(
						{
							maxDropHeight: 150,
							width: 200,
							icon: {},
							onItemClick: function (checkbox, selector) {
								var justChecked = checkbox.prop("checked");
								var checkCount = (justChecked) ? 1 : -1;
								for (var i = 0; i < selector.options.length; i++) {
									if (selector.options[i].selected)
										checkCount += 1;
								}
								if (checkCount < 1) {
									throw "too little";
								}
							}
						});
				$("#delete-frm")
					.submit(
						function (event) {
							var numberOfChecked = $(".news-object").find("input:checkbox:checked").length;
							if (numberOfChecked == 0) {
								alert("No one news selected to delete");
								event.preventDefault();
								return;
							}
							if (!confirm('Are you sure?')) {
								event.preventDefault();
							}
							return true;
						});
				$("#filter-frm")
					.submit(
						function (event) {
							var tagLength = $("#tag-select :selected").length;
							var authorVal = $("#author-select :selected").val();
							if (!tagLength && !(authorVal >= 1)) {
								alert("Nothing was selected in filter");
								event.preventDefault();
							}
							return true;
						});
			});
</script>