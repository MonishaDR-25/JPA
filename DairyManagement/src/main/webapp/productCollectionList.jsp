<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Collection List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { font-family: 'Poppins', sans-serif; background: #f8f9fa; }
        .sidebar {
            height: 100vh; width: 16.666667%;
            background-color: #2c3e50; color: #fff;
            position: fixed; top: 0; left: 0; padding-top: 20px;
        }
        .sidebar a {
            display: block; color: #bdc3c7; padding: 12px 20px;
            text-decoration: none; transition: 0.2s;
        }
        .sidebar a:hover, .sidebar a.active {
            background-color: #1abc9c; color: #fff; border-radius: 8px;
        }
        .header {
            padding: 18px 24px; background-color: #fff;
            border-bottom: 1px solid #eaeaea;
            display: flex; justify-content: space-between; align-items: center;
            position: fixed; top: 0; left: 16.666667%; right: 0; z-index: 1200;
        }
        .main { margin-left: 16.666667%; padding-top: 100px; padding: 100px 20px 40px; }
        table th { background: #16a085; color: white; }
    </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <h3 class="text-center text-white mb-4">Dairy</h3>
    <a href="adminDashboard.jsp">🏠 Dashboard</a>
    <a href="productDashboard">🥛 Products</a>
    <a href="agentDashboard">✅ Agents</a>
    <a href="productCollection.jsp">💰 Product Collection</a>
    <a href="productCollectionList" class="active">📋 View Collection List</a>
</div>

<!-- Header -->
<div class="header">
    <h4>Product Collection List</h4>
</div>

<!-- Main Content -->
<div class="main">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <div>
            <input type="text" id="searchBox" placeholder="Search Agent or Milk Type" class="form-control d-inline-block w-auto">
            <button class="btn btn-success btn-sm" id="viewAllBtn">View All</button>
        </div>
        <div>
            <input type="date" id="dateFilter" class="form-control">
        </div>
    </div>

    <table class="table table-bordered table-striped text-center align-middle">
        <thead>
        <tr>
            <th>Agent Name</th>
            <th>Phone</th>
            <th>Milk Type</th>
            <th>Price/L</th>
            <th>Quantity</th>
            <th>Total Amount</th>
            <th>Collected Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="collectionTable">
        <c:forEach var="col" items="${products}">
            <tr>
                <td>${col.agent.firstName} ${col.agent.lastName}</td>
                <td>${col.phoneNumber}</td>
                <td>${col.typeOfMilk}</td>
                <td>${col.price}</td>
                <td>${col.quantity}</td>
                <td>${col.totalAmount}</td>
                <td>${col.collectedAt}</td>
                <td>
                    <button class="btn btn-info btn-sm viewBtn" data-id="${col.productCollectionId}">View</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty products}">
        <p class="text-center text-muted mt-4">No records found for selected date.</p>
    </c:if>
</div>

<!-- Modal for Viewing Details -->
<div class="modal fade" id="viewModal" tabindex="-1" aria-labelledby="viewModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title" id="viewModalLabel">Product Collection Details</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h6 class="fw-bold text-primary mb-2">Agent Details</h6>
                <div class="row mb-3">
                    <div class="col-md-6"><strong>Name:</strong> <span id="agentName"></span></div>
                    <div class="col-md-6"><strong>Email:</strong> <span id="agentEmail"></span></div>
                    <div class="col-md-6 mt-2"><strong>Phone:</strong> <span id="agentPhone"></span></div>
                    <div class="col-md-6 mt-2"><strong>Address:</strong> <span id="agentAddress"></span></div>
                </div>

                <h6 class="fw-bold text-primary mb-2">Product Details</h6>
                <div class="row">
                    <div class="col-md-4"><strong>Milk Type:</strong> <span id="milkType"></span></div>
                    <div class="col-md-4"><strong>Price/L:</strong> <span id="price"></span></div>
                    <div class="col-md-4"><strong>Quantity (L):</strong> <span id="quantity"></span></div>
                    <div class="col-md-4 mt-2"><strong>Total Amount:</strong> <span id="totalAmount"></span></div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Search functionality
    $("#searchBox").on("input", function() {
        const query = $(this).val().toLowerCase();
        $("#collectionTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(query) > -1);
        });
    });

    // Date filter
    $("#dateFilter").on("change", function() {
        const date = $(this).val();
        if (date) {
            window.location.href = "productCollectionList?date=" + date;
        }
    });

    // View All
    $("#viewAllBtn").on("click", function() {
        window.location.href = "productCollectionList";
    });

    // View button click event
    $(document).on("click", ".viewBtn", function() {
        const id = $(this).data("id");
        $.ajax({
            url: "viewCollectionDetails",
            type: "GET",
            data: { id: id },
            success: function(data) {
                if (data) {
                    $("#agentName").text(data.agentName || "-");
                    $("#agentEmail").text(data.agentEmail || "-");
                    $("#agentPhone").text(data.agentPhone || "-");
                    $("#agentAddress").text(data.agentAddress || "-");
                    $("#milkType").text(data.typeOfMilk || "-");
                    $("#price").text(data.price || "-");
                    $("#quantity").text(data.quantity || "-");
                    $("#totalAmount").text(data.totalAmount || "-");

                    const modal = new bootstrap.Modal(document.getElementById("viewModal"));
                    modal.show();
                }
            },
            error: function() {
                alert("Failed to load details. Please try again.");
            }
        });
    });
</script>

</body>
</html>
