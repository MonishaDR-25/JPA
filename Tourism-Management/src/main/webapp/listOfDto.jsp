<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html xmlns:c="">
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product Details</h1>
<table border="1">
    <thead>
    <tr>
        <th>Package ID</th>
        <th>Package Name</th>
        <th>Destinations</th>
        <th>Days</th>
        <th>Package Price</th>
        <th>Person Count</th>
        <th>View</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ref" items="${ListOfDto}">
        <tr>
            <td>${ref.packageId}</td>
            <td>${ref.packageName}</td>
            <td>${ref.destination}</td>
            <td>${ref.days}</td>
            <td>${ref.packagePrice}</td>
            <td>${ref.personCount}</td>
            <td><a href="view?id=${ref.packageId}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="returnToIndex">HOME</a>
</body>
</html>