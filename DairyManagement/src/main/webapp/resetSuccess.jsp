<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Reset Success | Dairy Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            padding-top: 90px; /* space for fixed navbar */
        }
        .navbar {
            background-color: #2c3e50;
        }
        .navbar-brand img {
            height: 70px;
            margin-right: 10px;
        }
        .navbar-brand {
            font-size: 1.5rem;
            font-weight: bold;
            color: #ecf0f1 !important;
        }
        .navbar-nav .nav-link {
            color: #ecf0f1 !important;
            font-weight: 500;
        }
        .navbar-nav .nav-link:hover {
            color: #f39c12 !important;
        }
        footer {
            background-color: #2c3e50;
            color: #bdc3c7;
            padding: 15px 0;
            text-align: center;
            margin-top: 50px;
        }
        /* Success Card Styling */
        .success-card {
            max-width: 480px;
            margin: auto;
            padding: 30px;
            border-radius: 12px;
            background: #fff;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.1);
            text-align: center;
        }
        .success-card h3 {
            font-weight: bold;
            margin-bottom: 15px;
            color: #27ae60;
        }
        .success-card p {
            font-size: 1rem;
            color: #555;
            margin-bottom: 25px;
        }
        .btn-login {
            background-color: #2c3e50;
            color: white;
            font-weight: 600;
        }
        .btn-login:hover {
            background-color: #34495e;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">
            <img src="https://dcassetcdn.com/design_img/3810229/80753/23541915/8qxg6nf119f3636ev6e4xbnqf4_image.png" alt="Logo">
            Dairy Management
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

                <!-- Login Dropdown -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Login
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="adminLogin.jsp">Admin Login</a></li>
                        <li><a class="dropdown-item" href="customerLogin.jsp">Customer Login</a></li>
                        <li><a class="dropdown-item" href="agentLogin.jsp">Agent Login</a></li>
                        <li><a class="dropdown-item" href="registration.jsp">Registration</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Success Section -->
<div class="container my-5">
    <div class="success-card">
        <h3>✅ Password Reset Successful</h3>
        <p>Your password has been reset successfully. You can now login with your new credentials.</p>
        <a href="adminLoginForm.jsp" class="btn btn-login w-100">Go to Login</a>
    </div>
</div>

<!-- Footer -->
<footer>
    <p>&copy; 2025 Dairy Management System | All Rights Reserved</p>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
