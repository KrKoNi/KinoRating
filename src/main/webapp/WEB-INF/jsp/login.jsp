<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:directive.include file="../static/header.jsp" />
    <style><%@include file="../assets/css/login.css"%></style>
</head>
<body>
    <jsp:directive.include file="../static/navbar.jsp" />

    <c:set var="messages" value="${pageContext.request.getAttribute('messages')}"/>
    <c:forEach items="${messages}" var="message">
        <div class="toast text-white bg-dark" role="alert" aria-live="assertive" aria-atomic="true" style="position: absolute; z-index: 100">
            <div class="toast-header">
                <strong class="me-auto">ERROR</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                <c:out value="${message.value}"/>
            </div>
        </div>
    </c:forEach>
    <div class="login-dark">
        <form method="post">
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

<script>
    var toastElList = [].slice.call(document.querySelectorAll('.toast'))
    var toastList = toastElList.map(function (toastEl) {
        return new bootstrap.Toast(toastEl, "")
    })

    toastList.forEach(toast => toast.show());

    $(document).ready( function() {
        $('.toast').toast('show');
    });
</script>
</html>
