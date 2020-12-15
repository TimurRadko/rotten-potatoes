<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${requestScope.lang}">
<header>
    <div class="navbar">

        <a class="navbar-main" href="<c:url value="/controller?command=goToMain"/>">Rotten-Potatoes.by</a>

        <c:choose>
            <c:when test="${sessionScope.login == null}">
                <a class="navbar-login" href="<c:url value="/controller?command=goToLogin"/>"><fmt:message
                        key="label.login"/></a>
            </c:when>
            <c:when test="${sessionScope.login != null}">
                <a class="navbar-login" href="<c:url value="/controller?command=logout"/>"><fmt:message
                        key="label.logout"/></a>
            </c:when>
        </c:choose>

        <div class="navbar-dropdown">
            <button class="navbar-dropbtn"><fmt:message key="label.language"/></button>
            <div class="navbar-dropdown-content">
                <form method="post"
                      action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                    <input type="hidden" name="lang" value="en"/>
                    <button class="navbar-dropbtn" type="submit">EN</button>
                </form>
                <form method="post"
                      action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                    <input type="hidden" name="lang" value="ru"/>
                    <button class="navbar-dropbtn" type="submit">RU</button>
                </form>
                <form method="post"
                      action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                    <input type="hidden" name="lang" value="by"/>
                    <button class="navbar-dropbtn" type="submit">BY</button>
                </form>
            </div>
        </div>

        <c:if test="${sessionScope.login != null}">
            <a class="navbar-username" href="<c:url value="/controller?command=goToHome"/>"><fmt:message
                    key="label.welcome"/> ${sessionScope.login}</a>
        </c:if>
    </div>
</header>
</html>