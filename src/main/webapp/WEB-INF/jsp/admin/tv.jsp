<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:directive.include file="../../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="../../static/navbar.jsp"/>
<c:set var="tv" value="${pageContext.request.getAttribute('tv')}"/>

<form method="post" action="${pageContext.request.contextPath}/admin/update_tv">

    <input hidden id="id" name="id" value="${tv.id}">

    <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input type="text" class="form-control" id="title" name="title" value="${tv.title}">
    </div>

    <div class="mb-3">
        <label for="image_link" class="form-label">Image Link</label>
        <input type="url" class="form-control" id="image_link" name="image_link" value="${tv.imageLink}">
    </div>

    <div class="mb-3">
        <label for="short_description" class="form-label">Short Description</label>
        <input type="text" class="form-control" id="short_description" name="short_description" value="${tv.shortDescription}">
    </div>

    <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <input type="text" class="form-control" id="description" name="description" value="${tv.description}">
    </div>

    <button type="submit" class="btn btn-dark">Update</button>

</form>
<jsp:directive.include file="../../static/footer.jsp"/>
</html>
