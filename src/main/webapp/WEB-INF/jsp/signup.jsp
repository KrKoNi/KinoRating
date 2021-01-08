<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:directive.include file="../static/header.jsp" />
<head>
    <style><%@include file="../assets/css/login.css"%></style>
</head>
<body>
<jsp:directive.include file="../static/navbar.jsp" />
<div class="login-dark">
    <form method="post" action="${pageContext.request.contextPath}?command=signup-post">
        <h2 class="sr-only">Registration Form</h2>

        <div class="form-group">
            <input
                    class="form-control" type="text" name="login"
                    placeholder="Login" required minlength="4">
        </div>
        <div class="form-group">
            <input
                    class="form-control" type="email" name="email"
                    placeholder="E-mail" required minlength="4">
        </div>
        <div class="form-group">
            <input class="form-control" type="password" name="password"
                   placeholder="Password" required minlength="8">
        </div>
        <div class="form-group">
            <input
                    class="form-control" type="text" name="first_name"
                    placeholder="First name">
        </div>
        <div class="form-group">
            <input
                    class="form-control" type="text" name="last_name"
                    placeholder="Last name">
        </div>
        <div class="form-group">
            <input
                    class="form-control" type="date" name="birth_date">
        </div>

        <div class="form-group">
            <button class="btn btn-primary btn-block" type="submit">Sign up</button>
        </div>
    </form>
</div>

<jsp:directive.include file="../static/footer.jsp" />

</body>
</html>
