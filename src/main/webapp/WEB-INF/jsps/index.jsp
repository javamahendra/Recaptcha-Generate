<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>recap</title>
</head>
<body>
	<%-- ${pageContext. request.requestURL}<br/>
${pageContext. request.requestURI} --%>
	<br />
	<br />
	<br />
	<br />
	<br />
	<center>
	<form action="/mahi" method="post">
		<input type="text" name="recap" /> 
		<input type="submit" value="submit" /><br /> 
		<img alt="recaptcha" src="recaptchaImage.jpg"><br/>
		<img alt="QR_code" src="qr_image.jpg"/>
	</center>
	</form>
	<br /> ${msg}
</body>
</html>