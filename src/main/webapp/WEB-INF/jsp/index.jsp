<%@ page import="com.epam.jwd.dto.impl.UserDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:directive.include file="../static/header.jsp" />

<body>
    <jsp:directive.include file="../static/navbar.jsp" />
    <h1>Main</h1>

    <%
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
    %>

    <h1><%= userDTO != null ? userDTO.getId() + " " + userDTO.getRole().toString() : ""%></h1>
</body>

<jsp:directive.include file="../static/footer.jsp" />

</html>