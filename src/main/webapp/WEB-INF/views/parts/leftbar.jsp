<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<section id="user-actions">
    <div class="btn-group">
        <c:choose>
            <c:when test="${sessionScope.rights == null or sessionScope.blocked == true}">
                <a href="<c:url value="/controller?command=users"/>">
                    <button><fmt:message key="main.left.users"/></button>
                </a>
            </c:when>
            <c:when test="${sessionScope.login != null}">
                <ctg:access accessName="admin">
                    <a href="<c:url value="/controller?command=users"/>">
                        <button><fmt:message key="main.left.editUsers"/></button>
                    </a>
                    <a href="<c:url value="/controller?command=admin-goToAddFilm"/>">
                        <button><fmt:message key="main.left.addFilm"/></button>
                    </a>
                </ctg:access>

                <ctg:access accessName="user">
                    <a href="<c:url value="/controller?command=users"/>">
                        <button><fmt:message key="main.left.users"/></button>
                    </a>
                </ctg:access>
            </c:when>
        </c:choose>
    </div>
</section>
</html>