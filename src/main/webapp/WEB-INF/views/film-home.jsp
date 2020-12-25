<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>${sessionScope.title}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar-film-home.jsp"/>

    <section id="main">
        <h1>${sessionScope.title}</h1>

        <div class="description">
            <p><img class="poster" alt="${sessionScope.title}" src="${sessionScope.poster}"/></p>
            <p><fmt:message key="film.director"/> <a
                    href="<c:url value="/controller?command=director&director=${sessionScope.director}"/>">${sessionScope.director}</a>
            </p>
            <p><fmt:message key="film.avgRate"/> ${sessionScope.avgRate}</p>
        </div>

        <c:if test="${sessionScope.rights != 'BLOCKED' and sessionScope.rights != null}">
            <a href="<c:url value="/controller?command=goToReview"/>">
                <button><fmt:message key="film.addRate"/></button>
            </a>

            <a href="<c:url value="/controller?command=goToComment"/>">
                <button><fmt:message key="film.addComment"/></button>
            </a>
        </c:if>

        <c:if test="${requestScope.user_review != null}">
            <div class="table">
                <table>
                    <tr>
                        <th><fmt:message key="film.user.actions.userLogin"/></th>
                        <th><fmt:message key="film.user.actions.review"/></th>
                        <th><fmt:message key="film.user.actions.rate"/></th>
                    </tr>

                    <c:forEach var="userReview" items="${requestScope.user_review}" varStatus="index">
                        <tr>
                            <td>${userReview.login}</td>
                            <td>${userReview.review}</td>
                            <td>${userReview.filmRate}</td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </c:if>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>