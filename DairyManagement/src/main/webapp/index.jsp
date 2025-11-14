<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- ✅ Responsive -->
    <title>Farmer Fresh Family</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            padding-top: 90px; /* ✅ Space for fixed navbar */
        }
        .navbar {
            background-color: #2c3e50;
            padding-top: 0.5rem;
            padding-bottom: 0.5rem;
        }
        .navbar-brand img {
            height: 80px;
            width: auto;
            margin-right: 12px;
        }
        .navbar-brand {
            font-size: 1.6rem;
            font-weight: bold;
            color: #ecf0f1 !important;
            display: flex;
            align-items: center;
        }
        .navbar-nav .nav-link {
            color: #ecf0f1 !important;
            font-weight: 500;
        }
        .navbar-nav .nav-link:hover {
            color: #f39c12 !important;
        }
        footer {
            background-color: #2c3e50;
            color: #bdc3c7;
            padding: 15px 0;
            text-align: center;
            margin-top: 50px;
        }
        .hero-section {
            background: url('https://cdn.wallpapersafari.com/15/89/VGYpmE.jpg') center/cover no-repeat;
            height: 400px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            text-shadow: 1px 1px 5px rgba(0,0,0,0.6);
        }
        .hero-section h1 {
            font-size: 3rem;
            font-weight: bold;
            text-align: center;
        }

        /* ✅ Product card fixes */
        .card-img-top {
            height: 180px;
            width: 100%;
            object-fit: cover;
        }
        .card {
            border-radius: 12px;
            overflow: hidden;
            transition: transform 0.2s ease-in-out;
            display: flex;
            flex-direction: column;
        }
        .card-body {
            flex: 1;
            text-align: center;
        }
        .card:hover {
            transform: translateY(-5px);
        }

        /* ✅ About us image fix */
        .about-img {
            max-height: 280px;
            width: 100%;
            object-fit: cover;
            border-radius: 10px;
        }
        .about-text {
            display: flex;
            flex-direction: column;
            justify-content: center;
            height: 100%;
        }
    </style>
</head>
<body>

<!-- Navbar (fixed-top) -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://dcassetcdn.com/design_img/3810229/80753/23541915/8qxg6nf119f3636ev6e4xbnqf4_image.png" alt="Logo">
            Farmer Fresh Family
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="#products">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="#about">About Us</a></li>
                <li class="nav-item"><a class="nav-link" href="#contact">Contact</a></li>

                <!-- Login Dropdown -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Login
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="adminLoginForm">Admin Login</a></li>
                        <li><a class="dropdown-item" href="customerLogin">Customer Login</a></li>
                        <li><a class="dropdown-item" href="agentLogin">Agent Login</a></li>
                        <li><a class="dropdown-item" href="registration">Registration</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<div class="hero-section">
    <h1>Welcome to Farmer Fresh Family</h1>
</div>

<!-- Products Section -->
<div class="container my-5 text-center" id="products">
    <h2 class="mb-4">Our Dairy Products</h2>
    <!-- ✅ Fully responsive aligned grid -->
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4" id="product-container">
        <!-- Products will load here from JSON -->
    </div>
</div>

<!-- ✅ About Us Section -->
<div class="container my-5" id="about">
    <h2 class="text-center mb-4">About Us</h2>
    <div class="row align-items-center">
        <div class="col-md-6 text-center">
            <img src="https://media.istockphoto.com/id/1436282308/photo/phrase-about-us-with-magnifying-glass.jpg?s=612x612&w=0&k=20&c=5R9S89MTvbE3NkOaQo-ktdw6PMvnwxBp9k81w7keA1g="
                 alt="About Dairy" class="about-img shadow">
        </div>
        <div class="col-md-6 about-text">
            <p>
                We are passionate about providing fresh and high-quality dairy products directly from farmers to customers.
                Our Dairy Management System ensures transparency, efficiency, and trust between farmers, agents, and customers.
            </p>
            <ul>
                <li><b>Mission:</b> Empower farmers with modern tools.</li>
                <li><b>Vision:</b> Deliver freshness and quality every day.</li>
                <li><b>Values:</b> Trust, Sustainability, and Innovation.</li>
            </ul>
        </div>
    </div>
</div>

<!-- ✅ Contact Us Section -->
<div class="container my-5" id="contact">
    <h2 class="text-center mb-4">Contact Us</h2>
    <div class="row">
        <!-- Contact Info -->
        <div class="col-md-5">
            <h5>Get in Touch</h5>
            <p><b>Address:</b> #123 BTM Layout, Bengaluru, India</p>
            <p><b>Phone:</b> +91 98765 43210</p>
            <p><b>Email:</b> farmer@dairymanagement.com</p>
        </div>
        <!-- Contact Form -->
        <div class="col-md-7">
            <form>
                <div class="mb-3">
                    <label class="form-label">Name</label>
                    <input type="text" class="form-control" placeholder="Enter your name">
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" placeholder="Enter your email">
                </div>
                <div class="mb-3">
                    <label class="form-label">Message</label>
                    <textarea class="form-control" rows="4" placeholder="Type your message"></textarea>
                </div>
                <button type="submit" class="btn btn-success">Send Message</button>
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <p>&copy; 2025 Farmer Fresh Family | All Rights Reserved</p>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Script to load products dynamically -->
<script>
    document.addEventListener("DOMContentLoaded", function() {
        fetch('data/products.json')
            .then(response => response.json())
            .then(data => {
                let container = document.getElementById("product-container");
                container.innerHTML = "";
                data.forEach(product => {
                    container.innerHTML += `
                        <div class="col d-flex">
                            <div class="card shadow-sm h-100 w-100">
                                <img src="${product.image}" class="card-img-top" alt="${product.name}">
                                <div class="card-body d-flex flex-column">
                                    <h5 class="card-title">${product.name}</h5>
                                    <p class="card-text">${product.description}</p>
                                    <p class="fw-bold text-success">₹ ${product.price}</p>
                                    <a href="order.jsp?product=${encodeURIComponent(product.name)}" class="btn btn-primary mt-auto">Order Now</a>
                                </div>
                            </div>
                        </div>
                    `;
                });
            })
            .catch(error => console.error("Error loading products:", error));
    });
</script>

</body>
</html>
