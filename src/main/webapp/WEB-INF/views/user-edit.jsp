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
            <form name="admin-edit-user-rate"
                  action="${pageContext.request.contextPath}/controller?command=admin-edit-user-rate&id=${requestScope.user.id}"
                  method="POST">
                <input type="hidden" name="command" value="admin-edit-user-rate&id=${requestScope.user.id}"/>
                <p><fmt:message key="home.user.login"/> ${requestScope.user.login}</p>
                <p><fmt:message key="home.user.rate"/>
                    <label>
                        <input type="text" name="rate" placeholder="${requestScope.user.rate}" maxlength="10">
                    </label>
                </p>
                <p><fmt:message key="home.user.rights"/> ${requestScope.user.rights}</p>
                <p><fmt:message key="home.user.isBlocked"/> ${requestScope.user.blocked}</p>

                <button class="signupbtn" type="submit">
                    <fmt:message key="user.edit.saveUser"/>
                </button>
            </form>

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

            <div class="error-message">
                <c:choose>
                    <c:when test="${requestScope.errorMessage == 'errorRate'}">
                        <fmt:message key="user.edit.errorRate"/>
                    </c:when>
                    <c:when test="${requestScope.errorMessage == 'negativeRate'}">
                        <fmt:message key="user.edit.negativeRate"/>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>