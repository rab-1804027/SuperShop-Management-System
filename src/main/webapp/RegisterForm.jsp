<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 4/8/25
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
    <link rel="stylesheet" href="/CSS/RegistrationForm.css">
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

        .RegistrationForm {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
        }

        .RegistrationForm form {
            display: flex;
            flex-direction: column;
        }

        .RegistrationForm h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .RegistrationForm input[type="text"],
        .RegistrationForm input[type="email"],
        .RegistrationForm input[type="number"],
        .RegistrationForm input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .RegistrationForm input[type="submit"] {
            padding: 10px;
            background: #007BFF;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .RegistrationForm input[type="submit"]:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="RegistrationForm">
        <h1>Sign Up</h1>
        <form action="register" method="post">

            <input type="text" name="name" placeholder="Enter Your Name" required>
            <br>
            <input type="email" name="email" placeholder="Enter Your Email Address" required>
            <br>
            <input type="text" name="username" placeholder="Enter a Username" required>
            <br>
            <input type="password" name="password" placeholder="Set a Password" required>
            <br>
            <input type="password" name="confirmPassword" placeholder="Repeat the password" required>
            <br>
            <input type="submit" value="Register">
            ${error}
        </form>
    </div>
</body>
</html>
