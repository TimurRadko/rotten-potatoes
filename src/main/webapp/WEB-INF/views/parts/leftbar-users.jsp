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
                    <button><fmt:message key="main.left.films"/></button>
                </a>
            </c:when>
            <c:when test="${sessionScope.login != null}">

                <c:choose>

                    <c:when test="${sessionScope.rights == 'admin'}">
                        <a href="<c:url value="/controller?command=films"/>">
                            <button><fmt:message key="main.left.editFilms"/></button>
                        </a>
                    </c:when>

                    <c:when test="${sessionScope.rights == 'user'}">
                        <a href="<c:url value="/controller?command=films"/>">
                            <button><fmt:message key="main.left.films"/></button>
                        </a>
                    </c:when>
                </c:choose>
            </c:when>
        </c:choose>
    </div>
</section>
</html>