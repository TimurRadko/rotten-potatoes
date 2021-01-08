<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="film.edit.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div id="tableContainer">
    <jsp:include page="parts/leftbar-home.jsp"/>

    <section id="main">
        <div class="description">
            <form name="admin-add-film" enctype='multipart/form-data'
                  action="${pageContext.request.contextPath}/controller?command=admin-add-film" method="POST">
                <input type="hidden" name="command" value="admin-add-film"/>

                <h1><fmt:message key="film.edit.name.title"/></h1>

                <label>
                    <input type="text" name="title" placeholder="<fmt:message key="film.edit.name.title.placeholder"/>"
                           maxlength="255" required>
                </label>

                <img src="<c:url value="/static/images/default/noposter.jpg"/>" alt="no poster">
                <br/>
                <div>
                    <input class="signupbtn" type="file" name="poster-path" accept="image/jpeg,image/png">
                </div>

                <br/>
                <label for="director"><fmt:message key="film.director"/></label>
                <input type="text" id="director" name="director"
                       placeholder="<fmt:message key="film.edit.input.director.placeholder"/>" maxlength="45">

                <button class="signupbtn" type="submit">
                    <fmt:message key="film.edit.saveFilm"/>
                </button>

                <button class="signupbtn" onclick="history.back();">
                    <fmt:message key="film.edit.cancelSave"/>
                </button>

                <div class="error-message">
                    <c:if test="${requestScope.errorMessage == 'errorEmptyData'}">
                        <fmt:message key="film.edit.errorEmpty"/>
                    </c:if>
                    <c:if test="${requestScope.errorMessage == 'invalidFilmData'}">
                        <fmt:message key="film.edit.invalidFilmData"/>
                    </c:if>
                </div>

            </form>
        </div>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>