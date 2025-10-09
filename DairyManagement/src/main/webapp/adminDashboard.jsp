<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agents Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .sidebar {
            height: 100vh;
            background-color: #2c3e50;
            padding-top: 20px;
            color: #fff;
        }
        .sidebar a {
            display: block;
            color: #bdc3c7;
            padding: 12px 20px;
            text-decoration: none;
            transition: 0.3s;
        }
        .sidebar a:hover,
        .sidebar a.active {
            background-color: #1abc9c;
            color: #fff;
            border-radius: 8px;
        }
        .logout {
            color: #e74c3c !important;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .header {
            padding: 20px;
            background-color: #fff;
            border-bottom: 1px solid #eaeaea;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .profile-dropdown img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            object-fit: cover;
            cursor: pointer;
        }
        .dropdown-menu {
            border-radius: 12px;
            padding: 15px;
            min-width: 250px;
        }
        .profile-info {
            text-align: center;
        }
        .profile-info img {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 10px;
        }
        .new-agent-btn {
            margin-bottom: 15px;
        }
        table thead {
            background-color: #2c3e50;
            color: white;
        }
        .btn-sm {
            padding: 4px 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">

        <!-- Sidebar -->
        <div class="col-md-2 sidebar">
            <h3 class="text-center text-white mb-4">Dairy</h3>
            <a href="adminDashboard.jsp">🏠 Dashboard</a>
            <a href="productDashboard">🥛 Products</a>
            <a href="#">🛒 Orders</a>
            <a href="#">👥 Customers</a>
            <a href="#">📊 Reports</a>
            <a href="agentDashboard">✅ Agents</a>
            <a href="#" class="logout">🚪 Logout</a>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-0">

            <!-- Header -->
            <div class="header">
                <h4>Welcome, <c:out value="${loggedInAdmin.adminName}"/></h4>

                <!-- Profile Dropdown -->
                <div class="dropdown profile-dropdown">
                    <c:set var="profilePic"
                           value="${not empty loggedInAdmin.profilePicPath ? loggedInAdmin.profilePicPath : 'images/default.png'}"/>
                    <img src="/DairyManagement/${profilePic}?t=<%=System.currentTimeMillis()%>"
                         alt="Profile" id="profileDropdown"
                         data-bs-toggle="dropdown" aria-expanded="false">
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileDropdown">
                        <div class="profile-info">
                            <img src="/DairyManagement/${profilePic}?t=<%=System.currentTimeMillis()%>"
                                 alt="Profile Picture">
                            <h5><c:out value="${loggedInAdmin.adminName}"/></h5>
                            <p><strong>📞</strong> <c:out value="${loggedInAdmin.phoneNumber}"/></p>
                            <p><strong>✉️</strong> <c:out value="${loggedInAdmin.emailId}"/></p>
                            <div class="d-flex justify-content-between mt-3">
                                <button class="btn btn-sm btn-primary" data-bs-toggle="modal"
                                        data-bs-target="#editProfileModal">Edit</button>
                                <button class="btn btn-sm btn-secondary" data-bs-toggle="dropdown">Close</button>
                            </div>
                        </div>
                    </ul>
                </div>
            </div>

            <!-- Dashboard Cards -->
            <div class="container mt-4">
                <div class="row g-4">
                    <div class="col-md-4">
                        <div class="card text-center p-4" style="border-left: 6px solid #27ae60;">
                            <h5>Total Products</h5>
                            <h2 class="text-success">150</h2>
                            <p>Dairy products currently listed</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-center p-4" style="border-left: 6px solid #2980b9;">
                            <h5>Pending Orders</h5>
                            <h2 class="text-primary">60</h2>
                            <p>Orders waiting for processing</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-center p-4" style="border-left: 6px solid #f39c12;">
                            <h5>Customers</h5>
                            <h2 class="text-warning">420</h2>
                            <p>Registered customers</p>
                        </div>
                    </div>
                </div>
            </div>

<!-- Edit Profile Modal -->
<div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h5 class="modal-title" id="editProfileModalLabel">Edit Profile</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <!-- Modal Body -->
            <div class="modal-body">
                <form action="updateProfile" method="post" enctype="multipart/form-data" class="row g-3">

                    <!-- Profile Image Preview -->
                    <div class="col-12 text-center mb-3">
                        <img id="previewImg"
                             src="/DairyManagement/${profilePic}?t=<%=System.currentTimeMillis()%>"
                             alt="Profile"
                             class="rounded-circle shadow"
                             width="100"
                             height="100">
                    </div>

                    <!-- Full Name -->
                    <div class="col-md-12">
                        <label for="adminName" class="form-label">Full Name</label>
                        <input type="text" class="form-control" id="adminName" name="adminName"
                               value="${loggedInAdmin.adminName}" required>
                    </div>

                    <!-- Phone Number -->
                    <div class="col-md-12">
                        <label for="phoneNumber" class="form-label">Phone Number</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                               value="${loggedInAdmin.phoneNumber}" required>
                    </div>

                    <!-- Email -->
                    <div class="col-md-12">
                        <label for="emailId" class="form-label">Email</label>
                        <input type="email" class="form-control" id="emailId" name="emailId"
                               value="${loggedInAdmin.emailId}" required>
                    </div>

                    <!-- Profile Picture Upload -->
                    <div class="col-md-12">
                        <label for="profilePicPath" class="form-label">Profile Picture</label>
                        <input type="file" class="form-control" id="profilePicPath" name="profilePicPath"
                               accept="image/*" onchange="previewImage(event)">
                    </div>

                    <!-- Modal Footer -->
                    <div class="col-12 d-flex justify-content-end mt-3">
                        <button type="submit" class="btn btn-success me-2">💾 Save Changes</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">❌ Cancel</button>
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function previewImage(event) {
        const input = event.target;
        const reader = new FileReader();
        reader.onload = function () {
            const imgElement = document.getElementById('previewImg');
            imgElement.src = reader.result;
        };
        if (input.files[0]) {
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>
