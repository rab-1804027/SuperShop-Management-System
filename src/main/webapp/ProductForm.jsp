<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 4/23/25
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Form</title>
    <style>

        .navbar{
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
        }
        .navbar nav{
            flex: 1;
            text-align: right;
        }
        .navbar nav ul{
            display: inline-block;
            list-style-type: none;
        }
        .navbar nav ul li{
            display: inline-block;
            margin-right: 20px;
        }
        .navbar a{
            text-decoration: none;
            color: #555;
        }
        a:hover {
            color: #007BFF;
            text-decoration: underline;
        }

        .productForm {
            width: 50%;
            margin: 40px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .productForm h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #28a745;
        }

        .productForm input[type="text"],
        .productForm input[type="number"] {
            width: 100%;
            padding: 10px 15px;
            margin: 10px 0 5px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 16px;
        }

        .productForm .error {
            color: red;
            font-size: 14px;
            margin-top: -5px;
            margin-bottom: 10px;
            display: block;
        }

        .productForm button[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #007BFF;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .productForm button[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
        <div class="navbar">
            <div class = "title">
                <h1>SuperShop Management System</h1>
            </div>
            <nav>
                <ul>
                    <li><a href="/dashboard">Home</a></li>
                    <li><a href="/product?action=productForm">Add New Product</a></li>
                    <li><a href="/product?action=productCart">Cart</a></li>
                    <li><h1>${sessionScope.username}</h1></li>
                    <li><a href="/logout"><h1>Logout</h1></a></li>
                </ul>
            </nav>
        </div>

        <c:set var = "product" value = "${sessionScope.product}"/>

        <div class = "productForm">
            <c:if test="${product!=null}">
                <form action = "/product?action=update" method = "post">
            </c:if>
            <c:if test="${product==null}">
                <form action = "/product?action=add" method = "post">
            </c:if>
                    <caption>
                        <c:if test="${product!=null}">
                            <h2>Update Product</h2>
                        </c:if>
                        <c:if test="${product==null}">
                            <h2>Add New Product</h2>
                        </c:if>
                    </caption>
                    <input type = "hidden" name = "id" value = "${product.id}">
                    <br>
                    <input type = "hidden" name="userId" value="${sessionScope.userId}">
                    <br>
                    <input type = "text" name = "name" placeholder = "Enter Product Name" value = "${product.name}">
                    ${errors.name}
                    <br>
                    <input type = "number" name = "price" placeholder = "Enter Product Price" value = "${product.price}">
                    ${errors.price}
                    <br>
                    <input type = "number" name = "stockQuantity" placeholder = "Enter Product Quantity" value = "${product.stockQuantity}">
                    ${errors.stockQuantity}
                    <br>
                    <button type = "submit">Submit</button>
                </form>
        </div>
</body>
</html>
