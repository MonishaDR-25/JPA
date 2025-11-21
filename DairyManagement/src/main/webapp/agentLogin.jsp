<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Login | Farmer Fresh Family</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        html, body {
            height: 100%;
            margin: 0;
        }
        body {
            display: flex;
            flex-direction: column;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            padding-top: 90px;
        }
        .content {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .navbar {
            background-color: #495057;
        }
        .navbar-brand img {
            height: 70px;
            margin-right: 10px;
        }
        .navbar-brand {
            font-size: 1.5rem;
            font-weight: bold;
            color: #f8f9fa !important;
        }
        .navbar-nav .nav-link {
            color: #f8f9fa !important;
            font-weight: 500;
        }
        .navbar-nav .nav-link:hover {
            color: #adb5bd !important;
        }
        footer {
            background-color: #495057;
            color: #dee2e6;
            padding: 15px 0;
            text-align: center;
        }
        .login-card {
            width: 100%;
            max-width: 420px;
            padding: 30px;
            border-radius: 12px;
            background: #fff;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.1);
        }
        .login-card h3 {
            font-weight: bold;
            margin-bottom: 20px;
            color: #343a40;
            text-align: center;
        }
        .btn-custom {
            background-color: #495057;
            color: white;
            font-weight: 600;
        }
        .btn-custom:hover {
            background-color: #343a40;
        }
        .form-text {
            font-size: 0.9rem;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">
            <img src="https://dcassetcdn.com/design_img/3810229/80753/23541915/8qxg6nf119f3636ev6e4xbnqf4_image.png" alt="Logo">
            Farmer Fresh Family
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="index.jsp#products">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="index.jsp#about">About Us</a></li>
                <li class="nav-item"><a class="nav-link" href="index.jsp#contact">Contact</a></li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Login
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="adminLoginForm">Admin Login</a></li>
                        <li><a class="dropdown-item" href="customerLogin.jsp">Customer Login</a></li>
                        <li><a class="dropdown-item" href="agentLogin">Agent Login</a></li>
                        <li><a class="dropdown-item" href="Register.jsp">Registration</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Main Content -->
<div class="content">
    <div class="login-card">
        <h3>Agent Login</h3>

        <div id="messageBox"></div>

        <form id="agentLoginForm" method="post">
            <div class="mb-3">
                <label class="form-label">Email Address</label>
                <input type="email" id="emailId" name="emailId" class="form-control" placeholder="Enter email" required>
                <div id="emailFeedback" class="form-text text-danger"></div>
            </div>

            <div class="mb-3 text-center">
                <button type="button" id="sendOtpBtn" class="btn btn-custom w-100" disabled>Send OTP</button>
            </div>

            <div class="mb-3" id="otpSection" style="display:none;">
                <label class="form-label">Enter OTP</label>
                <input type="text" id="otp" name="otp" class="form-control" placeholder="Enter OTP" maxlength="6" required>
            </div>

            <button type="submit" id="loginBtn" class="btn btn-custom w-100" style="display:none;">Login</button>
        </form>
    </div>
</div>

<!-- Footer -->
<footer>
    <p>&copy; 2025 Farmer Fresh Family | All Rights Reserved</p>
</footer>

<!-- Bootstrap + AJAX Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
    $(document).ready(function () {
        // Validate email via AJAX
        $("#emailId").on("blur", function () {
            const email = $(this).val().trim();
            $("#emailFeedback").text("");
            $("#sendOtpBtn").prop("disabled", true);
            if (email !== "") {
                $.ajax({
                    url: "validateAgentEmail",
                    type: "POST",
                    data: { emailId: email },
                    success: function (response) {
                        if (response === "valid") {
                            $("#emailFeedback").html("<span class='text-success'>Email found. You can request OTP.</span>");
                            $("#sendOtpBtn").prop("disabled", false);
                        } else {
                            $("#emailFeedback").text("Email not registered. Please check or register first.");
                        }
                    },
                    error: function () {
                        $("#emailFeedback").text("Error checking email. Try again later.");
                    }
                });
            }
        });

        // Send OTP
        $("#sendOtpBtn").click(function () {
            const email = $("#emailId").val().trim();
            $.ajax({
                url: "sendAgentOtp",
                type: "POST",
                data: { emailId: email },
                success: function (response) {
                    if (response === "sent") {
                        $("#messageBox").html("<div class='alert alert-success text-center rounded-3'>OTP sent successfully to your email.</div>");
                        $("#otpSection").slideDown();
                        $("#loginBtn").fadeIn();
                    } else {
                        $("#messageBox").html("<div class='alert alert-danger text-center rounded-3'>Failed to send OTP. Please try again.</div>");
                    }
                },
                error: function () {
                    $("#messageBox").html("<div class='alert alert-danger text-center rounded-3'>Server error. Try again later.</div>");
                }
            });
        });

        // Handle Login with OTP
        $("#agentLoginForm").on("submit", function (e) {
            e.preventDefault();
            const email = $("#emailId").val().trim();
            const otp = $("#otp").val().trim();

            $.ajax({
                url: "agentLogin",
                type: "POST",
                data: { emailId: email, otp: otp },
                success: function (response) {
                    if (response === "success") {
                        $("#messageBox").html("<div class='alert alert-success text-center rounded-3'>Login successful! Redirecting...</div>");
                        setTimeout(() => window.location.href = "agentLoginSuccess", 1500);
                    } else {
                        $("#messageBox").html("<div class='alert alert-danger text-center rounded-3'>Invalid OTP. Please try again.</div>");
                    }
                },
                error: function () {
                    $("#messageBox").html("<div class='alert alert-danger text-center rounded-3'>Server error. Try again later.</div>");
                }
            });
        });
    });
</script>

</body>
</html>
