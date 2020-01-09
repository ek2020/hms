<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!
String arrays[]={"karthick","coda","cool","happy"};
%>
<%
	request.setAttribute("arrays", arrays);
%>
<c:forEach var="user" items="${arrays}">
<li><c:out value="${user}" /></li>
</c:forEach>


</body>
</html>