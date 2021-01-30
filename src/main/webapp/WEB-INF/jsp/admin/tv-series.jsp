<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:directive.include file="../../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="navbar.jsp"/>
<c:set var="tv_series" value="${pageContext.request.getAttribute('tv_series')}"/>
<table class="table table-dark table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Title</th>
        <th scope="col">Short description</th>
        <th scope="col">Description</th>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tv_series}" var="tv">
        <tr>
            <th scope="row"><c:out value="${tv.id}"/></th>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${tv.title}"/>
                </p>
            </td>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${tv.shortDescription}"/>
                </p>
            </td>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${tv.description}"/>
                </p>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/tv?id=${tv.id}">
                    <i class="fa fa-edit"></i>
                </a>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/admin/delete_show">
                    <input hidden name="id" value="${tv.id}">
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

