<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>${sessionScope.film.title}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar-film-home.jsp"/>

    <section id="main">
        <h1>${sessionScope.film.title}</h1>

        <div class="description">
            <p><img class="poster" alt="${sessionScope.film.title}" src="${sessionScope.film.poster}"/></p>
            <p><fmt:message key="film.director"/>
                <a href="<c:url value="/controller?command=director&director=${sessionScope.film.director}"/>">
                    ${sessionScope.film.director}</a>
            </p>
            <p><fmt:message key="film.avgRate"/> ${sessionScope.film.defaultRate}</p>
            <br/>

            <c:if test="${requestScope.errorMessage == 'errorEmptyData'}">
                <fmt:message key="film.home.errorEmptyData"/>
            </c:if>
        </div>

        <c:if test="${sessionScope.user.rights != null and sessionScope.user.blocked == false}">

            <ctg:access accessName="user">
                <a href="<c:url value="/controller?command=goToReview"/>">
                    <button><fmt:message key="film.addRate"/></button>
                </a>
            </ctg:access>

            <c:if test="${requestScope.user_reviews != null}">
                <div class="table">
                    <table>
                        <tr>
                            <th><fmt:message key="film.user.actions.userLogin"/></th>
                            <th><fmt:message key="film.user.actions.review"/></th>
                            <th><fmt:message key="film.user.actions.rate"/></th>
                            <th><ctg:access accessName="admin"><fmt:message key="film.admin.action"/></ctg:access></th>
                        </tr>

                        <c:forEach var="userReviewDto" items="${requestScope.user_reviews}" varStatus="index">
                            <tr>
                                <td>${userReviewDto.login}</td>
                                <td>${userReviewDto.review}</td>
                                <td>${userReviewDto.filmRate}</td>
                                <td>
                                    <ctg:access accessName="admin">
                                        <a href="<c:url value="/controller?command=admin-delete-review&id=${userReviewDto.id}"/>">
                                            <button><fmt:message key="film.admin.action.deleteReview"/></button>
                                        </a>
                                    </ctg:access>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </c:if>

            <ctg:access accessName="user">
                <a href="<c:url value="/controller?command=goToComment"/>">
                    <button><fmt:message key="film.addComment"/></button>
                </a>
            </ctg:access>

            <c:if test="${requestScope.user_comments != null}">
                <div class="table">
                    <table>
                        <tr>
                            <th><fmt:message key="film.user.actions.userLogin"/></th>
                            <th><fmt:message key="film.user.actions.comment"/></th>
                            <th><ctg:access accessName="admin"><fmt:message key="film.admin.action"/></ctg:access></th>
                        </tr>

                        <c:forEach var="userCommentDto" items="${requestScope.user_comments}" varStatus="index">
                            <tr>
                                <td>${userCommentDto.login}</td>
                                <td>${userCommentDto.comment}</td>
                                <td>
                                    <ctg:access accessName="admin">
                                        <a href="<c:url value="/controller?command=admin-delete-comment&id=${userCommentDto.id}"/>">
                                            <button><fmt:message key="film.admin.action.deleteComment"/></button>
                                        </a>
                                    </ctg:access>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </c:if>
        </c:if>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>