<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script defer src="/js/jquery-3.7.0.js"></script>
    <script defer src="/js/cart.js"></script>
</head>
<body>
<div class="content entity-page">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1>Cart</h1>
    <h3>${date}</h3>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <c:if test="${requestScope.orderDto.user != null}">
        <div class="buyer-info">
            <p>Buyer: ${orderDto.user.email}</p>
        </div>
    </c:if>

    <p class="total-price"></p>
    <p>List of books</p>

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

    <form method="post" action="\cart\purchase">
        <input class="purchase" type="submit" value="Purchase" disabled="disabled"/>
    </form>

    <h3><a href="/books/all">Book Catalog</a></h3>

</div>
</body>
</html>
