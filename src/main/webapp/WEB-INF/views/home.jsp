<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="home.user.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar-home.jsp"/>

    <section id="main">

        <div class="user-description">
            <p><fmt:message key="home.user.login"/> <c:out value="${sessionScope.login}"/></p>
            <p><fmt:message key="home.user.rate"/> <c:out value="${sessionScope.rate}"/> <fmt:message
                    key="home.user.rate.description"/></p>
            <p><fmt:message key="home.user.rights"/> <c:out value="${sessionScope.rights}"/></p>
        </div>

    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
