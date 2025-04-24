<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 4/21/25
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Supershop Management</title>
    <style>
        .navbar{
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
        }
        nav{
            flex: 1;
            text-align: right;
        }
        nav ul{
            display: inline-block;
            list-style-type: none;
        }
        nav ul li{
            display: inline-block;
            margin-right: 20px;
        }
        a{
            text-decoration: none;
            color: #555;
        }
        .shopkeeperList, .pendingShopkeeperList {
            margin: 40px auto;
            width: 90%;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
        }

        .shopkeeperList h2,
        .pendingShopkeeperList h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .shopkeeperList table,
        .pendingShopkeeperList table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .shopkeeperList thead,
        .pendingShopkeeperList thead {
            background-color: #28a745;
            color: white;
        }

        .shopkeeperList th, .shopkeeperList td,
        .pendingShopkeeperList th, .pendingShopkeeperList td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .shopkeeperList tbody tr:hover,
        .pendingShopkeeperList tbody tr:hover {
            background-color: #f1f1f1;
        }

        .pendingShopkeeperList button {
            background-color: #007BFF;
            border: none;
            padding: 6px 10px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
            transition: background-color 0.3s ease;
        }

        .pendingShopkeeperList button a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        .pendingShopkeeperList button:hover {
            background-color: #0056b3;
        }

        .pendingShopkeeperList td button:nth-child(2) {
            background-color: #dc3545;
        }

        .pendingShopkeeperList td button:nth-child(2):hover {
            background-color: #a71d2a;
        }
    </style>
</head>
<body>
    <div class = "container">
        <div class="navbar">
            <div class = "title">
                <h1>SuperShop Management System</h1>
            </div>
            <nav>
                <ul>
                    <li><h1>${sessionScope.username}</h1></li>
                    <li><a href="/logout"><h1>Logout</h1></a></li>
                </ul>
            </nav>
        </div>
    </div>

    <div class = "pendingShopkeeperList">
        <table>
            <caption><h2>Pending Shopkeeper's List</h2></caption>
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Username</th>
                <th>RegistrationTime</th>
                <th>ConfirmRegistration</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var = "user" items="${pendingShopkeeperList}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.username}</td>
                    <td>${user.registrationTime}</td>
                    <td><button><a href="approveShopkeeper?username=${user.username}">Approve</a></button>
                        <button><a href="rejectShopkeeper?username=${user.username}">Reject</a></button></td>
                </tr>
            </c:forEach></button>
            </tbody>
        </table>
    </div>

    <div class = "shopkeeperList">
        <table>
            <caption><h2>Shopkeeper's List</h2></caption>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Username</th>
                    <th>RegistrationTime</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var = "user" items="${approvalShopkeeperList}">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.username}</td>
                        <td>${user.registrationTime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
