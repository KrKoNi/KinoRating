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
    <form method="post" action="${pageContext.request.contextPath}/app/signup">
        <div class="form-group">
            <label for="login"><fmt:message key="msg.login-name"/></label>
            <input
                    id="login" class="form-control" type="text" name="login"
                    placeholder="Login" required minlength="4">
        </div>
        <div class="form-group">
            <label for="email">E-mail</label>
            <input
                    id="email" class="form-control" type="email" name="email"
                    placeholder="E-mail" required minlength="4">
        </div>
        <div class="form-group">
            <label for="password"><fmt:message key="msg.password"/></label>
            <input id="password" class="form-control" type="password" name="password"
                   placeholder="Password" required minlength="8">
        </div>

        <div class="form-group">
            <button class="btn btn-primary btn-block" type="submit"><fmt:message key="msg.signup"/></button>
        </div>
    </form>
</div>

<jsp:directive.include file="../static/footer.jsp" />

</body>
</html>
