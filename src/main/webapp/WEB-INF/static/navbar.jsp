<%@ page import="com.epam.jwd.dto.impl.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% UserDTO user = (UserDTO) session.getAttribute("user"); %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}?command=home">KinoRating</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=movies">Movies</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=tv">TV-Series</a>
                </li>
                <% if (user == null) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}?command=login">Log in</a>
                    </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=signup">Sign up</a>
                </li>
                <% } else {%>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}?command=logout">Log out</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
