<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>recap</title>

<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>


</head>
<body>
	<p>Set focus on the box to select a date!</p>
	${msg}
	<div class="input-append date form_datetime">
		<input size="16" type="text" value=""> <span
			class="add-on"><i class="icon-th"></i></span>
	</div>

	<script type="text/javascript">
		$(".form_datetime").datetimepicker({
			format : "dd MM yyyy",
			autoclose : true,
			todayBtn : true,
			pickerPosition : "bottom-left"
		});
	</script>
</body>
</html>