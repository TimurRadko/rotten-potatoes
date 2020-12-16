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
    <jsp:include page="parts/leftbar.jsp"/>

    <section id="main">
        <h1 align="center">${sessionScope.title}</h1>

        <c:choose>
            <c:when test="${sessionScope.rights != 'BLOCKED' and sessionScope.rights != null}">
                <form align="center" name="review" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=review-rate&film_id=${sessionScope.film_id}&user_id=${sessionScope.user_id}">
                    <input type="hidden" name="command" value="review-rate"/>
                    <fmt:message key="review.review"/><br/>
                    <textarea type="text" name="body" rows="20" cols="100"> </textarea>
                    <br/><br/>
                </form>
                <fmt:message key="review.rate"/><br/>
                <form align="center" name="rate" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=review-rate&film_id=${sessionScope.film_id}&user_id=${sessionScope.user_id}">
                    <input type="hidden" name="command" value="review-rate">
                    <input type="hidden" name="film_id" value="${sessionScope.film_id}">
                    <label>1
                        <input type="radio" name="rate" value="1" required>
                    </label>
                    <label>2
                        <input type="radio" name="rate" value="2" required>
                    </label>
                    <label>3
                        <input type="radio" name="rate" value="3" required>
                    </label>
                    <label>4
                        <input type="radio" name="rate" value="4" required>
                    </label>
                    <label>5
                        <input type="radio" name="rate" value="5" required>
                    </label>
                    <label>6
                        <input type="radio" name="rate" value="6" required>
                    </label>
                    <label>7
                        <input type="radio" name="rate" value="7" required>
                    </label>
                    <label>8
                        <input type="radio" name="rate" value="8" required>
                    </label>
                    <label>9
                        <input type="radio" name="rate" value="9" required>
                    </label>
                    <label>10
                        <input type="radio" name="rate" value="10" required>
                    </label>
                    <input type="submit" value="<fmt:message key="review.add"/>">
                </form>

                <%--            I checked in this place sessionScope--%>
                User:
                ${sessionScope.user_id}

                Film:
                ${sessionScope.film_id}
                ${sessionScope.title}
                ${sessionScope.director}

            </c:when>
            <c:otherwise>
                Hello! Change This!!!!
            </c:otherwise>
        </c:choose>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>