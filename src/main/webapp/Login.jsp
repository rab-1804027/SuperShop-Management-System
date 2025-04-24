<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 4/9/25
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Prevent caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // If already logged in, redirect to dashboard
    if (session.getAttribute("user") != null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>

<html>
<head>
    <title>Login Page</title>
<%--    <link rel="stylesheet" href="/CSS/Login.css">--%>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .Login{
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
        }

        .Login form {
            display: flex;
            flex-direction: column;
        }

        .Login h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .Login input[type="text"],
        .Login input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .Login input[type="submit"] {
            padding: 10px;
            background: #007BFF;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .Login input[type="submit"]:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>

<div class="Login">
        <h2>Login into SuperShop</h2>
        <h4 style="color: red;">${error}</h4>
        <form action="login" method="post">
            <input type="text" name="username" placeholder="Enter Username" required>
            ${usernameError}
            <br>
            <input type="password" name="password" placeholder="Enter Password" required>
            ${passwordError}
            <br>
            <input type="submit" value="Login">
        </form>
    </div>

<script>
    // Try to force recheck from server on back navigation
    window.addEventListener("pageshow", function(event) {
        // If the page is loaded from cache
        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            location.reload(); // Force reloading from server
        }
    });
</script>

</body>
</html>
