<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:directive.include file="../../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="navbar.jsp"/>
<c:set var="movies" value="${pageContext.request.getAttribute('movies')}"/>

<a class="btn btn-dark" href="${pageContext.request.contextPath}/admin/create_movie">Create movie</a>

<table class="table table-dark table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Title</th>
        <th scope="col">Short description</th>
        <th scope="col">Description</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${movies}" var="movie">
        <tr>
            <th scope="row"><c:out value="${movie.id}"/></th>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${movie.title}"/>
                </p>
            </td>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${movie.shortDescription}"/>
                </p>
            </td>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${movie.description}"/>
                </p>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/movie?id=${movie.id}">
                    <i class="fa fa-edit"></i>
                </a>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/admin/delete_show">
                    <input hidden name="id" value="${movie.id}">
                    <button class="btn-dark btn" type="submit"><i class="fa fa-trash"></i></button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:directive.include file="../../static/pagination.jsp"/>
</body>
<jsp:directive.include file="../../static/footer.jsp"/>
</html>

