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
    <c:set var="movies" value="${pageContext.request.getAttribute('movies')}"/>
    <c:set var="sortBy" value="${pageContext.request.getParameter('sort')}"/>
    <c:set var="orderBy" value="${pageContext.request.getParameter('order')}"/>
    <div>
        <form>
            <label for="sort">Sort by:</label>
            <select class="form-select-sm" name="sort" id="sort">
                <option value="release_date" ${sortBy.equals("release_date") ? "selected" : ""}>Release date</option>
                <option value="title" ${sortBy.equals("title") ? "selected" : ""}>Title</option>
                <option value="rate" ${sortBy.equals("rate") ? "selected" : ""}>Rate</option>
            </select>
            <select class="form-select-sm" name="order">
                <option value="desc" ${orderBy.equals("desc") ? "selected" : ""}>Descending</option>
                <option value="asc" ${orderBy.equals("asc") ? "selected" : ""}>Ascending</option>
            </select>
            <button class="btn btn-dark" type="submit">Sort</button>
        </form>
    </div>
    <div class="row row-cols-1 row-cols-md-4">
        <c:forEach items="${movies}" var="movie">
            <jwd:card show="${movie}"/>
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
