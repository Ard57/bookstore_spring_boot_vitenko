<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
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

        <form method="post" action="/user/${userDto.id}/edit">
            <label>Email:
                <input name="email" type="email" value="${userDto.email}" placeholder="your@email" required>
                <br>
            </label>
            <label>First Name:
                <input name="firstName" type="text" value="${userDto.firstName}" placeholder="First Name">
                <br>
            </label>
            <label>Last Name:
                <input name="lastName" type="text" value="${userDto.lastName}" placeholder="Last Name">
                <br>
            </label>
            <p>Role:</p>
            <input name="role" type="radio" value="CUSTOMER" id="radio_customer" <c:if
                    test="${userDto.role == 'CUSTOMER'}"> checked </c:if>>
            <label for="radio_customer">CUSTOMER</label>
            <br>

            <input name="role" type="radio" value="MANAGER" id="radio_manager" <c:if
                    test="${userDto.role == 'MANAGER'}"> checked </c:if>>
            <label for="radio_manager">MANAGER</label>
            <br>

            <input name="role" type="radio" value="ADMIN" id="radio_admin" <c:if test="${userDto.role == 'ADMIN'}">
                   checked </c:if>>
            <label for="radio_admin">ADMIN</label>
            <br>

            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>
