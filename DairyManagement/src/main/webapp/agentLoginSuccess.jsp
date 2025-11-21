<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Agent Dashboard | Farmer Fresh Family</title>
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

        /* Sidebar */
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

        /* Main Content */
        .main-content {
            flex: 1;
            margin-left: 250px;
            padding: 40px;
        }

        /* Profile Card */
        .profile-card {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.08);
            padding: 30px;
            max-width: 700px;
            margin: 0 auto;
        }
        .profile-pic {
            display: block;
            margin: 0 auto 15px;
            border-radius: 50%;
            height: 120px;
            width: 120px;
            object-fit: cover;
            border: 3px solid #adb5bd;
        }
        .btn-custom {
            background-color: #495057;
            color: #fff;
            font-weight: 600;
        }
        .btn-custom:hover {
            background-color: #343a40;
        }
        .readonly {
            background-color: #e9ecef;
            cursor: not-allowed;
        }

        footer {
            background-color: #495057;
            color: #dee2e6;
            text-align: center;
            padding: 12px;
            margin-top: auto;
        }

        .tab-content {
            margin-top: 20px;
        }

        /* Payment Cards */
        .payment-card {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
            padding: 20px;
            text-align: center;
            font-weight: 600;
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

<!-- Navbar -->
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
    <!-- Sidebar -->
    <div class="sidebar">
        <h5>Agent Panel</h5>
        <a  href="agentLoginSuccess">👤 Profile</a>
        <a  href="agentBankDetails">🏦 Bank Details</a>
        <a class="nav-link" data-bs-toggle="tab" href="#collectionsTab">🥛 Collections</a>
        <a class="nav-link" data-bs-toggle="tab" href="#paymentsTab">💰 Payments</a>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <div class="tab-content">

            <!-- Profile Section -->
            <div class="tab-pane fade show active" id="profileTab">
                <div class="profile-card">
                    <h4 class="text-center mb-4">My Profile</h4>

                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success text-center">${successMessage}</div>
                    </c:if>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger text-center">${errorMessage}</div>
                    </c:if>

                    <form action="updateAgentProfile" method="post" enctype="multipart/form-data">
                        <div class="text-center mb-3">
                            <c:choose>
                                <c:when test="${not empty loggedInAgent.profilePhotoPath}">
                                    <img id="profilePreview"
                                         src="${loggedInAgent.profilePhotoPath}"
                                         alt="Profile Photo" class="profile-pic">
                                </c:when>
                                <c:otherwise>
                                    <img id="profilePreview"
                                         src="https://cdn-icons-png.flaticon.com/512/149/149071.png"
                                         alt="Default Avatar" class="profile-pic">
                                </c:otherwise>
                            </c:choose>

                            <div class="mt-3">
                                <label class="form-label">Upload New Photo:</label>
                                <input type="file" id="profilePhoto" name="profilePhoto" class="form-control" accept="image/*">
                            </div>
                        </div>

                        <input type="hidden" name="agentId" value="${loggedInAgent.agentId}">

                        <div class="mb-3">
                            <label class="form-label">First Name</label>
                            <input type="text" name="firstName" value="${loggedInAgent.firstName}" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Last Name</label>
                            <input type="text" name="lastName" value="${loggedInAgent.lastName}" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" value="${loggedInAgent.email}" class="form-control readonly" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Phone Number</label>
                            <input type="text" name="phoneNumber" value="${loggedInAgent.phoneNumber}" class="form-control readonly" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Milk Type</label>
                            <input type="text" name="milkType" value="${loggedInAgent.milkType}" class="form-control readonly" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Address</label>
                            <textarea name="address" class="form-control" rows="3" required>${loggedInAgent.address}</textarea>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-custom px-4">Save Changes</button>
                            <a href="agentBankDetails" class="btn btn-outline-secondary px-4 ms-2">+ Add Bank Details</a>
                        </div>

                    </form>
                </div>
            </div>

            <!-- Collections Section -->
            <div class="tab-pane fade" id="collectionsTab">
                <h4 class="mb-4">Collection History</h4>
                <div class="table-responsive">
                    <table class="table table-striped table-hover align-middle">
                        <thead class="table-dark">
                        <tr>
                            <th>Date</th>
                            <th>Milk Type</th>
                            <th>Quantity (Litres)</th>
                            <th>Amount (₹)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>2025-11-04</td>
                            <td>Cow Milk</td>
                            <td>15</td>
                            <td>750</td>
                        </tr>
                        <tr>
                            <td>2025-11-05</td>
                            <td>Buffalo Milk</td>
                            <td>10</td>
                            <td>600</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Payments Section -->
            <div class="tab-pane fade" id="paymentsTab">
                <h4 class="mb-4">Payment Summary</h4>
                <div class="row g-4">
                    <div class="col-md-4">
                        <div class="payment-card">
                            <h5>Total Earnings</h5>
                            <p class="text-success fs-4">₹ 12,300</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="payment-card">
                            <h5>Pending Payments</h5>
                            <p class="text-warning fs-4">₹ 1,200</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="payment-card">
                            <h5>Last Payment</h5>
                            <p class="text-muted fs-5">02 Nov 2025</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <p>&copy; 2025 Farmer Fresh Family | All Rights Reserved</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Instant photo preview before upload
    document.getElementById('profilePhoto').addEventListener('change', function (event) {
        const reader = new FileReader();
        reader.onload = function () {
            document.getElementById('profilePreview').src = reader.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    });
</script>
</body>
</html>
