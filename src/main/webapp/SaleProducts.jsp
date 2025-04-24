<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 4/24/25
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <title>Sale Products</title>
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
                    <li><a href="/product?action=saleProducts">Sale Products</a></li>
                    <li><h1>${sessionScope.username}</h1></li>
                    <li><a href="/logout"><h1>Logout</h1></a></li>
                </ul>
            </nav>
        </div>
    </div>



</body>
</html>
