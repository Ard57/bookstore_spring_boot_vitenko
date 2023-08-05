<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script defer src="/js/lib/jquery-3.7.0.js"></script>
    <script defer src="/js/lib/big-decimal.js"></script>
    <script defer src="/js/order.js"></script>
</head>
<body>
<div class="content entity-page">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1>Order</h1>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <div class="order-info">
    </div>

    <table>
        <thead>
        <tr>
            <th>Book ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Pages</th>
            <th>Year published</th>
            <th>Cover</th>
            <th>Amount</th>
            <th>Price</th>
        </tr>
        </thead>

        <tbody>
        </tbody>

    </table>

    <h3><a href="/orders/all">List of all orders</a></h3>

</div>
</body>
</html>
