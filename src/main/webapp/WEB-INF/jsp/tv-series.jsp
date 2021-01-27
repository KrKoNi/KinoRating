<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <jsp:directive.include file="../static/header.jsp"/>
    <style type="text/css">
        <%@ include file="../assets/css/stars.css" %>
    </style>
</head>

<body>
    <jsp:directive.include file="../static/navbar.jsp"/>

    <c:set var="user" value="${pageContext.session.getAttribute('user')}"/>
    <c:set var="tvseries" value="${pageContext.request.getAttribute('tvseries')}"/>
    <div class="row row-cols-1 row-cols-md-4">
        <c:forEach items="${tvseries}" var="tv">
            <div class="col mb-4">
                <div class="card bg-dark">
                    <a href="${pageContext.request.contextPath}/app/tv?id=${tv.id}">
                        <img height="300px" src="${tv.imageLink}" class="card-img-top" alt="${tv.title}">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${tv.title}"/></h5>
                        <p class="card-text"><c:out value="${tv.shortDescription}"/></p>
                        <c:set var="user_rating" value="${tv.rates.get(user.id)}"/>
                        <jsp:directive.include file="../static/stars.jsp"/>
                    </div>
                    <c:if test="${user != null && user.role == Role.ADMIN}">
                        <div class="card-footer">
                            <form method="post" action="?command=movie_edit">
                                <input hidden name="movie_id" value="${tv.id}"/>
                                <button type="submit" class="btn btn-secondary btn-sm">Edit</button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>

    <jsp:directive.include file="../static/pagination.jsp"/>
</body>

<jsp:directive.include file="../static/footer.jsp"/>
</html>
