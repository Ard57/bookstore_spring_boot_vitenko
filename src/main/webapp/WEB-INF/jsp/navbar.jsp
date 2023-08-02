<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navigation Bar</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script defer src="/js/jquery-3.7.0.js"></script>
    <script defer src="/js/cart-size.js"></script>
</head>
<body>
<div class="navbar">
    <a href="/home">Home</a>

    <c:if test="${sessionScope.user != null &&
     (sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'MANAGER')}">
        <a href="/users/all">All Users</a>
    </c:if>

    <a href="/books/all">All Books</a>

    <c:if test="${sessionScope.user != null &&
     (sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'MANAGER')}">
        <a href="/orders/all">All Orders</a>
    </c:if>

    <c:if test="${sessionScope.user == null}">
        <a href="/login">Log In</a>
    </c:if>

    <c:if test="${sessionScope.user == null}">
        <a href="/register">Sign Up</a>
    </c:if>

    <a class="cart-size-counter" href="/cart">Cart</a>

    <c:if test="${sessionScope.user != null}">
        <a href="/logout">Log Out</a>
    </c:if>

</div>
</body>
</html>
