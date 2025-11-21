<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Collection</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap & Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
        }

        /* Sidebar */
        .sidebar {
            height: 100vh;
            width: 16.666667%;
            background-color: #2c3e50;
            color: #fff;
            position: fixed;
            top: 0;
            left: 0;
            padding-top: 20px;
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
            padding-top: 90px;
            padding-bottom: 40px;
            min-height: 100vh;
        }

        .profile-dropdown img { width:40px; height:40px; border-radius:50%; object-fit:cover; cursor:pointer; }
        .profile-info img { width:80px; height:80px; border-radius:50%; object-fit:cover; margin-bottom:8px; }

        /* Form styling */
        .form-card {
            background: #ffffff;
            border-radius: 14px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.1);
            padding: 30px;
            max-width: 900px;
            margin: auto;
        }

        .form-title {
            background: linear-gradient(90deg, #1abc9c, #16a085);
            color: #fff;
            padding: 12px 20px;
            border-radius: 10px;
            font-size: 1.2rem;
            font-weight: 600;
            margin-bottom: 25px;
            display: flex;
            align-items: center;
        }

        .form-title i {
            margin-right: 10px;
        }

        label {
            font-weight: 500;
        }

        input.form-control, select.form-select {
            border-radius: 8px;
        }

        input[readonly] {
            background-color: #f0f2f5;
        }

        .btn-submit {
            background: linear-gradient(90deg, #1abc9c, #16a085);
            color: #fff;
            border: none;
            font-weight: 600;
            border-radius: 10px;
            padding: 10px 25px;
            transition: all 0.3s ease;
        }

        .btn-submit:hover {
            background: linear-gradient(90deg, #16a085, #0e6655);
            transform: scale(1.03);
        }

        .form-section {
            border: 1px solid #eaeaea;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            background: #fafafa;
        }

        .form-section h6 {
            color: #16a085;
            font-weight: 600;
            margin-bottom: 15px;
        }
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
    <a href="agentDashboard">✅ Agents</a>
    <a href="productCollection.jsp" class="active">💰 Product Collection</a>
    <a href="productCollectionList.jsp">📋 View Collection List</a>
    <a href="#" class="logout">🚪 Logout</a>
</div>

<!-- Header -->
<div class="header">
    <h4>Welcome, <c:out value="${loggedInAdmin.adminName}"/></h4>

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
                <div class="d-flex justify-content-between mt-2">
                    <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editProfileModal">Edit</button>
                    <button class="btn btn-sm btn-secondary" data-bs-dismiss="dropdown">Close</button>
                </div>
            </div>
        </ul>
    </div>
</div>

<!-- Main -->
<div class="main">
    <div class="container-fluid">
        <div class="form-card">
            <div class="form-title">
                <i class="fa-solid fa-milk-bottle"></i> Product Collection Form
            </div>

            <form id="productForm" action="saveProductCollection" method="post">

                <!-- Admin & Agent Section -->
                <div class="form-section">
                    <h6><i class="fa-solid fa-user-shield me-2"></i>Admin & Agent Details</h6>
                    <div class="row g-3">
                        <div class="col-md-4">
                            <label for="adminName">Admin Name</label>
                            <input type="text" id="adminName" name="adminName" class="form-control" value="${loggedInAdmin.adminName}" readonly>
                        </div>

                        <div class="col-md-4">
                            <label for="agentPhone">Agent Phone Number</label>
                            <input type="text" id="agentPhone" name="phoneNumber" class="form-control" required>
                        </div>

                        <div class="col-md-4">
                            <label for="agentName">Agent Name</label>
                            <input type="text" id="agentName" name="agentName" class="form-control" readonly>
                        </div>

                        <div class="col-md-6">
                            <label for="agentEmail">Agent Email</label>
                            <input type="email" id="agentEmail" name="email" class="form-control" readonly>
                        </div>
                    </div>
                </div>

                <!-- Milk Section -->
                <div class="form-section">
                    <h6><i class="fa-solid fa-cow me-2"></i>Milk Details</h6>
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label for="milkType">Type of Milk</label>
                            <select id="milkType" name="typeOfMilk" class="form-select" required>
                                <option value="">-- Select Milk Type --</option>
                                <c:forEach items="${products}" var="product">
                                    <option value="${product.productName}" data-price="${product.productPrice}">
                                        ${product.productName} - ₹${product.productPrice}/L
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label for="pricePerLiter">Price Per Liter (₹)</label>
                            <input type="text" id="pricePerLiter" name="price" class="form-control" readonly>
                        </div>

                        <div class="col-md-6">
                            <label for="quantity">Quantity (Liters)</label>
                            <input type="number" id="quantity" name="quantity" class="form-control" min="1" required>
                        </div>

                        <div class="col-md-6">
                            <label for="totalAmount">Total Amount (₹)</label>
                            <input type="text" id="totalAmount" name="totalAmount" class="form-control" readonly>
                        </div>
                    </div>
                </div>

                <!-- Submit -->
                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-submit">
                        <i class="fa-solid fa-circle-check me-2"></i>Submit Collection
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Fetch agent details by phone
    $("#agentPhone").on("blur", function() {
        const phone = $(this).val();
        if (phone) {
            $.ajax({
                url: "productCollection/getAgentByPhone",
                type: "GET",
                data: { phoneNumber: phone },
                success: function(response) {
                    if (response && response.firstName) {
                        $("#agentName").val(response.firstName + " " + (response.lastName || ""));
                        $("#agentEmail").val(response.email);
                    } else {
                        alert("No agent found with this phone number!");
                        $("#agentName").val("");
                        $("#agentEmail").val("");
                    }
                },
                error: function() {
                    alert("Error fetching agent details!");
                }
            });
        }
    });

    // Auto price on milk selection
    $("#milkType").on("change", function() {
        const price = $(this).find(":selected").data("price") || "";
        $("#pricePerLiter").val(price);
        $("#quantity").trigger("input");
    });

    // Auto calculate total
    $("#quantity").on("input", function() {
        const price = parseFloat($("#pricePerLiter").val());
        const qty = parseFloat($(this).val());
        if (!isNaN(price) && !isNaN(qty)) {
            $("#totalAmount").val((price * qty).toFixed(2));
        } else {
            $("#totalAmount").val("");
        }
    });
</script>

</body>
</html>
