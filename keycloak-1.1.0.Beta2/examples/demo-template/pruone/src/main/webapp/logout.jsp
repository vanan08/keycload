
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="javax.servlet.http.Cookie" %>
<% 
session.invalidate();
String url= request.getContextPath() + "?landingPage=true";
%>
<html>
<head>
<title>PicketLink Example Application</title>
<META HTTP-EQUIV="refresh" CONTENT="1;URL=<%= url %>">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="StyleSheet" href="css/idp.css" type="text/css">
</head>

<body>
	<img src="images/picketlink-banner-1180px.png"
		style="margin-top: -10px; margin-left: -10px; opacity: 0.4; filter: alpha(opacity =   40);" />
	<div class="loginBox"
		style="margin-bottom: 80px; border: 1px solid #000000; width: 440px; background-color: #F8F8F8; align: center;">
		<center>
			<p>
				<b>Logout in progress. You will be redirected to the Login Page.</b>
			</p>
		</center>
	</div>
</body>
</html>