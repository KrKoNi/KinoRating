<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="user" value="${pageContext.request.getAttribute('user')}"/>

<html>
    <head>
        <jsp:directive.include file="../static/header.jsp"/>
    </head>
    <body>
        <jsp:directive.include file="../static/navbar.jsp"/>

        <div>
            First name: <span><c:out value="${user.firstName}"/></span>
            Last name: <span><c:out value="${user.lastName}"/></span>
            Birth date: <span><c:out value="${user.birthDate}"/></span>
            Login: <span><c:out value="${user.login}"/></span>
            Email: <span><c:out value="${user.email}"/></span>
            Registration date: <span><c:out value="${user.registrationDate}"/></span>
        </div>

    </body>
    <jsp:directive.include file="../static/footer.jsp"/>
</html>
