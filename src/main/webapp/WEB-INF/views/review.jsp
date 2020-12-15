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
                <form align="center" action="${pageContext.request.contextPath}/controller?command=review"
                      method="POST">
                    <fmt:message key="review.review"/><br/>
                    <textarea type="text" name="body" rows="20" cols="100"> </textarea>
                    <br/><br/>
                    <fmt:message key="review.rate"/><br/>
                    <select name="rate">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                    <button><fmt:message key="review.add"/></button>
                </form>
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