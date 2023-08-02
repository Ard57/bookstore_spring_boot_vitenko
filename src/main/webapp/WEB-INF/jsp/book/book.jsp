<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script defer src="/js/jquery-3.7.0.js"></script>
    <script defer src="/js/book.js"></script>
</head>
<body>


<div class="content entity-page">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1>Book</h1>
    <h3>${date}</h3>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>
    <div class="book-fields">

    </div>
    <div class="actions">
        <h3>
            <a href="/book/all">List of all books</a>
            <a href="/cart/add/${bookDto.id}">Add to Cart</a>
        </h3>
    </div>
</div>
</body>
</html>
