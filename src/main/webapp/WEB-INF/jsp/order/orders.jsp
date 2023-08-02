<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="content">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1>List of all orders</h1>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <table>
        <tr>
            <th>#</th>
            <th>ID</th>
            <th>Buyer email</th>
            <th>Number 0f items</th>
            <th>Total price</th>
            <th>Status</th>
        </tr>

        <c:forEach items="${orderDtos}" var="orderDto" varStatus="counter">
            <tr>
                <td class="center-align">${counter.count}</td>
                <td class="center-align"><a href="/orders/${orderDto.id}">${orderDto.id}</a></td>
                <td>${orderDto.user.email}</td>
                <td class="center-align">${totalBooks.get(orderDto.id)}</td>
                <td class="center-align">${totalPrices.get(orderDto.id)}</td>
                <td class="center-align">${orderDto.status}</td>
            </tr>
        </c:forEach>

    </table>

</div>

</body>
</html>
