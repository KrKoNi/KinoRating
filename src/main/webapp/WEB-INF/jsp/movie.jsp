<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:directive.include file="../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="../static/navbar.jsp"/>
<c:set var="user" value="${pageContext.session.getAttribute('user')}"/>
<c:set var="movie" value="${pageContext.request.getAttribute('movie')}"/>

<div class="card mb-3 bg-dark">
    <div class="row g-0">
        <div class="col-md-4">
            <img height="300px" src="${movie.imageLink}" alt="${movie.title}">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${movie.title}"/></h5>
                <br/>
                <p class="card-text"><c:out value="${movie.shortDescription}"/></p>
            </div>
        </div>
        <div class="card-footer">
            <div class="container mb-3" style="width: 200px; min-width: 100px">
                <div class="star-widget">
                    <form method="post" action="${pageContext.request.contextPath}/app/rate">
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
        </div>
    </div>
</div>

<div>
    <p><c:out value="${movie.description}"/></p>
</div>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>

<jsp:directive.include file="../static/footer.jsp"/>
</html>
