<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="user.edit.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar.jsp"/>

    <section id="main">
        <div class="user-description">
            <p><fmt:message key="home.user.login"/> <c:out value="${requestScope.user.login}"/></p>
            <p><fmt:message key="home.user.rate"/> <c:out value="${requestScope.user.rate}"/></p>
            <p><fmt:message key="home.user.rights"/> <c:out value="${requestScope.user.rights}"/></p>
            <p><fmt:message key="home.user.isBlocked"/> <c:out value="${requestScope.user.blocked}"/></p>
        </div>

        <c:choose>
            <c:when test="${requestScope.user.blocked == false}">
                <a href="<c:url value="/controller?command=admin-block-unblock&id=${requestScope.user.id}"/>">
                    <button><fmt:message key="user.edit.block"/></button>
                </a>
            </c:when>
            <c:otherwise>
                <a href="<c:url value="/controller?command=admin-block-unblock&id=${requestScope.user.id}"/>">
                    <button><fmt:message key="user.edit.unblock"/></button>
                </a>
            </c:otherwise>
        </c:choose>

        <a href="<c:url value="/controller?command=admin-add-user-rate&id=${requestScope.user.id}"/>">
            <button><fmt:message key="user.edit.addRate"/></button>
        </a>

        <a href="<c:url value="/controller?command=admin-reduce-user-rate&id=${requestScope.user.id}"/>">
            <button><fmt:message key="user.edit.reduceRate"/></button>
        </a>

    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>