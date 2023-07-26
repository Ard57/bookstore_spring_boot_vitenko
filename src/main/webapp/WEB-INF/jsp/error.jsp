<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="content">

    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>

    <h1>Error</h1>
    <div class="message-error">
        <h3>An error has occurred</h3>
        <p>Error message: ${requestScope.message}</p>
        <p>${requestScope.help}</p>
    </div>
</div>
</body>
</html>
