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
<c:set var="movie" value="${ pageContext.request.getAttribute('movie')}"/>

<div class="card bg-dark">
    <img src="https://thumbs.filmix.co/posters/orig/klinika-scrubs-serial-2010_88952_0.jpg" class="card-img-top"
         alt="...">
    <div class="card-body">
        <h5 class="card-title"><c:out value="${movie.title}"/></h5>
        <p class="card-text"><c:out value="${movie.shortDescription}"/></p>
        <c:set var="user_rating" value="${movie.rates.get(user.id)}"/>
        <c:if test="${user != null}">
            <c:if test="${user_rating != null}">
                <p>Your rating: <c:out value="${user_rating}"/></p>
            </c:if>
            <div class="container">
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
            <c:if test="${user.role == Role.ADMIN}">
                <div class="card-footer">
                    <form method="post" action="?command=movie_edit">
                        <input hidden name="movie_id" value="${movie.id}"/>
                        <button type="submit" class="btn btn-secondary btn-sm">Edit</button>
                    </form>
                </div>
            </c:if>
        </c:if>
    </div>
</div>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>

<jsp:directive.include file="../static/footer.jsp"/>
</html>
