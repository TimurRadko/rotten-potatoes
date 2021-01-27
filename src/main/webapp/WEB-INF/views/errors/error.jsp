<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles/main.css">
</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div><h1 align="center"><fmt:message key="error.message"/></h1></div>
<jsp:include page="../parts/footer.jsp"/>
</body>
</html>