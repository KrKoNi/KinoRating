<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="genres" value="${pageContext.request.getAttribute('genres')}"/>

<html>
<head>
    <jsp:directive.include file="../../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="navbar.jsp"/>

<form method="post">

    <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input type="text" class="form-control" id="title" name="title">
    </div>

    <div class="mb-3">
        <label for="image_link" class="form-label">Image Link</label>
        <input type="url" class="form-control" id="image_link" name="image_link">
    </div>

    <div class="mb-3">
        <label for="short_description" class="form-label">Short Description</label>
        <input type="text" class="form-control" id="short_description" name="short_description">
    </div>

    <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <input type="text" class="form-control" id="description" name="description">
    </div>

    <div class="mb-3">
        <label for="release_date" class="form-label">Release Date</label>
        <input type="date" class="form-control" id="release_date" name="release_date">
    </div>

    <div class="mb-3">
        <label for="directed_by" class="form-label">Director</label>
        <input type="text" class="form-control" id="directed_by" name="directed_by">
    </div>

    <div class="mb-3">
        <label for="duration" class="form-label">Duration</label>
        <input type="time" class="form-control" id="duration" name="duration">
    </div>

    <c:forEach items="${genres}" var="genre">
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="genres" value="${genre.id}" id="check${genre.id}">
            <label class="form-check-label" for="check${genre.id}">
                <c:out value="${genre.name}"/>
            </label>
        </div>
    </c:forEach>

    <button type="submit" class="btn btn-dark">Create</button>

</form>
<jsp:directive.include file="../../static/footer.jsp"/>
</html>
