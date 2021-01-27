<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:directive.include file="../static/header.jsp"/>
<head>
    <style>
        <%@ include file="../assets/css/stars.css" %>
    </style>
</head>
<body>
<jsp:directive.include file="../static/navbar.jsp"/>

<c:set var="movie" value="${pageContext.request.getAttribute('movie')}"/>

<form method="post" action="${pageContext.request.contextPath}/app/movie">

    <div class="mb-3" hidden>
        <label for="title_input" class="form-label" hidden>Title</label>
        <input type="text" name="movie_id" class="form-control" id="id" value="${movie.id}" hidden>
    </div>
    <div class="mb-3">
        <label for="title_input" class="form-label">Title</label>
        <input type="text" name="title" class="form-control" id="title_input" value="${movie.title}">
    </div>
    <div class="mb-3">
        <label for="short_description_input" class="form-label">Short description</label>
        <input type="text" name="short_description" class="form-control" id="short_description_input" value="${movie.shortDescription}">
    </div>
    <div class="mb-3">
        <label for="description_input" class="form-label">Description</label>
        <input type="text" name="description" class="form-control" id="description_input" value="${movie.description}">
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
<jsp:directive.include file="../static/footer.jsp"/>
</html>
