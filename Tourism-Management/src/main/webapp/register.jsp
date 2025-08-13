<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Package Form</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h2>Enter Package Details</h2>

<form action="register" method="post">
    <p>${message}</p>
    <label>Package Name:</label>
    <input type="text" name="packageName" value="${dto.packageName}" required><br><br>
    <label>Destination:</label>
    <input type="text" name="destination" value="${dto.destination}" required><br><br>
    <label>Days:</label>
    <input type="number" name="days" value="${dto.days}"><br><br>
    <label>Package Price:</label>
    <input type="number" step="0.01" name="packagePrice" value="${dto.packagePrice}" required><br><br>
    <label>Persons Count:</label>
    <input type="number" name="personsCount" value="${dto.personsCount}" required><br><br>
    <input type="submit" value="Submit">
</form>
<a href="returnToIndex">HOME</a>
<a href="redirectToTourism">Tourism Form</a>
</body>
</html>