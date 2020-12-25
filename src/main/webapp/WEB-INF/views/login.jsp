<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="login.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/login.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div class="container">
    <h1><fmt:message key="login.sign.up"/></h1>

    <p><fmt:message key="login.action"/></p>

    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login"/>
        <fmt:message key="login.login"/> <input type="text" name="login"/><br/>
        <br/>
        <fmt:message key="login.password"/> <input type="password" name="password"/><br/>
        <button type="submit" class="signupbtn"><fmt:message key="login.submit"/></button>
        <br/>
    </form>

    <c:choose>
        <c:when test="${requestScope.errorMessage == 'errorLogin'}">
            <fmt:message key="login.errorLogin"/>
        </c:when>
        <c:when test="${requestScope.errorMessage == 'errorBlocked'}">
            <fmt:message key="login.errorBlocked"/>
        </c:when>
    </c:choose>

</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>