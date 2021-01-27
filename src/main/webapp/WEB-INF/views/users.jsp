<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="users.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar-users.jsp"/>

    <section id="main">
        <h1><fmt:message key="users.h1"/></h1>
        <div class="table">
            <br>
            <table>
                <tr>
                    <th><fmt:message key="users.position"/></th>
                    <th><fmt:message key="users.login"/></th>
                    <th><fmt:message key="users.rate"/></th>
                    <c:if test="${sessionScope.user.rights == 'admin'}">
                        <th><fmt:message key="users.blocked"/></th>
                    </c:if>
                </tr>

                <c:forEach var="user" items="${requestScope.users}" varStatus="index">
                    <c:choose>
                        <c:when test="${sessionScope.user.rights == 'admin'}">
                            <tr>
                                <td>${index.count}</td>
                                <td>
                                    <a href="<c:url value="/controller?command=admin-user-edit&id=${user.id}"/>">${user.login}</a>
                                </td>
                                <td>${user.rate}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.blocked == true}">
                                            <fmt:message key="users.blocked.answer.yes"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="users.blocked.answer.no"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${user.rights != 'admin' or user.blocked == true}">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${user.login}</td>
                                    <td>${user.rate}</td>
                                </tr>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
            <br/>
        </div>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>