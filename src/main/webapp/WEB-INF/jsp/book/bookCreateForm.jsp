<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="content">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <div class="form">

        <form method="post" action="/book/create">
            <label>Name:
                <input name="name" type="text" value="${bookDto.name}" placeholder="Book Name" required>
                <br>
            </label>
            <label>Author:
                <input name="author" type="text" value="${bookDto.author}" placeholder="Author" required>
                <br>
            </label>
            <label>ISBN:
                <input name="isbn" type="text" value="${bookDto.isbn}" placeholder="ISBN" required>
                <br>
            </label>
            <label>Pages:
                <input name="pages" type="number" step="1" value="${bookDto.pages}"
                       placeholder="Number of pages" required>
                <br>
            </label>
            <label>Year Published:
                <input name="yearPublished" type="number" step="1" value="${bookDto.yearPublished}"
                       placeholder="Year of publication of the book" required>
                <br>
            </label>
            <label>Price:
                <input name="price" type="number" step="0.01" value="${bookDto.price}" placeholder="Price" required>
                <br>
            </label>

            <p>Cover:</p>
            <input name="cover" type="radio" value="HARD" id="radio_hard">
            <label for="radio_hard">HARD</label>
            <br>

            <input name="cover" type="radio" value="SOFT" id="radio_soft">
            <label for="radio_soft">SOFT</label>
            <br>

            <input name="cover" type="radio" value="SPECIAL" id="radio_special">
            <label for="radio_special">SPECIAL</label>
            <br>

            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>
