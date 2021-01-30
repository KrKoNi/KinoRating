<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:directive.include file="../../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="../../static/navbar.jsp"/>
<c:set var="movie" value="${pageContext.request.getAttribute('movie')}"/>

<form method="post" action="${pageContext.request.contextPath}/admin/update_movie">

    <input hidden id="id" name="id" value="${movie.id}">

    <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input type="text" class="form-control" id="title" name="title" value="${movie.title}">
    </div>

    <div class="mb-3">
        <label for="image_link" class="form-label">Image Link</label>
        <input type="url" class="form-control" id="image_link" name="image_link" value="${movie.imageLink}">
    </div>

    <div class="mb-3">
        <label for="short_description" class="form-label">Short Description</label>
        <input type="text" class="form-control" id="short_description" name="short_description" value="${movie.shortDescription}">
    </div>

    <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <input type="text" class="form-control" id="description" name="description" value="${movie.description}">
    </div>

    <div class="mb-3">
        <label for="release_date" class="form-label">Release Date</label>
        <input type="date" class="form-control" id="release_date" name="release_date" value="${movie.releaseDate}">
    </div>

    <div class="mb-3">
        <label for="directed_by" class="form-label">Director</label>
        <input type="text" class="form-control" id="directed_by" name="directed_by" value="${movie.directedBy}">
    </div>

    <div class="mb-3">
        <label for="duration" class="form-label">Duration</label>
        <input type="time" class="form-control" id="duration" name="duration" value="${movie.duration}">
    </div>

    <button type="submit" class="btn btn-dark">Update</button>

</form>
<jsp:directive.include file="../../static/footer.jsp"/>
</html>
