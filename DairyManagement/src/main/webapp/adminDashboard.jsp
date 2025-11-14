<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; }

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
        .sidebar a:hover, .sidebar a.active {
            background-color: #1abc9c;
            color: #fff;
            border-radius: 8px;
        }
        .logout { color: #e74c3c !important; }

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

        table thead {
            background-color: #2c3e50;
            color: white;
        }
    </style>
</head>

<body>
<div class="container-fluid">
    <div class="row">

        <!-- ========== SIDEBAR ========== -->
        <div class="col-md-2 sidebar">
            <h3 class="text-center text-white mb-4">Dairy</h3>

            <a href="adminDashboard" class="active">🏠 Dashboard</a>
            <a href="productDashboard">🥛 Products</a>
            <a href="#">🛒 Orders</a>
            <a href="#">👥 Customers</a>
            <a href="#">📊 Reports</a>
            <a href="agentDashboard">✅ Agents</a>
            <a href="productCollection">🧾 Product Collection</a>
            <a href="productCollectionList">📋 View Collection List</a>
            <a href="adminLogout" class="logout">🚪 Logout</a>
        </div>

        <!-- ========== MAIN CONTENT ========== -->
        <div class="col-md-10 p-0">

            <!-- ========== HEADER ========== -->
            <div class="header">
                <h4>Welcome, <c:out value="${loggedInAdmin.adminName}"/></h4>

                <div class="d-flex align-items-center">

                    <!-- ========== NOTIFICATION BELL ========== -->
                    <div class="dropdown me-4">
                        <button class="btn btn-light position-relative" data-bs-toggle="dropdown">
                            🔔
                            <c:if test="${not empty notifications}">
                                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                    ${fn:length(notifications)}
                                </span>
                            </c:if>
                        </button>

                        <ul class="dropdown-menu dropdown-menu-end p-3" style="width:320px;">

                            <c:if test="${empty notifications}">
                                <li class="text-center text-muted p-2">No notifications</li>
                            </c:if>

                            <c:forEach var="note" items="${notifications}">
                                <li class="p-2 border-bottom">

                                    <!-- Click to open payment details -->
                                    <div onclick="window.location.href='agentPaymentDetails?agentId=${note.agentId}'"
                                         style="cursor:pointer;">

                                        <div class="d-flex justify-content-between">
                                            <strong>${note.agentName}</strong>

                                            <small class="${note.overdue ? 'text-danger fw-bold' : 'text-muted'}">
                                                ${note.dueDate}
                                            </small>
                                        </div>

                                        <div class="${note.overdue ? 'text-danger fw-bold' : 'text-muted'}">
                                            ${note.message}
                                        </div>
                                    </div>

                                    <!-- Mark as Paid -->
                                    <form action="markPaymentDone" method="post" class="mt-2">
                                        <input type="hidden" name="agentId" value="${note.agentId}">
                                        <button class="btn btn-sm btn-success w-100">Mark as Paid ✔</button>
                                    </form>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <!-- ========== PROFILE DROPDOWN ========== -->
                    <div class="dropdown profile-dropdown">
                        <c:set var="profilePic"
                               value="${not empty loggedInAdmin.profilePicPath ? loggedInAdmin.profilePicPath : 'images/default.png'}"/>

                        <img src="/DairyManagement/${profilePic}?t=<%=System.currentTimeMillis()%>"
                             alt="Profile" id="profileDropdown"
                             data-bs-toggle="dropdown" aria-expanded="false">

                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileDropdown">
                            <div class="profile-info text-center">

                                <img src="/DairyManagement/${profilePic}?t=<%=System.currentTimeMillis()%>"
                                     class="rounded-circle" width="80" height="80">

                                <h5 class="mt-2"><c:out value="${loggedInAdmin.adminName}"/></h5>
                                <p><strong>📞</strong> ${loggedInAdmin.phoneNumber}</p>
                                <p><strong>✉️</strong> ${loggedInAdmin.emailId}</p>

                                <div class="d-flex justify-content-between mt-3">
                                    <button class="btn btn-sm btn-primary" data-bs-toggle="modal"
                                            data-bs-target="#editProfileModal">Edit</button>
                                    <button class="btn btn-sm btn-secondary" data-bs-toggle="dropdown">Close</button>
                                </div>
                            </div>
                        </ul>
                    </div>

                </div>
            </div>

            <!-- ========== DASHBOARD CARDS ========== -->
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

            <!-- ===== EDIT PROFILE MODAL (unchanged) ===== -->
            <div class="modal fade" id="editProfileModal" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h5>Edit Profile</h5>
                            <button class="btn-close" data-bs-dismiss="modal"></button>
                        </div>

                        <div class="modal-body">
                            <form action="updateProfile" method="post" enctype="multipart/form-data" class="row g-3">

                                <div class="col-12 text-center">
                                    <img id="previewImg"
                                         src="/DairyManagement/${profilePic}?t=<%=System.currentTimeMillis()%>"
                                         class="rounded-circle shadow"
                                         width="100" height="100">
                                </div>

                                <div class="col-12">
                                    <label>Full Name</label>
                                    <input type="text" class="form-control" name="adminName"
                                           value="${loggedInAdmin.adminName}" required>
                                </div>

                                <div class="col-12">
                                    <label>Phone Number</label>
                                    <input type="text" class="form-control" name="phoneNumber"
                                           value="${loggedInAdmin.phoneNumber}" required>
                                </div>

                                <div class="col-12">
                                    <label>Email</label>
                                    <input type="email" class="form-control" name="emailId"
                                           value="${loggedInAdmin.emailId}" required>
                                </div>

                                <div class="col-12">
                                    <label>Profile Picture</label>
                                    <input type="file" class="form-control" id="profilePicPath"
                                           name="profilePicPath" accept="image/*"
                                           onchange="previewImage(event)">
                                </div>

                                <div class="col-12 text-end">
                                    <button class="btn btn-success">Save</button>
                                    <button class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                </div>

                            </form>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function previewImage(event) {
        const reader = new FileReader();
        reader.onload = function () {
            document.getElementById('previewImg').src = reader.result;
        };
        reader.readAsDataURL(event.target.files[0]);
    }
</script>

</body>
</html>
