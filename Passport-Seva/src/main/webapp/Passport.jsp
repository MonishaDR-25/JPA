<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<form action="register" method="post">
    <label for="passportOffice">Passport Office:</label>
    <select name="passportOffice" id="passportOffice">
        <option value="Bangalore">Bangalore</option>
        <option value="Delhi">Delhi</option>
        <option value="Mumbai">Mumbai</option>
        <option value="AndhraPradesh">Andhra Pradesh</option>
    </select>
    <br>
    <div>
        Given Name: <input type="text" name="givenName">
    </div>
    <br>
    <div>
        Surname:  <input type="text" name="surname">
    </div>
    <br>
    <div>
        Date Of Birth:  <input type="date" name="dateOfBirth">
    </div>
    <br>
    <div>
        <span id="emailerror"></span>
        E-mail Id:  <input type="text" id="email" name="emailId" onblur="loginemail()">
    </div>
    <br>
    <div>
        <span id="loginiderror"></span>
        Login Id:  <input type="text" id="loginId" name="loginId" onblur="loginId()">
    </div>
    <br>
    <div>
        Password:  <input type="text" name="Password">
    </div>
    <br>
    <div>
        Confirm Password:   <input type="text" name="confirmPassword">
    </div>
    <br>
    <div>
        Hint Question:  <input type="text" name="hintQuestion">
    </div>
    <br>
    <div>
        Hint Answer:   <input type="text" name="hintAnswer">
    </div>
    <br>
    <div>
        Phone Number:   <input type="number" name="phoneNumber">
    </div>
    <br>
    <input type="submit" value="Submit">
</form>
<div>
<a href="redirectToPassport">Passport Form</a>
</div>
<script>
    function loginemail() {
    const email = document.getElementById('email').value;
    console.log("email:", email);

    if (email !== "") {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:9090/Passport-Seva/loginemail?email=" + email);
        xhttp.send();

        xhttp.onload = function () {
            document.getElementById("emailerror").innerHTML = this.responseText;
        };
    }
}
     function loginId() {
    const loginId = document.getElementById('loginId').value;
    console.log("loginId:", loginId);

    if (loginId !== "") {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:9090/Passport-Seva/loginId?loginId=" + loginId);
        xhttp.send();

        xhttp.onload = function () {
            document.getElementById("loginerror").innerHTML = this.responseText;
        };
    }
}
</script>
</body>
</html>