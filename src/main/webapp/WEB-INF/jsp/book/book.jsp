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

    <h1>Book</h1>
    <h3>${date}</h3>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <p>ID: ${bookDto.id}</p>
    <p>Name: ${bookDto.name}</p>
    <p>Author: ${bookDto.author}</p>
    <p>ISBN: ${bookDto.isbn}</p>
    <p>Pages: ${bookDto.pages}</p>
    <p>Year published: ${bookDto.yearPublished}</p>
    <p>Cover: ${bookDto.cover}</p>
    <p>Price: ${bookDto.price}</p>
    <h3><a href="/book/all">List of all books</a></h3>
</div>
</body>
</html>
