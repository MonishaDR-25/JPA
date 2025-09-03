<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register Soap</title>

<body>
<form action="update" method="post">
    <p style="color:red">${message}</p>

    <div>
        Soap ID:
        <input type="number" name="soapId" value="${soapDto.soapId}" readonly>
    </div>
    <div>
        Soap Name:
        <input type="text" name="name" value="${soapDto.name}" required>
    </div>
    <div>
        Soap Brand:
        <input type="text" name="brand" value="${soapDto.brand}" required>
    </div>
    <div>
        Soap Color:
        <input type="text" name="color" value="${soapDto.color}" required>
    </div>
    <div>
        Soap Cost:
        <input type="number" name="cost" value="${soapDto.cost}" required>
    </div>
    <button type="submit">Submit</button>
</form>
</body>
</html>