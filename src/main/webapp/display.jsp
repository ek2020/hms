<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    if(session.getAttribute("loggedUser")==null){
    	response.sendRedirect("index.jsp");
    }
    %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <jsp:useBean id="user" class="global.coda.hospitalmanagementsystem.models.User" scope="request"/>  
     welcome, <%--<jsp:getProperty name="user" property="userName"/>--%>
       ${user.userName}
      <a href=edit>Edit</a>&nbsp;&nbsp;&nbsp;
      <a href=logoutUser>logout</a>
</body>
</html>