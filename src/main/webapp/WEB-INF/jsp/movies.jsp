<%@ page import="com.epam.jwd.domain.Movie" %>

<%@ page import="java.util.List" %>
<%@ page import="com.epam.jwd.dto.impl.UserDTO" %>
<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page import="com.epam.jwd.strategy.impl.MoviesPageAction" %>
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
<%
    UserDTO userDTO = (UserDTO) session.getAttribute("user");
%>
    <c:set var="movies" value="${ pageContext.request.getAttribute('movies')}"/>
    <c:forEach items="${movies}" var="movie">
    <div class="card mb-3 bg-dark">
        <div class="row g-0">
            <div class="col-md-4">
                <img height="200px" src="https://thumbs.filmix.co/posters/orig/klinika-scrubs-serial-2010_88952_0.jpg" alt="${movie.title}">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${movie.title}"/></h5>
                    <p class="card-text"><c:out value="${movie.shortDescription}"/></p>
                    <c
                    <% if (userDTO != null) { %>
                        <div class="star-block"><jsp:directive.include file="../static/stars.jsp"/></div>
                    <% } %>
                    <% if (userDTO != null && userDTO.getRole() == Role.ADMIN) { %>
                    <form method="post" action="?command=movie_edit">
                        <input hidden name="movie_id" value="${movie.id}"/>
                        <button type="submit">Edit</button>
                    </form>
                    <% } %>
                </div>

            </div>
        </div>
    </div>
    </c:forEach>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>

<jsp:directive.include file="../static/footer.jsp"/>
</html>
