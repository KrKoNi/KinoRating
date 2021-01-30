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

    <c:set var="movies" value="${pageContext.request.getAttribute('movies')}"/>
    <div class="row row-cols-1 row-cols-md-4">
    <c:forEach items="${movies}" var="movie">
        <div class="col mb-4">
            <div class="card bg-dark">
                <a href="${pageContext.request.contextPath}/app/movie?id=${movie.id}">
                    <img onloadstart="setActive(${movie.id}, ${movie.rates[userDTO.id]})" height="300px" src="${movie.imageLink}" class="card-img-top" alt="${movie.title}">
                </a>
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${movie.title}"/></h5>
                    <p class="card-text"><c:out value="${movie.shortDescription}"/></p>

                    <c:if test="${not empty movie.rates[userDTO.id]}">
                        <span>Your rating: <c:out value="${movie.rates[userDTO.id]}"/></span>
                        <button onclick="removeRate(${movie.id})" type="button" class="btn btn-dark">Remove rate</button>
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
                    <span hidden class="answer" id="${movie.id}"></span>

                </div>
            </div>
        </div>
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
