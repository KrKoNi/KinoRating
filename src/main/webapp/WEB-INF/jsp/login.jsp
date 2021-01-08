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
        <form method="post" action="${pageContext.request.contextPath}?command=login-post">
            <h2 class="sr-only">Login Form</h2>

            <div class="form-group">
                <input
                        class="form-control" type="text" name="login"
                        placeholder="Login" required minlength="4">
            </div>
            <div class="form-group">
                <input class="form-control" type="password" name="password"
                       placeholder="Password" required minlength="8">
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-block" type="submit">Log In</button>
            </div>
        </form>
    </div>

    <jsp:directive.include file="../static/footer.jsp" />

</body>
</html>
