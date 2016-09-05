<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nike Runs</title>
</head>
<body>
<%
String nikeFuel = (String) request.getAttribute("nikeFuel");
String totalDistance = (String) request.getAttribute("totalDistance");
String totalTime = (String) request.getAttribute("totalTime");
String avgPace = (String) request.getAttribute("avgPace");
%>

Nike Fuel : <%=nikeFuel %><br />
Total Distance : <%=totalDistance %><br />
Total Time : <%=totalTime %><br />
Average Pace : <%=avgPace %><br />
</body>
</html>