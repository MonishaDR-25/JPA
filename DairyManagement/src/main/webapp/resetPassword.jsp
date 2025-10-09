<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password | Dairy Management System</title>
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
        /* Reset Password Card Styling */
        .reset-card {
            max-width: 450px;
            margin: auto;
            padding: 30px;
            border-radius: 12px;
            background: #fff;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.1);
        }
        .reset-card h3 {
            font-weight: bold;
            margin-bottom: 20px;
            color: #2c3e50;
            text-align: center;
        }
        .btn-reset {
            background-color: #2c3e50;
            color: white;
            font-weight: 600;
        }
        .btn-reset:hover {
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

<!-- Reset Password Section -->
<div class="container my-5">
    <div class="reset-card">
        <h3>🔑 Reset Password</h3>
        <c:if test="${not empty message}">
            <div class="alert alert-info text-center rounded-3">
                ${message}
            </div>
        </c:if>
        <form action="resetPassword" method="post">
            <input type="hidden" name="email" value="${email}" />
            <div class="mb-3">
                <label class="form-label">New Password</label>
                <input type="password" name="password" class="form-control" placeholder="Enter new password" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Confirm Password</label>
                <input type="password" name="confirmPassword" class="form-control" placeholder="Re-enter new password" required>
            </div>
            <button type="submit" class="btn btn-reset w-100">Reset Password</button>
        </form>
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
