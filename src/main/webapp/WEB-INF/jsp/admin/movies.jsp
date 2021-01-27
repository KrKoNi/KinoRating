<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <jsp:directive.include file="../../static/header.jsp"/>
</head>

<body>
<jsp:directive.include file="../../static/navbar.jsp"/>

<c:set var="user" value="${pageContext.session.getAttribute('user')}"/>
<c:set var="movies" value="${pageContext.request.getAttribute('movies')}"/>

<table class="table table-dark table-striped table-hover">
    <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Title</th>
            <th scope="col">Short description</th>
            <th scope="col">Description</th>
            <th scope="col">Image link</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${movies}" var="movie">
            <tr>
                <th scope="row"><c:out value="${movie.id}"/></th>
                <td style="white-space:nowrap; overflow: hidden; text-overflow: ellipsis"><c:out value="${movie.title}"/></td>
                <td style="white-space:nowrap; overflow: hidden; text-overflow: ellipsis"><c:out value="${movie.shortDescription}"/></td>
                <td style="white-space:nowrap; overflow: hidden; text-overflow: ellipsis"><c:out value="${movie.description}"/></td>
                <td style="white-space:nowrap; overflow: hidden; text-overflow: ellipsis"><input type="url" value="${movie.imageLink}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:directive.include file="../../static/pagination.jsp"/>
</body>
<jsp:directive.include file="../../static/footer.jsp"/>
</html>

