<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="review.tittle"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar-review.jsp"/>

    <section id="main">
        <h1>${sessionScope.film.title}</h1>
        <div class="description">
            <c:choose>
                <c:when test="${sessionScope.user.blocked == false and sessionScope.user.rights != null}">

                    <form name="comment" method="POST"
                          action="${pageContext.request.contextPath}/controller?command=comment">
                        <input type="hidden" name="command" value="comment"/>

                        <fmt:message key="comment.comment"/><br/>

                        <label>
                            <textarea type="text" name="comment" rows="20" cols="100"></textarea>
                        </label>

                        <button type="submit" class="signupbtn"><fmt:message key="comment.add"/></button>
                    </form>

                    <c:choose>
                        <c:when test="${requestScope.errorMessage == 'errorEmptyComment'}">
                            <fmt:message key="comment.errorMessageEmpty"/>
                        </c:when>
                    </c:choose>

                </c:when>
            </c:choose>

        </div>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>