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
    <jsp:include page="parts/leftbar-review.jsp"/>

    <section id="main">
        <div class="description">
            <form name="admin-edit-film" enctype='multipart/form-data'
                  action="${pageContext.request.contextPath}/controller?command=admin-edit-film"
                  method="POST">
                <input type="hidden" name="command" value="admin-edit-film"/>

                <p><label for="title"><fmt:message key="films.title"/></label>
                    <input type="text" id="title" name="title" value="${sessionScope.film.title}" maxlength="255"/>
                </p>

                <p><img class="poster" src="${sessionScope.film.poster}" alt="poster"></p>
                <div>
                    <input class="signupbtn" type="file" name="poster-path" accept="image/jpeg,image/png">
                </div>

                <p><label for="director"><fmt:message key="film.director"/></label>
                    <input type="text" id="director" name="director"
                           value="${sessionScope.film.director}" maxlength="45"/>
                </p>

                <p><fmt:message key="film.avgRate"/> ${sessionScope.film.defaultRate}</p>

                <button class="signupbtn" type="submit">
                    <fmt:message key="film.admin.action.saveEditingFilm"/>
                </button>

                <button class="signupbtn" onclick="history.back();">
                    <fmt:message key="film.admin.action.cancelEditingSave"/>
                </button>
            </form>
        </div>
    </section>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>