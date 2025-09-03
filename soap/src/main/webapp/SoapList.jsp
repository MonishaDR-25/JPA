<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SOAP DETAILS</title>
</head>
<body>
<table>
    <tr>
        <th>Soap ID:</th>
        <th>Soap Name:</th>
        <th>Soap Brand:</th>
        <th>Soap Color:</th>
        <th>Soap Cost:</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="ref" items="${list}">
        <tr>
            <td>${ref.soapId}</td>
            <td>${ref.name}</td>
            <td>${ref.brand}</td>
            <td>${ref.color}</td>
            <td>${ref.cost}</td>
            <td><a href="getAllData">View All</a></td>
            <td class="text-center">
                <a href="view?id=${ref.soapId}">View</a>
                <a href="edit?id=${ref.soapId}" >Edit</a>
                <a href="delete?id=${ref.soapId}" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div>
    <a href="redirectToIndex" class="btn btn-outline-secondary">Home</a>
</div>
</body>
</html>
