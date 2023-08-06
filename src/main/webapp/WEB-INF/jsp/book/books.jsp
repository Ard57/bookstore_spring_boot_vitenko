<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script defer src="/js/lib/jquery-3.7.0.js"></script>
    <script defer src="/js/pagination.js"></script>
    <script defer src="/js/books.js"></script>
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

    <div class="pagination">

    </div>

    <table>
        <thead>
        <tr>
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
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>
