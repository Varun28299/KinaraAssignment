<%@page import="com.kc.dto.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student</title>
</head>
<body>
	<%
	ArrayList<Student> students = (ArrayList) request.getAttribute("data");
	%>

	<h1>STUDENTS LIST</h1>
	<br>
	<br>
	<table border="5px">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Marks</th>
		</tr>

		<%
		for (int i = 0; i < students.size(); i++) {
		%>
		<%
		Student s = students.get(i);
		%>
		<tr>
			<td><%=s.getId()%></td>
			<td><%=s.getName()%></td>
			<td><%=s.getMarks()%></td>
		</tr>
		<%
		}
		%>


	</table>


	<div>
		<%
		int currentPage = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		int totalPages = 10;

		String baseUrl = "studentdata";
		String prevUrl = currentPage > 1 ? baseUrl + "?page=" + (currentPage - 1) : "";
		String nextUrl = currentPage < totalPages ? baseUrl + "?page=" + (currentPage + 1) : "";
		%>
		<h4>
			<a href="<%=prevUrl%>">Previous</a> Page
			<%=currentPage%>
			of
			<%=totalPages%>
			<a href="<%=nextUrl%>">Next</a>
		</h4>
	</div>

	<div>
		<%
		String min = request.getParameter("minMarks");
		String max = request.getParameter("maxMarks");
		%>
		<form action="studentdata?page=0">
			<label for="minMarks">Min Marks:</label> <input type="number"
				id="minMarks" name="minMarks" value="<%=min%>" required> <label
				for="maxMarks">Max Marks:</label> <input type="number" id="maxMarks"
				name="maxMarks" value="<%=max%>" required> <input
				type="submit" value="Filter">
		</form>
	</div>




</body>

</html>