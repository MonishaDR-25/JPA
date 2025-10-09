<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; margin: 0; }

        /* Sidebar */
        .sidebar {
            height: 100vh;
            width: 16.666667%;
            background-color: #2c3e50;
            padding-top: 20px;
            color: #fff;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1100;
        }
        .sidebar a {
            display: block;
            color: #bdc3c7;
            padding: 12px 20px;
            text-decoration: none;
            transition: 0.2s;
        }
        .sidebar a:hover, .sidebar a.active {
            background-color: #1abc9c;
            color: #fff;
            border-radius: 8px;
        }

        /* Header */
        .header {
            padding: 18px 24px;
            background-color: #fff;
            border-bottom: 1px solid #eaeaea;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: fixed;
            top: 0;
            left: 16.666667%;
            right: 0;
            z-index: 1200;
        }

        /* Main */
        .main {
            margin-left: 16.666667%;
            padding-top: 100px;
            padding-bottom: 40px;
        }

        .card { border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .error-msg { color: #d63384; display: none; font-size: 0.9rem; }

        /* Profile dropdown */
        .profile-dropdown img { width:40px; height:40px; border-radius:50%; object-fit:cover; cursor:pointer; }
        .profile-info img { width:80px; height:80px; border-radius:50%; object-fit:cover; margin-bottom: 8px; }

        /* Responsive fix */
        @media (max-width: 992px) {
            .sidebar { width: 220px; position: relative; height: auto; }
            .header { left: 0; }
            .main { margin-left: 0; padding-top: 120px; }
        }
    </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <h3 class="text-center text-white mb-4">Dairy</h3>
    <a href="adminDashboard.jsp">🏠 Dashboard</a>
    <a href="#" class="active">🥛 Products</a>
    <a href="#">🛒 Orders</a>
    <a href="#">👥 Customers</a>
    <a href="#">📊 Reports</a>
    <a href="agentDashboard">✅ Agents</a>
    <a href="#" class="logout">🚪 Logout</a>
</div>

<!-- Header -->
<div class="header">
    <h4>Welcome, <c:out value="${loggedInAdmin.adminName}"/></h4>

    <!-- Profile Dropdown -->
    <div class="dropdown profile-dropdown">
        <c:set var="profilePic" value="${not empty loggedInAdmin.profilePicPath ? loggedInAdmin.profilePicPath : 'images/default.png'}"/>
        <img src="${pageContext.request.contextPath}/${profilePic}?t=<%= System.currentTimeMillis() %>"
             alt="Profile" id="profileDropdown" data-bs-toggle="dropdown" aria-expanded="false">
        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileDropdown">
            <div class="profile-info p-3 text-center">
                <img src="${pageContext.request.contextPath}/${profilePic}?t=<%= System.currentTimeMillis() %>" alt="Profile Picture">
                <h5><c:out value="${loggedInAdmin.adminName}"/></h5>
                <p><strong>📞</strong> <c:out value="${loggedInAdmin.phoneNumber}"/></p>
                <p><strong>✉️</strong> <c:out value="${loggedInAdmin.emailId}"/></p>
            </div>
        </ul>
    </div>
</div>

<!-- Main Content -->
<div class="main container">
    <div class="row justify-content-center">
        <div class="col-lg-10 col-xl-8">
            <div class="card p-4">
                <h3 class="form-title text-center mb-4">✏️ Edit Product</h3>

                <form id="editProductForm" method="post" action="updateProduct">
                    <input type="hidden" name="productId" value="${product.productId}">

                    <div class="row g-3">
                        <div class="col-md-6">
                            <label for="productName" class="form-label">Product Name</label>
                            <input type="text" class="form-control" id="productName" name="productName"
                                   value="${product.productName}" required>
                            <div id="productNameError" class="error-msg">Product name must be at least 3 characters</div>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label d-block">Product Type</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="buyOption" name="productType"
                                       value="Buy" <c:if test="${product.productType eq 'Buy'}">checked</c:if> required>
                                <label class="form-check-label" for="buyOption">Buy</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="sellOption" name="productType"
                                       value="Sell" <c:if test="${product.productType eq 'Sell'}">checked</c:if> required>
                                <label class="form-check-label" for="sellOption">Sell</label>
                            </div>
                            <div id="productTypeError" class="error-msg">Please select Buy or Sell</div>
                        </div>

                        <div class="col-md-6">
                            <label for="productPrice" class="form-label">Price</label>
                            <input type="number" class="form-control" id="productPrice" name="productPrice"
                                   value="${product.productPrice}" step="0.01" required>
                            <div id="productPriceError" class="error-msg">Enter a valid price (greater than 0)</div>
                        </div>
                    </div>

                    <div class="mt-4 d-flex justify-content-between">
                        <a href="productDashboard.jsp" class="btn btn-secondary">⬅ Back</a>
                        <button type="submit" class="btn btn-primary">💾 Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        function show(id){ document.getElementById(id).style.display = 'block'; }
        function hide(id){ document.getElementById(id).style.display = 'none'; }

        function validateProductName(){
            const v = document.getElementById('productName').value.trim();
            if(v.length < 3){ show('productNameError'); return false; }
            hide('productNameError'); return true;
        }
        function validateProductType(){
            const radios = document.getElementsByName('productType');
            let checked = false;
            for(let r of radios){ if(r.checked) { checked = true; break; } }
            if(!checked){ show('productTypeError'); return false; }
            hide('productTypeError'); return true;
        }
        function validateProductPrice(){
            const v = parseFloat(document.getElementById('productPrice').value);
            if(isNaN(v) || v <= 0){ show('productPriceError'); return false; }
            hide('productPriceError'); return true;
        }

        const form = document.getElementById('editProductForm');
        form.addEventListener('submit', function(e){
            if(!(validateProductName() && validateProductType() && validateProductPrice())){
                e.preventDefault();
            }
        });

        document.getElementById('productName').addEventListener('input', validateProductName);
        document.getElementById('productPrice').addEventListener('input', validateProductPrice);
        const radios = document.getElementsByName('productType');
        radios.forEach(r => r.addEventListener('change', validateProductType));
    });
</script>
</body>
</html>
