<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="content entity-page">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1>Order</h1>
    <h3>${date}</h3>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <p>ID: ${orderDto.id}</p>
    <p>Buyer: ${orderDto.user.email}</p>
    <p>Order status: ${orderDto.status}</p>
    <p>Total price: ${totalPrice}</p>
    <p>List of books</p>

    <table>
        <tr>
            <th>#</th>
            <th>Item ID</th>
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

        <c:forEach items="${orderDto.orderItems}" var="orderItem" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td class="center-align">${orderItem.id}</td>
            <td class="center-align"><a href="/book/show?id=${orderItem.book.id}">${orderItem.book.id}</a></td>
            <td>${orderItem.book.name}</td>
            <td>${orderItem.book.author}</td>
            <td>${orderItem.book.isbn}</td>
            <td class="center-align">${orderItem.book.pages}</td>
            <td class="center-align">${orderItem.book.yearPublished}</td>
            <td class="center-align">${orderItem.book.cover}</td>
            <td class="center-align">${orderItem.amount}</td>
            <td> ${orderItem.amount} * ${orderItem.price} = ${orderItem.amount * orderItem.price}</td>
        </tr>
        </c:forEach>

    </table>

    <h3><a href="/order/all">List of all orders</a></h3>

</div>
</body>
</html>
