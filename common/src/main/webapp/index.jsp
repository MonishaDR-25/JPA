<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Home Page</title>
    <style>
        body {
          font-family: Arial, sans-serif;
          margin: 0;
          padding: 0;
          background-color: #f4f4f4;
        }

        header {
          background-color: #1c1c1c;
          padding: 10px 20px;
          color: white;
        }

        .header-container {
          display: flex;
          justify-content: space-between;
          align-items: center;
        }

        .diamond-button {
          transform: rotate(45deg);
          background-color: #4CAF50;
          width: 60px;
          height: 60px;
          line-height: 60px;
          color: white;
          font-weight: bold;
          cursor: pointer;
          display: flex;
          justify-content: center;
          align-items: center;
        }

        .diamond-button span {
          transform: rotate(-45deg);
          display: block;
        }

        .nav-links a {
          margin-left: 15px;
          padding: 10px 20px;
          background-color: #2196F3;
          color: white;
          text-decoration: none;
          border-radius: 4px;
        }

        .nav-links a:hover {
          background-color: #1976D2;
        }

        .main-content {
          text-align: center;
          padding: 50px;
        }

        footer {
          background-color: #1c1c1c;
          color: white;
          text-align: center;
          padding: 10px;
          position: fixed;
          bottom: 0;
          width: 100%;
        }
    </style>
</head>
<body>

<header>
    <div class="header-container">
        <div class="diamond-button" onclick="window.location.href='index.html'">
            <span>Home</span>
        </div>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="redirectToIndex">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="redirectToRegister">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="redirectToLogin">Login</a>
                </li>
            </ul>
        </div>
    </div>
</header>

<div class="main-content">
    <h1>Welcome to Registration Portal</h1>
<!--    <p>Select an option from the top right to get started.</p>-->
</div>

<footer>
    <p>&copy; Registration Portal</p>
</footer>

</body>
</html>