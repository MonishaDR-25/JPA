<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Agents Dashboard</title>
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
        .agents-container { flex: 1; overflow-y: auto; padding: 20px; }
        .profile-dropdown img { width:40px; height:40px; border-radius:50%; object-fit:cover; cursor:pointer; }
        .card { border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .new-agent-btn { margin-bottom: 15px; }
        table thead { background-color: #2c3e50; color: white; }
        .pagination { justify-content: center; margin-top: 15px; }
        .error-msg { color: #d63384; display: none; font-size: 0.9rem; }
    </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <h3 class="text-center text-white mb-4">Dairy</h3>
    <a href="adminDashboard">🏠 Dashboard</a>
    <a href="productDashboard">🥛 Products</a>
    <a href="#">🛒 Orders</a>
    <a href="#">👥 Customers</a>
    <a href="#">📊 Reports</a>
    <a href="agentDashboard" class="active">✅ Agents</a>
    <a href="productCollection">🧾 Product Collection</a>
    <a href="productCollectionList">📋 View Collection List</a>
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
            <div class="p-3 text-center">
                <img src="${pageContext.request.contextPath}/${profilePic}?t=<%= System.currentTimeMillis() %>" class="rounded-circle" width="80" height="80">
                <h5 class="mt-2 mb-1"><c:out value="${loggedInAdmin.adminName}"/></h5>
                <p class="mb-1"><strong>📞</strong> <c:out value="${loggedInAdmin.phoneNumber}"/></p>
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
    <div class="agents-container">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <div class="card p-4">
            <div class="d-flex justify-content-between align-items-center mb-3 flex-wrap">
                <h4>Agents List</h4>

                <!-- Search Form -->
                <form class="d-flex mb-2" method="get" action="agentDashboard">
                    <input type="text" name="search" value="${param.search}" class="form-control me-2" placeholder="🔍 Search by name or email" style="width:250px;">
                    <button class="btn btn-outline-primary" type="submit">Search</button>
                </form>

                <button class="btn btn-success new-agent-btn" data-bs-toggle="modal" data-bs-target="#registerAgentModal">➕ Register New Agent</button>
            </div>

            <div class="table-responsive">
                <table id="agentsTable" class="table table-bordered table-hover align-middle">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Milk Type</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="agent" items="${agents}">
                        <tr>
                            <td>${agent.agentId}</td>
                            <td>${agent.firstName}</td>
                            <td>${agent.lastName}</td>
                            <td>${agent.email}</td>
                            <td>${agent.phoneNumber}</td>
                            <td>${agent.address}</td>
                            <td>${agent.milkType}</td>
                            <td>
                                <a href="editAgent?agentId=${agent.agentId}" class="btn btn-sm btn-primary">Edit</a>
                                <a href="agentBankDetailsInfo?agentId=${agent.agentId}" class="btn btn-sm btn-info">Bank</a>
                                <a href="#" class="btn btn-sm btn-danger" data-agent-id="${agent.agentId}" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty agents}">
                        <tr><td colspan="8" class="text-center text-muted">No agents found.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </div>

            <!-- ✅ Smart Pagination -->
            <c:if test="${totalPages > 1}">
                <c:set var="startPage" value="${currentPage - 2}"/>
                <c:set var="endPage" value="${currentPage + 2}"/>

                <c:if test="${startPage < 1}">
                    <c:set var="endPage" value="${endPage + (1 - startPage)}"/>
                    <c:set var="startPage" value="1"/>
                </c:if>
                <c:if test="${endPage > totalPages}">
                    <c:set var="startPage" value="${startPage - (endPage - totalPages)}"/>
                    <c:set var="endPage" value="${totalPages}"/>
                </c:if>
                <c:if test="${startPage < 1}">
                    <c:set var="startPage" value="1"/>
                </c:if>

                <nav>
                    <ul class="pagination justify-content-center mt-3">
                        <li class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>'">
                            <a class="page-link"
                               href="agentDashboard?page=${currentPage - 1}&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">Previous</a>
                        </li>

                        <!-- Show '...' before -->
                        <c:if test="${startPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="agentDashboard?page=1&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">1</a>
                            </li>
                            <li class="page-item disabled"><span class="page-link">...</span></li>
                        </c:if>

                        <!-- Page numbers -->
                        <c:forEach var="i" begin="${startPage}" end="${endPage}">
                            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                <a class="page-link"
                                   href="agentDashboard?page=${i}&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Show '...' after -->
                        <c:if test="${endPage < totalPages}">
                            <li class="page-item disabled"><span class="page-link">...</span></li>
                            <li class="page-item">
                                <a class="page-link" href="agentDashboard?page=${totalPages}&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">${totalPages}</a>
                            </li>
                        </c:if>

                        <li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>'">
                            <a class="page-link"
                               href="agentDashboard?page=${currentPage + 1}&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">Next</a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>
</div>

<!-- Modals -->

<!-- Edit Profile Modal -->
<div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form action="updateProfile" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <h5 class="modal-title" id="editProfileModalLabel">Edit Profile</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="text-center mb-3">
                        <img id="profileModalPreview" src="${pageContext.request.contextPath}/${profilePic}?t=<%= System.currentTimeMillis() %>"
                             alt="Profile" class="rounded-circle" width="100" height="100">
                    </div>
                    <div class="mb-3">
                        <label for="adminName" class="form-label">Full Name</label>
                        <input type="text" class="form-control" id="adminName" name="adminName" value="${loggedInAdmin.adminName}" required>
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumberModal" class="form-label">Phone Number</label>
                        <input type="text" class="form-control" id="phoneNumberModal" name="phoneNumber" value="${loggedInAdmin.phoneNumber}" required>
                    </div>
                    <div class="mb-3">
                        <label for="emailIdModal" class="form-label">Email</label>
                        <input type="email" class="form-control" id="emailIdModal" name="emailId" value="${loggedInAdmin.emailId}" required>
                    </div>
                    <div class="mb-3">
                        <label for="profilePicPathModal" class="form-label">Profile Picture</label>
                        <input type="file" class="form-control" id="profilePicPathModal" name="profilePicPath" accept="image/*" onchange="previewProfileModalImage(event)">
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

<!-- Register Agent Modal -->
<div class="modal fade" id="registerAgentModal" tabindex="-1" aria-labelledby="registerAgentModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <form id="registerForm" method="post" action="registerAgents" enctype="multipart/form-data">
                <div class="modal-header">
                    <h5 class="modal-title" id="registerAgentModalLabel">Agent Registration</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="row g-3">
                        <!-- First Name -->
                        <div class="col-md-6">
                            <label class="form-label">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" required>
                            <div id="firstNameError" class="error-msg">First name must be at least 3 letters</div>
                        </div>
                        <!-- Last Name -->
                        <div class="col-md-6">
                            <label class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" required>
                            <div id="lastNameError" class="error-msg">Last name must be at least 3 letters</div>
                        </div>
                        <!-- Email -->
                        <div class="col-md-6">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                            <div id="emailError" class="error-msg">Enter a valid email (must contain @)</div>
                            <div id="emailExists" class="error-msg">This email is already registered</div>
                        </div>
                        <!-- Phone -->
                        <div class="col-md-6">
                            <label class="form-label">Phone Number</label>
                            <input type="text" class="form-control" id="phone" name="phoneNumber" required>
                            <div id="phoneError" class="error-msg">Phone number must be 10 digits</div>
                            <div id="phoneExists" class="error-msg">This phone number is already registered</div>
                        </div>
                        <!-- Address -->
                        <div class="col-12">
                            <label class="form-label">Address</label>
                            <textarea class="form-control" id="address" name="address" rows="2" required></textarea>
                            <div id="addressError" class="error-msg">Address cannot be empty</div>
                        </div>
                        <!-- Milk Type -->
                        <div class="col-md-6">
                            <label class="form-label">Types of Milk</label>
                            <select class="form-select" id="milkType" name="milkType" required>
                                <option value="">-- Select Milk Type --</option>
                                <option>Full Cream</option>
                                <option>Toned Milk</option>
                                <option>Double Toned Milk</option>
                                <option>Skimmed Milk</option>
                                <option>Organic Milk</option>
                            </select>
                            <div id="milkTypeError" class="error-msg">Please select a milk type</div>
                        </div>
                    </div> <!-- end row -->
                </div> <!-- end modal-body -->
                <div class="modal-footer">
                    <button id="registerSubmitBtn" type="submit" class="btn btn-success">Register</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Delete Confirmation Modal -->
<div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="deleteConfirmLabel">Confirm Delete</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this agent?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Delete</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const deleteConfirmModal = document.getElementById('deleteConfirmModal');
        const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');
        deleteConfirmModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const agentId = button.getAttribute('data-agent-id');
            confirmDeleteBtn.href = 'deleteAgent?agentId=' + agentId;
        });
    });
</script>

</body>
</html>
