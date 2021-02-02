<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<c:set var="movie" value="${pageContext.request.getAttribute('movie')}"/>

<div class="card mb-3 bg-dark">
    <div class="row g-0">
        <div class="col-md-4">
            <img onloadstart="setActive(${movie.id}, ${movie.rates[userDTO.id]})" height="300px" src="${movie.imageLink}" alt="${movie.title}">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${movie.title}"/></h5>
                <br/>
                <p class="card-title"><c:out value="${movie.shortDescription}"/></p>
                <br/>
                <p class="card-text"><fmt:message key="msg.director"/>: <c:out value="${movie.directedBy}"/></p>
                <br/>
                <p class="card-text"><fmt:message key="msg.release-date"/>: <c:out value="${movie.releaseDate}"/></p>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty movie.rates[userDTO.id]}">
    <span><fmt:message key="msg.your-rate"/>: <c:out value="${movie.rates[userDTO.id]}"/></span>
    <button onclick="removeRate(${movie.id})" type="button" class="btn btn-dark"><fmt:message key="msg.remove-rate"/></button>
</c:if>

<div class="star-rating" id="${movie.id}" style="direction: rtl">
    <span class="10" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 10)" onclick="sendRate(${movie.id}, 10)"><i class="fa fa-star"></i></span>
    <span class="9" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 9)" onclick="sendRate(${movie.id}, 9)"><i class="fa fa-star"></i></span>
    <span class="8" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 8)" onclick="sendRate(${movie.id}, 8)"><i class="fa fa-star"></i></span>
    <span class="7" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 7)" onclick="sendRate(${movie.id}, 7)"><i class="fa fa-star"></i></span>
    <span class="6" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 6)" onclick="sendRate(${movie.id}, 6)"><i class="fa fa-star"></i></span>
    <span class="5" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 5)" onclick="sendRate(${movie.id}, 5)"><i class="fa fa-star"></i></span>
    <span class="4" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 4)" onclick="sendRate(${movie.id}, 4)"><i class="fa fa-star"></i></span>
    <span class="3" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 3)" onclick="sendRate(${movie.id}, 3)"><i class="fa fa-star"></i></span>
    <span class="2" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 2)" onclick="sendRate(${movie.id}, 2)"><i class="fa fa-star"></i></span>
    <span class="1" onmouseout="setActive(${movie.id}, ${movie.rates[userDTO.id]})" onmouseover="setActive(${movie.id}, 1)" onclick="sendRate(${movie.id}, 1)"><i class="fa fa-star"></i></span>
</div>
<div>
    <p><c:out value="${movie.description}"/></p>
</div>
</body>
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

<jsp:directive.include file="../static/footer.jsp"/>
</html>
