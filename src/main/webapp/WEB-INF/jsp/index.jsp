<%@ page import="com.epam.jwd.dto.impl.UserDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:directive.include file="../static/header.jsp" />

<body>
    <jsp:directive.include file="../static/navbar.jsp" />
    <h1>Main</h1>

    <c:set var="user" value="${pageContext.session.getAttribute('user')}"/>
    <%
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
    %>

    <h1>
        <c:if test="${user != null}">
            <c:out value="${user.id}"/>
            <c:out value="${user.role}"/>
        </c:if>
    </h1>

</body>

<jsp:directive.include file="../static/footer.jsp" />

</html>