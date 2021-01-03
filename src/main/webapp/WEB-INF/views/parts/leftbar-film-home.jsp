<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<section id="user-actions">
    <div class="btn-group">
        <c:choose>
            <c:when test="${sessionScope.rights == null or sessionScope.blocked == true}">
            <a href="<c:url value="/controller?command=films"/>">
                <button><fmt:message key="main.left.return"/></button>
            </a>
            </c:when>
            <c:when test="${sessionScope.rights == 'user'}">
                <a href="<c:url value="/controller?command=show-reviews"/>">
                    <button><fmt:message key="main.left.showReview"/></button>
                </a>
                <a href="<c:url value="/controller?command=show-comments"/>">
                    <button><fmt:message key="main.left.showComments"/></button>
                </a>
                <a href="<c:url value="/controller?command=films"/>">
                    <button><fmt:message key="main.left.return"/></button>
                </a>
            </c:when>
            <c:when test="${sessionScope.rights == 'admin'}">
                <a href="<c:url value="/controller?command=show-reviews"/>">
                    <button><fmt:message key="main.left.showReview"/></button>
                </a>
                <a href="<c:url value="/controller?command=show-comments"/>">
                    <button><fmt:message key="main.left.showComments"/></button>
                </a>
                <a href="<c:url value="/controller?command=films"/>">
                    <button><fmt:message key="main.left.return"/></button>
                </a>
                <a href="<c:url value="/controller?command=admin-delete-film&id=${requestScope.film.id}"/>">
                    <button><fmt:message key="main.left.deleteFilm"/></button>
                </a>
            </c:when>
        </c:choose>
    </div>
</section>
</html>