<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<section id="user-actions">
    <div class="btn-group">
        <a href="<c:url value="/controller?command=show-review"/>">
            <button><fmt:message key="main.left.showReview"/></button>
        </a>
        <a href="<c:url value="/controller?command=show-comments"/>">
            <button><fmt:message key="main.left.showComments"/></button>
        </a>
        <a href="<c:url value="/controller?command=goToMain"/>">
            <button><fmt:message key="main.left.return"/></button>
        </a>
    </div>
</section>
</html>
