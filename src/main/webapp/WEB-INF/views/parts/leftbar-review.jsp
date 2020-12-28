<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<section id="user-actions">
    <div class="btn-group">
        <c:if test="${sessionScope.rights != null or sessionScope.blocked != true}">
            <a href="<c:url value="/controller?command=goToFilmHome"/>">
                <button><fmt:message key="review.left.goToFilmHome"/></button>
            </a>
        </c:if>

    </div>
</section>
</html>
