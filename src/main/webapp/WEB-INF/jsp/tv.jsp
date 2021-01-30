<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:directive.include file="../static/header.jsp"/>
</head>
<body>
<jsp:directive.include file="../static/navbar.jsp"/>
<c:set var="user" value="${pageContext.session.getAttribute('user')}"/>
<c:set var="tv" value="${pageContext.request.getAttribute('tv')}"/>

<div class="card mb-3 bg-dark">
    <div class="row g-0">
        <div class="col-md-4">
            <img height="300px" src="${tv.imageLink}" alt="${tv.title}">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${tv.title}"/></h5>
                <br/>
                <p class="card-text"><c:out value="${tv.shortDescription}"/></p>
            </div>
        </div>
        <div class="card-footer">
            <jsp:directive.include file="../static/stars.jsp"/>
        </div>
    </div>
</div>

<div>
    <p><c:out value="${tv.description}"/></p>
</div>

</body>
<jsp:directive.include file="../static/footer.jsp"/>
</html>
