<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${requestScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>${sessionScope.director}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar-users.jsp"/>

    <section id="main">
        <h1>${sessionScope.director}</h1>

        <div class="table">
            <table>
                <tr>
                    <th><fmt:message key="films.number"/></th>
                    <th><fmt:message key="films.title"/></th>
                    <th><fmt:message key="films.avgRate"/></th>
                </tr>

                <c:forEach var="film" items="${requestScope.films}" varStatus="index">
                    <tr>
                        <td>${index.count}</td>
                        <td><a href="<c:url value="/controller?command=film-home&id=${film.id}"/>">${film.title}</a>
                        </td>
                        <td>${film.avgRate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>
