<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String error = null;
if ("POST".equalsIgnoreCase(request.getMethod())) {
String name = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
String dob = request.getParameter("dob");
String gender = request.getParameter("gender");
String state = request.getParameter("state");
String address = request.getParameter("address");
String psw = request.getParameter("psw");
String cpw = request.getParameter("cpw");

if (name == null || name.trim().isEmpty() ||
email == null || email.trim().isEmpty() ||
phone == null || phone.trim().isEmpty() ||
dob == null || dob.trim().isEmpty() ||
gender == null || gender.trim().isEmpty() ||
state == null || state.trim().isEmpty() ||
address == null || address.trim().isEmpty() ||
psw == null || psw.trim().isEmpty() ||
cpw == null || cpw.trim().isEmpty()) {
error = "All fields are required.";
} else if (!email.contains("@")) {
error = "Email must contain '@'.";
} else if (!psw.equals(cpw)) {
error = "Passwords do not match.";
} else {
error = "Registration successful!";
}
}
%>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <title>Register</title>
    <style>
        body {
          font-family: Arial, sans-serif;
          background-color: #f4f4f4;
          margin: 0;
          padding: 0;
        }
        header, footer {
          background-color: #1c1c1c;
          color: white;
          text-align: center;
          padding: 10px;
        }
        .form-container {
          max-width: 500px;
          margin: 30px auto;
          background-color: #fff;
          padding: 20px;
          border-radius: 8px;
          box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input, select, textarea {
          width: 100%;
          padding: 10px;
          margin: 10px 0;
          box-sizing: border-box;
        }
        button {
          background-color: #4CAF50;
          color: white;
          padding: 10px 20px;
          border: none;
          cursor: pointer;
        }
        button:hover {
          background-color: #45a049;
        }
        .error {
          color: red;
          text-align: center;
        }
        .success {
          color: green;
          text-align: center;
        }
    </style>
</head>
<body>

<header>
    <h2>Registration Page</h2>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link " href="redirectToIndex">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="redirectToRegister">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="redirectToLogin">Login</a>
            </li>
        </ul>
    </div>
</header>
<div class="form-container">
    <h4>${message}</h4>


    <form action="userRegister" method="post" id="form" class="w-50 p-4 border rounded">
        <label for="username" class="form-label">Username</label>
        <input type="text" class="form-control" id="username" name="name" value="${dto.name}" required minlength="3">

        <label for="email">Email:</label>
        <input type="email" class="form-control" id="email" name="email" value="${dto.email}" onblur="checkEmail()"
               pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,}$"
               title="Enter a valid email address like user@example.com" />
        <span style="color:red;" id="emailError"></span><br><br>

        <label for="phoneNumber" class="form-label">Phone Number</label>
        <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="${dto.phoneNumber}" onblur="phoneNumberCheck()"
               pattern="[6-9][0-9]{9}"
               title="Enter a valid 10-digit Indian mobile number starting with 6-9" />
        <span style="color:red;" id="phoneNumberError"></span><br><br>

        <label for="dob" class="form-label">Date of Birth</label>
        <input type="date" class="form-control" id="dob" name="dateOfBirth" value="${dto.dateOfBirth}" required>

        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="">Select Gender</option>
            <option value="female">Female</option>
            <option value="male">Male</option>
            <option value="other">Other</option>
        </select>

        <label for="state">State:</label>
        <select id="state" name="state" required>
            <option value="">Select State</option>
            <option value="Karnataka">Karnataka</option>
            <option value="Tamil Nadu">Tamil Nadu</option>
            <option value="Maharashtra">Maharashtra</option>
            <option value="Kerala">Kerala</option>
            <option value="Telangana">Telangana</option>
        </select>

        <label for="address" class="form-label">Address</label>
        <input type="text" class="form-control" id="address" name="address" value="${dto.address}" required>

        <label for="psw">Password:</label>
        <input type="password" id="psw" name="password" required minlength="6" />

        <label for="cpw">Confirm Password:</label>
        <input type="password" id="cpw" name="confirmPassword" required />

        <button type="submit">Register</button>
    </form>
</div>

<footer>
    <p>&copy; </p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.min.js"
        integrity="sha384-7qAoOXltbVP82dhxHAUje59V5r2YsVfBafyUDxEdApLPmcdhBPg1DKg1ERo0BZlK"
        crossorigin="anonymous"></script>
<script >document.querySelector("#form").addEventListener("submit", function (e) {
  let valid = true;
  const dob = document.getElementById("dob").value;
  if (!dob) {
    document.getElementById("dobError").textContent =
      "Date of Birth is required.";
    valid = false;
  } else if (new Date(dob) >= new Date()) {
    document.getElementById("dobError").textContent =
      "Date of Birth must be in the past.";
    valid = false;
  } else {
    document.getElementById("dobError").textContent = "";
  }
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;
  const passwordRegex =
    /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{5,}$/;

  if (!passwordRegex.test(password)) {
    document.getElementById("passwordError").textContent =
      "Password must be at least 5 characters, contain one uppercase letter, one number, and one special character.";
    valid = false;
  } else {
    document.getElementById("passwordError").textContent = "";
  }

  if (password !== confirmPassword) {
    document.getElementById("confirmPasswordError").textContent =
      "Passwords should match.";
    valid = false;
  } else {
    document.getElementById("confirmPasswordError").textContent = "";
  }

  const emailError = document.getElementById("emailError").innerText.trim();
  const phoneError = document.getElementById("phoneNumberError").innerText.trim();

  if (!valid || emailError !== "" || phoneError !== "") {
    e.preventDefault();
    alert("Fix the highlighted errors before submitting!");
  }
});

function checkEmail() {
  const email = document.getElementById('email').value;
  if (email !== "") {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:9090/common/emailCheck?email=" + email);
    xhttp.send();

    xhttp.onload = function () {
      document.getElementById("emailError").innerHTML = this.responseText;
      toggleSubmit();
    }
  }
}

function phoneNumberCheck() {
  const phoneNumber = document.getElementById('phoneNumber').value;
  if (phoneNumber !== "") {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:9090/common/phoneNumberCheck?phoneNumber=" + phoneNumber);
    xhttp.send();

    xhttp.onload = function () {
      document.getElementById("phoneNumberError").innerHTML = this.responseText;
      toggleSubmit();
    }
  }
}

function toggleSubmit() {
  const emailError = document.getElementById("emailError").innerText.trim();
  const phoneError = document.getElementById("phoneNumberError").innerText.trim();

  const submitButton = document.querySelector("#form button[type='submit']");

  if (emailError !== "" || phoneError !== "") {
    submitButton.disabled = true;
  } else {
    submitButton.disabled = false;
  }
}
</script>

</body>
</html>