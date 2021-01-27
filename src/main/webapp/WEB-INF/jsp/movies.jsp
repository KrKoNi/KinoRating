<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <jsp:directive.include file="../static/header.jsp"/>
    <style type="text/css">
        <%@ include file="../assets/css/stars.css" %>
    </style>
    <link href="../assets/css/stars.css" rel="stylesheet" type="text/css">
</head>

<body>
    <jsp:directive.include file="../static/navbar.jsp"/>

    <c:set var="user" value="${pageContext.session.getAttribute('user')}"/>
    <c:set var="movies" value="${pageContext.request.getAttribute('movies')}"/>
    <div class="row row-cols-1 row-cols-md-4">
    <c:forEach items="${movies}" var="movie">
        <div class="col mb-4">
            <div class="card bg-dark">
                <a href="${pageContext.request.contextPath}/app/movie?id=${movie.id}">
                    <img height="300px" src="${movie.imageLink}" class="card-img-top" alt="${movie.title}">
                </a>
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${movie.title}"/></h5>
                    <p class="card-text"><c:out value="${movie.shortDescription}"/></p>

                    <jsp:directive.include file="../static/stars.jsp"/>

                </div>
            </div>
        </div>
    </c:forEach>
    </div>
    <jsp:directive.include file="../static/pagination.jsp"/>
</body>
<jsp:directive.include file="../static/footer.jsp"/>
</html>
