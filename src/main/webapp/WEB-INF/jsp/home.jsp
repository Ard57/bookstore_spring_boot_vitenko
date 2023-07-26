<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="content">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1 class="">Welcome to the bookstore!</h1>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>
</div>
</body>
</html>
