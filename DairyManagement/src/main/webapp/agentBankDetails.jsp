<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bank Details | Farmer Fresh Family</title>
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

        /* Navbar */
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
        .nav-link:hover {
            color: #adb5bd !important;
        }

        /* Card */
        .bank-card {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.08);
            padding: 30px;
            max-width: 700px;
            margin: 100px auto;
        }
        .btn-custom {
            background-color: #495057;
            color: #fff;
            font-weight: 600;
        }
        .btn-custom:hover {
            background-color: #343a40;
        }

        footer {
            background-color: #495057;
            color: #dee2e6;
            text-align: center;
            padding: 12px;
            margin-top: auto;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="agentLoginSuccess">
            <img src="https://dcassetcdn.com/design_img/3810229/80753/23541915/8qxg6nf119f3636ev6e4xbnqf4_image.png" alt="Logo">
            Farmer Fresh Family
        </a>
        <div class="ms-auto">
            <a href="agentLoginSuccess" class="btn btn-outline-light btn-sm me-2">Back to Profile</a>
            <a href="logout" class="btn btn-sm btn-danger">Logout</a>
        </div>
    </div>
</nav>

<!-- Bank Details Form -->
<div class="bank-card">
    <h4 class="text-center mb-4">Add Bank Details</h4>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success text-center">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger text-center">${errorMessage}</div>
    </c:if>

    <form action="saveBankDetails" method="post">
        <input type="hidden" name="agentId" value="${loggedInAgent.agentId}">

        <div class="row mb-3">
            <div class="col-md-6">
                <label class="form-label">Bank Name</label>
                <input type="text" name="bankName" class="form-control" placeholder="Enter bank name" required>
            </div>
            <div class="col-md-6">
                <label class="form-label">Branch Name</label>
                <input type="text" name="branchName" class="form-control" placeholder="Enter branch name" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label class="form-label">Account Holder Name</label>
                <input type="text" name="accountHolderName" class="form-control" placeholder="Enter account holder name" required>
            </div>
            <div class="col-md-6">
                <label class="form-label">Account Type</label>
                <select name="accountType" class="form-select" required>
                    <option value="">Select Account Type</option>
                    <option value="Savings">Savings</option>
                    <option value="Current">Current</option>
                </select>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label class="form-label">Account Number</label>
                <input type="text" name="accountNumber" class="form-control" placeholder="Enter account number"  required>
            </div>
            <div class="col-md-6">
                <label class="form-label">Confirm Account Number</label>
                <input type="text" name="confirmAccountNumber" class="form-control" placeholder="Re-enter account number" required>
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label">IFSC Code</label>
            <input type="text" name="ifscCode" class="form-control text-uppercase" placeholder="Enter IFSC code (11 chars)" maxlength="11" required>
            <small class="text-muted">Ensure the IFSC code is exactly 11 characters.</small>
        </div>

        <div class="alert alert-warning mt-3 text-center">
            <strong>Note:</strong> Bank details can be submitted only once.
            For any future corrections, please contact your bank administrator.
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-custom px-5 mt-2">Save Bank Details</button>
        </div>
    </form>
</div>

<!-- Footer -->
<footer>
    <p>&copy; 2025 Farmer Fresh Family | All Rights Reserved</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
