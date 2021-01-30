<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <jsp:directive.include file="../static/header.jsp"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

<c:set var="tvSeries" value="${pageContext.request.getAttribute('tv_series')}"/>
<div class="row row-cols-1 row-cols-md-4">
    <c:forEach items="${tvSeries}" var="tv">
        <div class="col mb-4">
            <div class="card bg-dark">
                <a href="${pageContext.request.contextPath}/app/movie?id=${tv.id}">
                    <img onloadstart="setActive(${tv.id}, ${tv.rates[userDTO.id]})" height="300px" src="${tv.imageLink}" class="card-img-top" alt="${tv.title}">
                </a>
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${tv.title}"/></h5>
                    <p class="card-text"><c:out value="${tv.shortDescription}"/></p>
                    <c:if test="${not empty tv.rates[userDTO.id]}">
                        <span>Your rating: <c:out value="${tv.rates[userDTO.id]}"/></span>
                    </c:if>
                    <div class="star-rating" id="${tv.id}" style="direction: rtl">
                        <span class="10" onclick="sendRate(${tv.id}, 10)"><i class="fa fa-star"></i></span>
                        <span class="9" onclick="sendRate(${tv.id}, 9)"><i class="fa fa-star"></i></span>
                        <span class="8" onclick="sendRate(${tv.id}, 8)"><i class="fa fa-star"></i></span>
                        <span class="7" onclick="sendRate(${tv.id}, 7)"><i class="fa fa-star"></i></span>
                        <span class="6" onclick="sendRate(${tv.id}, 6)"><i class="fa fa-star"></i></span>
                        <span class="5" onclick="sendRate(${tv.id}, 5)"><i class="fa fa-star"></i></span>
                        <span class="4" onclick="sendRate(${tv.id}, 4)"><i class="fa fa-star"></i></span>
                        <span class="3" onclick="sendRate(${tv.id}, 3)"><i class="fa fa-star"></i></span>
                        <span class="2" onclick="sendRate(${tv.id}, 2)"><i class="fa fa-star"></i></span>
                        <span class="1" onclick="sendRate(${tv.id}, 1)"><i class="fa fa-star"></i></span>
                    </div>

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
