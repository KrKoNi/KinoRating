<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="local" prefix="jwd"%>

<html>

<head>
    <jsp:directive.include file="../static/header.jsp"/>
    <script>
        function setActive(showId, rate) {
            let div = document.getElementById(showId);
            let spans = div.getElementsByTagName('span');
            for (let span of spans) {
                if(parseInt(span.className) <= rate) {
                    span.style.color = 'gold';
                } else {
                    span.style.color = '';
                }
            }
        }
    </script>
</head>

<body>
<jsp:directive.include file="../static/navbar.jsp"/>

<c:set var="tv_series" value="${pageContext.request.getAttribute('tv_series')}"/>
<div class="row row-cols-1 row-cols-md-4">
    <c:forEach items="${tv_series}" var="tv">
        <jwd:card show="${tv}"/>
    </c:forEach>
</div>
<jsp:directive.include file="../static/pagination.jsp"/>
</body>
<jsp:directive.include file="../static/footer.jsp"/>
</html>

<script>

    async function removeRate(showId) {
        let url = "http://localhost:8080/ajax/rate?id=" + showId;
        try {
            await fetch(url, {method: 'POST'});
        } catch (e) {
            alert("Unable to connect to server");
        }

        location.reload();
    }

    async function sendRate(showId, rate) {
        let url = "http://localhost:8080/ajax/rate?id=" + showId + "&rate=" + rate;
        try {
            await fetch(url, {method: 'POST'});
        } catch (e) {
            alert("Unable to connect to server");
        }

        location.reload();
    }
</script>
