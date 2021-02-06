<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="user" value="${pageContext.request.getAttribute('user')}"/>

<html>
<head>
    <jsp:directive.include file="../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="../static/navbar.jsp"/>

<form method="post" action="${pageContext.request.contextPath}/app/update_user">
    <input hidden name="id" id="id" value="${user.id}">

    <div class="mb-3">
        <label for="login" class="form-label">Login</label>
        <input type="text" class="form-control" id="login" name="login" value="${user.login}">
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input type="email" class="form-control" id="email" name="email" value="${user.email}">
    </div>
    <div class="mb-3">
        <label for="first_name" class="form-label">First name</label>
        <input type="text" class="form-control" id="first_name" name="first_name" value="${user.firstName}">
    </div>
    <div class="mb-3">
        <label for="last_name" class="form-label">Last name</label>
        <input type="text" class="form-control" id="last_name" name="last_name" value="${user.lastName}">
    </div>
    <div class="mb-3">
        <label for="birth_date" class="form-label">Birth date</label>
        <input type="date" class="form-control" id="birth_date" name="birth_date" value="${user.birthDate}">
    </div>


    <button type="submit" class="btn btn-dark">Update</button>
</form>

<jsp:directive.include file="../static/footer.jsp"/>

</html>

