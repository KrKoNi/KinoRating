<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

    <button type="submit" class="btn btn-dark">Create</button>

</form>
<jsp:directive.include file="../../static/footer.jsp"/>
</html>
