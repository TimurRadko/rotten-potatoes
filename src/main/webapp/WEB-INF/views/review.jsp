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
                <c:when test="${sessionScope.blocked == false and sessionScope.rights != null}">

                    <form name="review" method="POST"
                          action="${pageContext.request.contextPath}/controller?command=review-rate">
                        <input type="hidden" name="command" value="review-rate"/>

                        <fmt:message key="review.review"/><br/>

                        <label>
                            <textarea type="text" name="review" rows="20" cols="100"></textarea>
                        </label>

                        <br/><br/>
                        <fmt:message key="review.rate"/><br/>

                        <input type="hidden" name="film_id" value="${sessionScope.film_id}">
                        <label><input type="radio" name="film_rate" value="1" required> 1 </label>
                        <label><input type="radio" name="film_rate" value="2" required> 2 </label>
                        <label><input type="radio" name="film_rate" value="3" required> 3 </label>
                        <label><input type="radio" name="film_rate" value="4" required> 4 </label>
                        <label><input type="radio" name="film_rate" value="5" required> 5 </label>
                        <label><input type="radio" name="film_rate" value="6" required> 6 </label>
                        <label><input type="radio" name="film_rate" value="7" required> 7 </label>
                        <label><input type="radio" name="film_rate" value="8" required> 8 </label>
                        <label><input type="radio" name="film_rate" value="9" required> 9 </label>
                        <label><input type="radio" name="film_rate" value="10" required> 10 </label><br/><br/>

                        <button type="submit" class="signupbtn"><fmt:message key="review.add"/></button>
                    </form>

                    <c:choose>
                        <c:when test="${requestScope.errorMessage == 'errorEmptyReview'}">
                            <fmt:message key="review.errorMessageEmpty"/>
                        </c:when>
                        <c:when test="${requestScope.errorMessage == 'errorRepeatedReview'}">
                            <fmt:message key="review.errorMessageRepeated"/>
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