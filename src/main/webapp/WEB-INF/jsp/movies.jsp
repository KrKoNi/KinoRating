<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:directive.include file="../static/header.jsp"/>

<head>
    <style type="text/css">
        <%@ include file="../assets/css/stars.css" %>
    </style>
</head>

<body>
    <jsp:directive.include file="../static/navbar.jsp"/>

    <h1>Movies</h1>

    <c:set var="user" value="${pageContext.session.getAttribute('user')}"/>
    <c:set var="movies" value="${pageContext.request.getAttribute('movies')}"/>
    <div class="row row-cols-1 row-cols-md-4">
    <c:forEach items="${movies}" var="movie">
        <div class="col mb-4">
            <div class="card bg-dark">
                <a href="?command=movie&movie_id=${movie.id}">
                    <img height="300px" src="${movie.imageLink}" class="card-img-top" alt="${movie.title}">
                </a>
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${movie.title}"/></h5>
                    <p class="card-text"><c:out value="${movie.shortDescription}"/></p>
                    <c:set var="user_rating" value="${movie.rates.get(user.id)}"/>
                    <c:if test="${user != null}">
                        <div class="container mb-5">
                            <div class="star-widget">
                                <form method="post" action="?command=movie-rate">
                                    <input hidden name="movie_id" value="${movie.id}"/>
                                    <label class="fas fa-star">
                                        <input type="submit" name="rate" value="5">
                                    </label>
                                    <label class="fas fa-star">
                                        <input type="submit" name="rate" value="4">
                                    </label>
                                    <label class="fas fa-star">
                                        <input type="submit" name="rate" value="3">
                                    </label>
                                    <label class="fas fa-star">
                                        <input type="submit" name="rate" value="2">
                                    </label>
                                    <label class="fas fa-star">
                                        <input type="submit" name="rate" value="1">
                                    </label>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
                <c:if test="${user != null && user.role == Role.ADMIN}">
                    <div class="card-footer">
                        <form method="post" action="?command=movie_edit">
                            <input hidden name="movie_id" value="${movie.id}"/>
                            <button type="submit" class="btn btn-secondary btn-sm">Edit</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </c:forEach>
    </div>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item bg-dark"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item bg-dark"><a class="page-link" href="${pageContext.request.contextPath}?command=movies&page=1">1</a></li>
            <li class="page-item bg-dark"><a class="page-link" href="${pageContext.request.contextPath}?command=movies&page=2">2</a></li>
            <li class="page-item bg-dark"><a class="page-link" href="${pageContext.request.contextPath}?command=movies&page=3">3</a></li>
            <li class="page-item bg-dark"><a class="page-link" href="#">Next</a></li>
        </ul>
    </nav>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>

<jsp:directive.include file="../static/footer.jsp"/>
</html>
