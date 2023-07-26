<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create User</title>
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

        <form method="post" action="/register">
            <label>Email:
                <input name="email" type="email" placeholder="your@email" required> <br>
            </label>
            <label>Password:
                <input name="password" type="password" placeholder="Password" required> <br>
            </label>
            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>
