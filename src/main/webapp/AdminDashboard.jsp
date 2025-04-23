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
    <title>Title</title>
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
                    <li><a href="/logout">Logout</a></li>
                </ul>
            </nav>
        </div>
    </div>

    <div>
        <h2>Shopkeeper's List</h2>
        <table>
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

    <div>
        <h2>Pending Shopkeeper's List</h2>
        <table>
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

</body>
</html>
