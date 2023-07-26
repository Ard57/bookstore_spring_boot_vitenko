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

    <h1>User</h1>
    <h3>${date}</h3>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <p>ID: ${userDto.id}</p>
    <p>Email: ${userDto.email}</p>
    <p>Name: ${userDto.lastName} ${userDto.firstName}</p>
    <p>Password: ${userDto.password}</p>
    <p>Role: ${userDto.role}</p>
    <h3><a href="/user/all">List of all users</a>
        <a href="/user/edit?id=${userDto.id}">Edit</a>
    </h3>
</div>
</body>
</html>
