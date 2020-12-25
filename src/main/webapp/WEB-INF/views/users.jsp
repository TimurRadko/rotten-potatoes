<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <th><fmt:message key="users.login"/></th>
                    <th><fmt:message key="users.rate"/></th>
                </tr>

                <c:forEach var="user" items="${requestScope.users}" varStatus="index">
                    <c:choose>
                        <c:when test="${sessionScope.rights == 'ADMIN'}">
                            <tr>
                                <td>${index.count}</td>
                                <td>
                                    <a href="<c:url value="/controller?command=admin-user-edit&id=${user.id}"/>">${user.login}</a>
                                </td>
                                <td>${user.rate}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${user.rights != 'ADMIN' or user.blocked == true}">
                                <tr>
                                    <td>${user.login}</td>
                                    <td>${user.rate}</td>
                                </tr>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
            </br>
        </div>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>