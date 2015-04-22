<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String flag = null;
	try {
		flag = session.getAttribute("flag").toString();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	if (flag == null) {
		response.sendRedirect("https://localhost:8443/root/");
		
		session.setAttribute("already_redirect", "already_redirect");
		

	} else {
		response.sendRedirect("https://localhost:8443/sales-post/main.jsp");
	}
%>