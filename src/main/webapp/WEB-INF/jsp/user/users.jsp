<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script defer src="/js/jquery-3.7.0.js"></script>
    <script defer src="/js/users.js"></script>
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
        <thead>
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Password</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        </tbody>

    </table>
</div>
</body>
</html>
