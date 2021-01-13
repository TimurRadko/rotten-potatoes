<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="films.pageTitle"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar.jsp"/>

    <section id="main">
        <h1><fmt:message key="films.h1"/></h1>

        <div class="table">
            <table>
                <tr>
                    <th><fmt:message key="films.position"/></th>
                    <th><fmt:message key="films.title"/></th>
                    <th><fmt:message key="films.director"/></th>
                    <th><fmt:message key="films.avgRate"/></th>
                </tr>

                <c:forEach var="film" items="${requestScope.films}" varStatus="index">
                    <tr>
                        <td>${(5)*(requestScope.currentPage - 1) + index.count}</td>
                        <td><a href="<c:url value="/controller?command=film-home&id=${film.id}"/>">${film.title}</a>
                        </td>
                        <td>
                            <a href="<c:url value="/controller?command=director&director=${film.director}"/>">${film.director}</a>
                        </td>
                        <td>${film.defaultRate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="pagination-text">
            <c:if test="${(requestScope.currentPage - 1) != 0}">
                <a class="pagination"
                   href="${pageContext.request.contextPath}/controller?command=films&currentPage=${requestScope.currentPage-1}"
                   type="submit">❮❮</a>
            </c:if>
            ${requestScope.currentPage}
            <c:if test="${requestScope.films.size() == 5}">
                <a class="pagination"
                   href="${pageContext.request.contextPath}/controller?command=films&currentPage=${requestScope.currentPage+1}"
                   type="submit">❯❯</a>
            </c:if>
        </div>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>