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
        .container {
            margin: 0;
            padding: 0;
            height: 90vh;
            background: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
        }

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

        .ProductForm{
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
        }

        .ProductForm form {
            display: flex;
            flex-direction: column;
        }

        .ProductForm h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .ProductForm input[type="text"],
        .ProductForm input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .ProductForm input[type="submit"] {
            padding: 10px;
            background: #007BFF;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .ProductForm input[type="submit"]:hover {
            background: #0056b3;
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
                    <li><a href="">Sale Products</a></li>
                    <li><h1>${sessionScope.username}</h1></li>
                    <li><a href="/logout">Logout</a></li>
                </ul>
            </nav>
        </div>

    <div class = "container">
        <div class = "ProductForm">
            <c:if test="${product!=null}">
                <form action = "/product?action=updateProduct" method = "post">
            </c:if>
            <c:if test="${product==null}">
                <form action = "/product?action=addProduct" method = "post">
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
    </div>
</body>
</html>
