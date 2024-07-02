<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Categories</title>
</head>
<body>
    <h1>All Categories</h1>
    <ul>
        <c:forEach items="${categories}" var="category">
            <li>${category.name}</li>
        </c:forEach>
    </ul>
</body>
</html>
