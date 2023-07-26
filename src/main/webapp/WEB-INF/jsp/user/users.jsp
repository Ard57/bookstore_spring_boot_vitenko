<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="content">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1>List of all users</h1>

    <c:if test="${requestScope.message != null}">
        <div class="message-info">
                ${requestScope.message}
        </div>
    </c:if>

    <table>
        <tr>
            <th>#</th>
            <th>ID</th>
            <th>Email</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Password</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>

        <c:forEach items="${userDtos}" var="userDto" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td class="center-align"><a href="/user/show?id=${userDto.id}">${userDto.id}</a></td>
                <td>${userDto.email}</td>
                <td>${userDto.lastName}</td>
                <td>${userDto.firstName}</td>
                <td>${userDto.password}</td>
                <td class="center-align">${userDto.role}</td>
                <td class="center-align"><a href="/user/edit?id=${userDto.id}">Edit</a></td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
