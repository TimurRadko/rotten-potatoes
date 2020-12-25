<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<section id="user-actions">
    <c:choose>
        <c:when test="${sessionScope.login != null}">

            <c:choose>

                <c:when test="${sessionScope.rights == 'ADMIN'}">
                    <div class="user-actions-list">
                        <ul>
                            <a href="<c:url value="/controller?command=title"/>"><fmt:message
                                    key="main.left.editUsers"/></a>
                            <a href="<c:url value="/controller?command=director"/>"><fmt:message
                                    key="main.left.editFilms"/></a>
                        </ul>
                    </div>
                </c:when>

                <c:when test="${sessionScope.rights == 'USER'}">
                    <div class="user-actions-list">
                        <ul>
                            <a href="<c:url value="/controller?command=title"/>"><fmt:message
                                    key="main.left.editUsers"/></a>
                            <a href="<c:url value="/controller?command=director"/>"><fmt:message
                                    key="main.left.editFilms"/></a>
                        </ul>
                    </div>
                </c:when>

            </c:choose>

        </c:when>

    </c:choose>
</section>
</html>