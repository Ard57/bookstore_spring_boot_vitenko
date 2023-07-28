<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="content">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1>List of all books</h1>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <table>
        <tr>
            <th>#</th>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Pages</th>
            <th>Year published</th>
            <th>Cover</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>

        <c:forEach items="${bookDtos}" var="bookDto" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="/book/${bookDto.id}">${bookDto.id}</a></td>
                <td>${bookDto.name}</td>
                <td>${bookDto.author}</td>
                <td class="center-align">${bookDto.isbn}</td>
                <td class="center-align">${bookDto.pages}</td>
                <td class="center-align">${bookDto.yearPublished}</td>
                <td class="center-align">${bookDto.cover}</td>
                <td class="center-align">${bookDto.price}</td>
                <td class="center-align"><a href="/cart/add?book_id=${bookDto.id}">Add to Cart</a></td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
