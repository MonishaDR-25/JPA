<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Agent Profile</title>
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
            left: 16.666667%; /* same as sidebar width */
            right: 0;
            z-index: 1200;
        }

        /* Main content wrapper */
        .main {
            margin-left: 16.666667%;
            padding-top: 100px; /* avoid header overlap */
            padding-bottom: 40px;
        }

        .card { border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .error-msg { color: #d63384; display: none; font-size: 0.9rem; }

        /* Profile dropdown */
        .profile-dropdown img { width:40px; height:40px; border-radius:50%; object-fit:cover; cursor:pointer; }
        .profile-info img { width:80px; height:80px; border-radius:50%; object-fit:cover; margin-bottom: 8px; }

        /* ensure modal overlays above header */
        .modal { z-index: 2000; }

        /* Responsive fix for sidebar */
        @media (max-width: 992px) {
            .sidebar { width: 220px; position: relative; height: auto; }
            .header { left: 0; }
            .main { margin-left: 0; padding-top: 120px; }
        }
    </style>
</head>
<body>

<!-- sidebar -->
<div class="sidebar">
    <h3 class="text-center text-white mb-4">Dairy</h3>
    <a href="adminDashboard.jsp">🏠 Dashboard</a>
    <a href="#">🥛 Products</a>
    <a href="#">🛒 Orders</a>
    <a href="#">👥 Customers</a>
    <a href="#">📊 Reports</a>
    <a href="agentDashboard" class="active">✅ Agents</a>
    <a href="#" class="logout">🚪 Logout</a>
</div>

<!-- header -->
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
                <div class="d-flex justify-content-between mt-2">
                    <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editProfileModal">Edit</button>
                    <button class="btn btn-sm btn-secondary" data-bs-dismiss="dropdown">Close</button>
                </div>
            </div>
        </ul>
    </div>
</div>

<!-- main content -->
<div class="main container">
    <div class="row justify-content-center">
        <div class="col-lg-10 col-xl-8">
            <div class="card p-4">
                <h3 class="form-title text-center mb-4">✏️ Edit Agent Profile</h3>

                <form id="editAgentForm" method="post" action="updateAgent" enctype="multipart/form-data">
                    <input type="hidden" name="agentId" value="${agent.agentId}">

                    <div class="row g-3">
                        <div class="col-md-6">
                            <label for="firstName" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="firstName"
                                   value="${agent.firstName}" required>
                            <div id="firstNameError" class="error-msg">First name must be at least 3 letters</div>
                        </div>

                        <div class="col-md-6">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName"
                                   value="${agent.lastName}" required>
                            <div id="lastNameError" class="error-msg">Last name must be at least 3 letters</div>
                        </div>

                        <div class="col-md-6">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email"
                                   value="${agent.email}" required>
                            <div id="emailError" class="error-msg">Enter a valid email (must contain @)</div>
                        </div>

                        <div class="col-md-6">
                            <label for="phone" class="form-label">Phone Number</label>
                            <input type="text" class="form-control" id="phone" name="phoneNumber"
                                   value="${agent.phoneNumber}" required>
                            <div id="phoneError" class="error-msg">Phone number must be 10 digits</div>
                        </div>

                        <div class="col-12">
                            <label for="address" class="form-label">Address</label>
                            <textarea class="form-control" id="address" name="address" rows="2" required>${agent.address}</textarea>
                            <div id="addressError" class="error-msg">Address cannot be empty</div>
                        </div>

                        <div class="col-md-6">
                            <label for="milkType" class="form-label">Types of Milk</label>
                            <select class="form-select" id="milkType" name="milkType" required>
                                <option value="">-- Select Milk Type --</option>
                                <option value="Full Cream" <c:if test="${agent.milkType eq 'Full Cream'}">selected</c:if>>Full Cream</option>
                                <option value="Toned Milk" <c:if test="${agent.milkType eq 'Toned Milk'}">selected</c:if>>Toned Milk</option>
                                <option value="Double Toned Milk" <c:if test="${agent.milkType eq 'Double Toned Milk'}">selected</c:if>>Double Toned Milk</option>
                                <option value="Skimmed Milk" <c:if test="${agent.milkType eq 'Skimmed Milk'}">selected</c:if>>Skimmed Milk</option>
                                <option value="Organic Milk" <c:if test="${agent.milkType eq 'Organic Milk'}">selected</c:if>>Organic Milk</option>
                            </select>
                            <div id="milkTypeError" class="error-msg">Please select a milk type</div>
                        </div>



                    <div class="mt-4 d-flex justify-content-between">
                        <a href="agentDashboard" class="btn btn-secondary">⬅ Back</a>
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
    // Validation
    document.addEventListener('DOMContentLoaded', function () {
        function show(id){ document.getElementById(id).style.display = 'block'; }
        function hide(id){ document.getElementById(id).style.display = 'none'; }

        function validateFirstName(){
            const v = document.getElementById('firstName').value.trim();
            if(v.length < 3){ show('firstNameError'); return false; }
            hide('firstNameError'); return true;
        }
        function validateLastName(){
            const v = document.getElementById('lastName').value.trim();
            if(v.length < 3){ show('lastNameError'); return false; }
            hide('lastNameError'); return true;
        }
        function validateEmail(){
            const v = document.getElementById('email').value.trim();
            if(!v.includes('@')){ show('emailError'); return false; }
            hide('emailError'); return true;
        }
        function validatePhone(){
            const v = document.getElementById('phone').value.trim();
            if(!/^[0-9]{10}$/.test(v)){ show('phoneError'); return false; }
            hide('phoneError'); return true;
        }
        function validateAddress(){
            const v = document.getElementById('address').value.trim();
            if(v === ''){ show('addressError'); return false; }
            hide('addressError'); return true;
        }
        function validateMilkType(){
            const v = document.getElementById('milkType').value;
            if(v === ''){ show('milkTypeError'); return false; }
            hide('milkTypeError'); return true;
        }

        const form = document.getElementById('editAgentForm');
        form.addEventListener('submit', function(e){
            if(!(validateFirstName() && validateLastName() && validateEmail() &&
                 validatePhone() && validateAddress() && validateMilkType())){
                e.preventDefault();
            }
        });

        document.getElementById('firstName').addEventListener('input', validateFirstName);
        document.getElementById('lastName').addEventListener('input', validateLastName);
        document.getElementById('email').addEventListener('input', validateEmail);
        document.getElementById('phone').addEventListener('input', validatePhone);
        document.getElementById('address').addEventListener('input', validateAddress);
        document.getElementById('milkType').addEventListener('change', validateMilkType);
    });
</script>
</body>
</html>
