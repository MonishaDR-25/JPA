<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Bank Details | Farmer Fresh Family</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            display: flex;
            min-height: 100vh;
            flex-direction: column;
        }

        .navbar {
            background-color: #495057;
        }
        .navbar-brand img {
            height: 55px;
            margin-right: 10px;
        }
        .navbar-brand, .nav-link {
            color: #f8f9fa !important;
            font-weight: 500;
        }

        .dashboard-container {
            display: flex;
            flex: 1;
            margin-top: 70px;
        }
        .sidebar {
            width: 250px;
            background-color: #343a40;
            color: #dee2e6;
            padding-top: 20px;
            position: fixed;
            height: calc(100vh - 70px);
            overflow-y: auto;
        }
        .sidebar h5 {
            text-align: center;
            color: #f8f9fa;
            font-weight: 600;
            margin-bottom: 30px;
        }
        .sidebar a {
            display: block;
            color: #dee2e6;
            padding: 12px 20px;
            text-decoration: none;
            border-radius: 8px;
            margin: 4px 12px;
        }
        .sidebar a:hover, .sidebar a.active {
            background-color: #495057;
            color: #fff;
        }

        .main-content {
            flex: 1;
            margin-left: 250px;
            padding: 40px;
        }

        .bank-card {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.08);
            padding: 30px;
            max-width: 700px;
            margin: 0 auto;
        }

        .bank-header h3 {
            font-weight: 600;
            color: #444;
        }

        .bank-details label {
            font-weight: 600;
            color: #444;
        }

        .bank-details p {
            background: #f1f3f5;
            padding: 12px;
            border-radius: 8px;
            border: 1px solid #dee2e6;
        }

        footer {
            background-color: #495057;
            color: #dee2e6;
            text-align: center;
            padding: 12px;
            margin-top: auto;
        }

        @media (max-width: 992px) {
            .sidebar {
                position: static;
                width: 100%;
                height: auto;
            }
            .main-content {
                margin-left: 0;
                padding: 20px;
            }
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://dcassetcdn.com/design_img/3810229/80753/23541915/8qxg6nf119f3636ev6e4xbnqf4_image.png" alt="Logo">
            Farmer Fresh Family
        </a>
        <div class="ms-auto">
            <a href="logout" class="btn btn-sm btn-danger">Logout</a>
        </div>
    </div>
</nav>

<div class="dashboard-container">

    <div class="sidebar">
        <h5>Agent Panel</h5>
        <a href="agentLoginSuccess">👤 Profile</a>
        <a href="agentBankDetails">🏦 Bank Details</a>
        <a href="#collectionsTab">🥛 Collections</a>
        <a href="#paymentsTab">💰 Payments</a>
    </div>

    <div class="main-content">

        <div class="bank-card">
            <div class="bank-header text-center mb-4">
                <h3>Bank Details</h3>
                <hr style="width:80px; margin:auto; border:2px solid #495057;">
            </div>

            <c:choose>

                <c:when test="${not empty bankDetails}">
                    <div class="bank-details">

                        <div class="mb-3">
                            <label>Bank Name</label>
                            <p>${bankDetails.bankName}</p>
                        </div>

                        <div class="mb-3">
                            <label>Branch Name</label>
                            <p>${bankDetails.branchName}</p>
                        </div>

                        <div class="mb-3">
                            <label>Account Holder Name</label>
                            <p>${bankDetails.accountHolderName}</p>
                        </div>

                        <div class="mb-3">
                            <label>Account Number</label>
                            <p>${bankDetails.accountNumber}</p>
                        </div>

                        <div class="mb-3">
                            <label>IFSC Code</label>
                            <p>${bankDetails.ifscCode}</p>
                        </div>

                        <div class="mb-3">
                            <label>Account Type</label>
                            <p>${bankDetails.accountType}</p>
                        </div>

                        <div class="text-center mt-4">
                            <a href="agentLoginSuccess" class="btn btn-secondary px-4">⬅ Back to Dashboard</a>
                        </div>

                    </div>
                </c:when>

                <c:otherwise>
                    <div class="text-center">
                        <p class="text-muted fs-5 mt-3">No bank details found. Please add your bank details.</p>
                        <a href="agentBankDetails" class="btn btn-dark mt-3 px-4">+ Add Bank Details</a>
                    </div>
                </c:otherwise>

            </c:choose>
        </div>

    </div>
</div>

<footer>
    <p>&copy; 2025 Farmer Fresh Family | All Rights Reserved</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
