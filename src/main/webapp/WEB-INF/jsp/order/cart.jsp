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

    <p>Total price: ${totalPrice}</p>
    <p>List of books</p>

    <table>
        <tr>
            <th>#</th>
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
                <td class="center-align"><a href="/book/${orderItem.book.id}">${orderItem.book.id}</a></td>
                <td>${orderItem.book.name}</td>
                <td>${orderItem.book.author}</td>
                <td>${orderItem.book.isbn}</td>
                <td class="center-align">${orderItem.book.pages}</td>
                <td class="center-align">${orderItem.book.yearPublished}</td>
                <td class="center-align">${orderItem.book.cover}</td>

                <td class="center-align">
                    <form method="post" action="/cart/add">
                        <input name="book_id" type="hidden" value="${orderItem.book.id}">
                        <input name="amount" type="hidden" value="-1">
                        <input type="submit" value="-">
                    </form>

                        ${orderItem.amount}

                    <form method="post" action="/cart/add">
                        <input name="book_id" type="hidden" value="${orderItem.book.id}">
                        <input name="amount" type="hidden" value="1">
                        <input type="submit" value="+">
                    </form>
                </td>

                <td> ${orderItem.amount} * ${orderItem.price} = ${orderItem.amount * orderItem.price}</td>
            </tr>
        </c:forEach>

    </table>

    <form method="post" action="\cart\purchase">
        <input class="purchase" type="submit" value="Purchase"
        <c:if test="${purchasePossible == false}"> disabled="disabled" title="${purchaseHelp}" </c:if>>
    </form>

    <h3><a href="/book/all">Book Catalog</a></h3>

</div>
</body>
</html>
