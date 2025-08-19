<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String message = null;
if ("POST".equalsIgnoreCase(request.getMethod())) {
String email = request.getParameter("email");
String password = request.getParameter("password");

if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
message = "Both fields are required.";
} else if (!email.contains("@")) {
message = "Invalid email format.";
} else {
// Placeholder for authentication logic
// Example: if (email.equals("admin@example.com") && password.equals("admin123")) { ... }

message = "Login attempt received. (Backend validation pending)";
}
}
%>
<html>
<head>
    <title>Login</title>
    <style>
        body {
          font-family: Arial, sans-serif;
          background-color: #f2f2f2;
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
          max-width: 400px;
          margin: 40px auto;
          background-color: #fff;
          padding: 25px;
          border-radius: 8px;
          box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input {
          width: 100%;
          padding: 10px;
          margin: 12px 0;
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
        .message {
          text-align: center;
          color: <%= message != null && message.contains("Login") ? "green" : "red" %>;
        }
    </style>
</head>
<body>

<header>
    <h2>Login Portal</h2>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link " href="redirectToIndex">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="redirectToRegister">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="redirectToLogin">Login</a>
            </li>
        </ul>
    </div>
</header>

<div class="form-container">
    <% if (message != null) { %>
    <p class="message"><%= message %></p>
    <% } %>

    <form action="userLogin" method="post" id="form" class="w-50 p-4 border rounded">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" onblur="checkEmail()"  />
<!--               pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,}$"-->
<!--               title="Enter a valid email address" />-->

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required minlength="6" />

        <button type="submit">Login</button>
    </form>
</div>

<footer>
    <p>&copy; 2025 Monisha's Portal</p>
</footer>
<script>
    function checkEmail() {
  const email = document.getElementById('email').value;
  if (email !== "") {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:9090/common/loginEmailCheck?email=" + email);
    xhttp.send();

    xhttp.onload = function () {
      document.getElementById("emailError").innerHTML = this.responseText;
      toggleSubmit();
    }
  }
}

function toggleSubmit() {
  const emailError = document.getElementById("emailError").innerText.trim();

  const submitButton = document.querySelector("#form button[type='submit']");

  if (emailError === "") {
    submitButton.disabled = true;
  } else {
    submitButton.disabled = false;
  }
}
</script>

</body>
</html>