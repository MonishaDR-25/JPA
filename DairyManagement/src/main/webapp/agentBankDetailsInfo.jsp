<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Agent Bank Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; margin: 0; }
        .sidebar { height: 100vh; width: 16.666667%; background-color: #2c3e50; padding-top: 20px; color: #fff; position: fixed; top: 0; left: 0; z-index: 1100; }
        .sidebar a { display: block; color: #bdc3c7; padding: 12px 20px; text-decoration: none; transition: 0.2s; }
        .sidebar a:hover, .sidebar a.active { background-color: #1abc9c; color: #fff; border-radius: 8px; }
        .header { padding: 18px 24px; background-color: #fff; border-bottom: 1px solid #eaeaea; display: flex; justify-content: space-between; align-items: center; position: fixed; top: 0; left: 16.666667%; right: 0; z-index: 1200; }
        .main { margin-left: 16.666667%; padding-top: 80px; height: 100vh; display: flex; flex-direction: column; }
        .container-box { padding: 25px; }
        .card { border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .bank-item { padding: 10px 0; border-bottom: 1px solid #eee; }
        .bank-title { font-weight: bold; color: #2c3e50; }
    </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <h3 class="text-center text-white mb-4">Dairy</h3>
    <a href="adminDashboard.jsp">🏠 Dashboard</a>
    <a href="productDashboard">🥛 Products</a>
    <a href="#">🛒 Orders</a>
    <a href="#">👥 Customers</a>
    <a href="#">📊 Reports</a>
    <a href="agentDashboard" class="active">✅ Agents</a>
    <a href="productCollection">🧾 Product Collection</a>
    <a href="productCollectionList.jsp">📋 View Collection List</a>
    <a href="#" class="logout">🚪 Logout</a>
</div>

<!-- Header -->
<div class="header">
    <h4>Welcome, <c:out value="${loggedInAdmin.adminName}"/></h4>
</div>

<!-- Main -->
<div class="main">
    <div class="container-box">
        <!-- Flash Messages -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success text-center">${successMessage}</div>
        </c:if>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger text-center">${errorMessage}</div>
        </c:if>

        <div class="card p-4">
            <div class="d-flex justify-content-between mb-3">
                <h4>Agent Bank Details</h4>

                <!-- OPEN POPUP BUTTON -->
                <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editBankModal">
                    ✏ Edit
                </button>
            </div>

            <c:if test="${empty bankDetails}">
                <div class="alert alert-danger text-center">
                    Bank details not added yet.
                </div>
            </c:if>

            <c:if test="${not empty bankDetails}">
                <div class="bank-item">
                    <span class="bank-title">Bank Name:</span> ${bankDetails.bankName}
                </div>
                <div class="bank-item">
                    <span class="bank-title">Branch Name:</span> ${bankDetails.branchName}
                </div>
                <div class="bank-item">
                    <span class="bank-title">Account Holder Name:</span> ${bankDetails.accountHolderName}
                </div>
                <div class="bank-item">
                    <span class="bank-title">Account Number:</span> ${bankDetails.accountNumber}
                </div>
                <div class="bank-item">
                    <span class="bank-title">IFSC Code:</span> ${bankDetails.ifscCode}
                </div>
                <div class="bank-item">
                    <span class="bank-title">Account Type:</span> ${bankDetails.accountType}
                </div>
            </c:if>

            <div class="mt-4">
                <a href="agentDashboard" class="btn btn-secondary">⬅ Back to Agents</a>
            </div>
        </div>

    </div>
</div>


<!-- ============================= -->
<!--  EDIT BANK DETAILS MODAL     -->
<!-- ============================= -->

<div class="modal fade" id="editBankModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <form action="updateAgentBankDetails" method="post">

                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title">Edit Bank Details</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">

                    <!-- Hidden Fields -->
                    <input type="hidden" name="id" value="${bankDetails.id}">
                    <input type="hidden" name="agentId" value="${bankDetails.agentId}">

                    <div class="mb-3">
                        <label class="form-label">Bank Name</label>
                        <input type="text" name="bankName" class="form-control"
                               value="${bankDetails.bankName}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Branch Name</label>
                        <input type="text" name="branchName" class="form-control"
                               value="${bankDetails.branchName}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Account Holder Name</label>
                        <input type="text" name="accountHolderName" class="form-control"
                               value="${bankDetails.accountHolderName}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Account Number</label>
                        <input type="text" name="accountNumber" class="form-control"
                               value="${bankDetails.accountNumber}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">IFSC Code</label>
                        <input type="text" name="ifscCode" class="form-control"
                               value="${bankDetails.ifscCode}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Account Type</label>
                        <select name="accountType" class="form-select" required>
                            <option ${bankDetails.accountType == 'Savings' ? 'selected' : ''}>Savings</option>
                            <option ${bankDetails.accountType == 'Current' ? 'selected' : ''}>Current</option>
                        </select>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Save Changes</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>

            </form>

        </div>
    </div>
</div>


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
