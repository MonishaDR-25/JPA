<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <title>All Data</title>
    <style>
        table {
          font-family: arial, sans-serif;
          border-collapse: collapse;
          width: 100%;
        }

        td, th {
          border: 1px solid #dddddd;
          text-align: left;
          padding: 8px;
        }

        tr:nth-child(even) {
          background-color: #dddddd;
        }
    </style>
</head>
<body>
<div>
    <a href="redirectToView">Soap Form</a>
</div>

<h2>Soap Table</h2>
<h4>${message}</h4>
<table>
    <tr>
        <th>SoapId</th>
        <th>Name</th>
        <th>Brand</th>
        <th>Color</th>
        <th>Cost</th>
    </tr>

    <c:forEach var="item" items="${soapDtoList}">
        <tr>
            <td>${item.SoapId}</td>
            <td>${item.Name}</td>
            <td>${item.Brand}</td>
            <td>${item.Color}</td>
            <td>${item.Cost}</td>
            <td><a href="getByID?id=${item.soapId}">View</a> / <a href="edit?id=${item.soapId}">Edit</a> / <a href="delete/${item.soapId}">Delete</a></td>
        </tr>
    </c:forEach>

    <tr>
        <td>${soapDto.soapId}</td>
        <td>${soapDto.name}</td>
        <td>${soapDto.brand}</td>
        <td>${soapDto.color}</td>
        <td>${soapDto.cost}</td>
        <td><a href="fetchAll">View All</a></td>
    </tr>


</table>
<a href="backToIndex">Go Back</a>


</body>
</html>