<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 4/23/25
  Time: 12:56 PM
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
                    <li><a href="/dashboard">Home</a></li>
                    <li><a href="/product?action=productForm">Add New Product</a></li>
                    <li><a href="">Sale Products</a></li>
                    <li><h1>${sessionScope.username}</h1></li>
                    <li><a href="/logout">Logout</a></li>
                </ul>
            </nav>
        </div>
    </div>

    <div>
        <table>
            <caption><h1>Product List</h1></caption>
            <thead>
                <th>Name</th>
                <th>Price</th>
                <th>In Stock</th>
                <th>Manage</th>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.stockQuantity}</td>
                    <td><button><a href="/product?action=productForm&id=${product.id}">Update</a></button>
                    <button><a href="">Delete</a></button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
