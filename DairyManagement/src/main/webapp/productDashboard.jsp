<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
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
            padding-top: 80px;
            height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .products-container {
            flex: 1;
            overflow-y: auto;
            padding: 20px;
        }

        .profile-dropdown img { width:40px; height:40px; border-radius:50%; object-fit:cover; cursor:pointer; }
        .card { border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .new-product-btn { margin-bottom: 15px; }
        table thead { background-color: #2c3e50; color: white; }

        .modal { z-index: 2000; }
        .pagination a, .pagination span { margin: 0 3px; }
    </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <h3 class="text-center text-white mb-4">Dairy</h3>
    <a href="adminDashboard.jsp">🏠 Dashboard</a>
    <a href="productDashboard" class="active">🥛 Products</a>
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
                <img src="${pageContext.request.contextPath}/${profilePic}?t=<%= System.currentTimeMillis() %>" alt="Profile Picture" width="80" height="80" class="rounded-circle mb-2">
                <h5><c:out value="${loggedInAdmin.adminName}"/></h5>
                <p><strong>📞</strong> <c:out value="${loggedInAdmin.phoneNumber}"/></p>
                <p><strong>✉️</strong> <c:out value="${loggedInAdmin.emailId}"/></p>
            </div>
        </ul>
    </div>
</div>

<!-- Main -->
<div class="main">
    <div class="products-container">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <div class="card p-4">
            <div class="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
                <h4>Products List</h4>

                <!-- Search Form -->
                <form class="d-flex" method="get" action="productDashboard">
                    <input type="hidden" name="page" value="1">
                    <input type="text" name="search" class="form-control me-2" placeholder="Search products..."
                           value="${search != null ? search : ''}" style="min-width: 220px;">
                    <button class="btn btn-outline-primary" type="submit">Search</button>
                    <a href="productDashboard" class="btn btn-outline-secondary ms-2">Reset</a>
                </form>

                <button class="btn btn-success new-product-btn" data-bs-toggle="modal" data-bs-target="#registerProductModal">➕ Add Product</button>
            </div>

            <div class="table-responsive">
                <table id="productsTable" class="table table-bordered table-hover align-middle">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td><c:out value="${product.productId}"/></td>
                            <td><c:out value="${product.productName}"/></td>
                            <td><c:out value="${product.productType}"/></td>
                            <td>₹ <c:out value="${product.productPrice}"/></td>
                            <td>
                                <a href="editProduct?productId=${product.productId}" class="btn btn-sm btn-primary">Edit</a>
                                <a href="#" class="btn btn-sm btn-danger"
                                   data-product-id="${product.productId}"
                                   data-bs-toggle="modal"
                                   data-bs-target="#deleteProductModal">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty products}">
                        <tr>
                            <td colspan="5" class="text-center text-muted">No products found.</td>
                        </tr>
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
                               href="productDashboard?page=${currentPage - 1}&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">Previous</a>
                        </li>

                        <!-- Show '...' before -->
                        <c:if test="${startPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="productDashboard?page=1&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">1</a>
                            </li>
                            <li class="page-item disabled"><span class="page-link">...</span></li>
                        </c:if>

                        <!-- Page numbers -->
                        <c:forEach var="i" begin="${startPage}" end="${endPage}">
                            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                <a class="page-link"
                                   href="productDashboard?page=${i}&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Show '...' after -->
                        <c:if test="${endPage < totalPages}">
                            <li class="page-item disabled"><span class="page-link">...</span></li>
                            <li class="page-item">
                                <a class="page-link" href="productDashboard?page=${totalPages}&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">${totalPages}</a>
                            </li>
                        </c:if>

                        <li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>'">
                            <a class="page-link"
                               href="productDashboard?page=${currentPage + 1}&size=${pageSize}<c:if test='${not empty search}'>&search=${search}</c:if>">Next</a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>
</div>

<!-- ===== Modals ===== -->

<!-- Register Product Modal -->
<div class="modal fade" id="registerProductModal" tabindex="-1" aria-labelledby="registerProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <form method="post" action="registerProduct">
                <div class="modal-header">
                    <h5 class="modal-title" id="registerProductModalLabel">Add Product</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Product Name</label>
                        <input type="text" name="productName" class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label d-block">Product Type</label>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="productType" id="buyOption" value="Buy" required>
                            <label class="form-check-label" for="buyOption">Buy</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="productType" id="sellOption" value="Sell" required>
                            <label class="form-check-label" for="sellOption">Sell</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Price</label>
                        <input type="number" name="productPrice" class="form-control" step="0.01" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Save Product</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal fade" id="deleteProductModal" tabindex="-1" aria-labelledby="deleteProductLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="deleteProductLabel">Confirm Delete</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this product?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <a id="confirmDeleteProductBtn" href="#" class="btn btn-danger">Delete</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const deleteModal = document.getElementById('deleteProductModal');
        const confirmDeleteBtn = document.getElementById('confirmDeleteProductBtn');

        deleteModal.addEventListener('show.bs.modal', function (event) {
            const triggerBtn = event.relatedTarget;
            const productId = triggerBtn.getAttribute('data-product-id');
            confirmDeleteBtn.href = 'deleteProduct?productId=' + encodeURIComponent(productId);
        });
    });
</script>

</body>
</html>
