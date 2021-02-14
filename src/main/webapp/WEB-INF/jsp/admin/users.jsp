<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:directive.include file="../../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="navbar.jsp"/>
<c:set var="users" value="${pageContext.request.getAttribute('users')}"/>
<table class="table table-dark table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Login</th>
        <th scope="col">E-mail</th>
        <th scope="col">Birth date</th>
        <th scope="col">Make admin/user</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <th scope="row"><c:out value="${user.id}"/></th>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${user.login}"/>
                </p>
            </td>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${user.email}"/>
                </p>
            </td>
            <td>
                <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                    <c:out value="${user.birthDate}"/>
                </p>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/admin/change_role">
                    <input hidden name="id" value="${user.id}">
                    <c:choose>
                        <c:when test="${user.role.id == 1}">
                            <button class="btn btn-dark"><i class="fa fa-arrow-circle-up"></i></button>
                        </c:when>
                        <c:when test="${user.role.id == 2}">
                            <button class="btn btn-dark"><i class="fa fa-arrow-circle-down"></i></button>
                        </c:when>
                    </c:choose>
                </form>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/admin/remove_user">
                    <input hidden name="id" value="${user.id}">
                    <button class="btn btn-dark"><i class="fa fa-trash"></i></button>
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

