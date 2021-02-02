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
<c:set var="tv" value="${pageContext.request.getAttribute('tv')}"/>

<div class="card mb-3 bg-dark">
    <div class="row g-0">
        <div class="col-md-4">
            <img onloadstart="setActive(${tv.id}, ${tv.rates[userDTO.id]})" height="300px" src="${tv.imageLink}" alt="${tv.title}">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${tv.title}"/></h5>
                <br/>
                <p class="card-title"><c:out value="${tv.shortDescription}"/></p>
            </div>
        </div>
    </div>
</div>
<div class="star-rating" id="${tv.id}" style="direction: rtl">
    <span class="10" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 10)" onclick="sendRate(${tv.id}, 10)"><i class="fa fa-star"></i></span>
    <span class="9" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 9)" onclick="sendRate(${tv.id}, 9)"><i class="fa fa-star"></i></span>
    <span class="8" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 8)" onclick="sendRate(${tv.id}, 8)"><i class="fa fa-star"></i></span>
    <span class="7" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 7)" onclick="sendRate(${tv.id}, 7)"><i class="fa fa-star"></i></span>
    <span class="6" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 6)" onclick="sendRate(${tv.id}, 6)"><i class="fa fa-star"></i></span>
    <span class="5" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 5)" onclick="sendRate(${tv.id}, 5)"><i class="fa fa-star"></i></span>
    <span class="4" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 4)" onclick="sendRate(${tv.id}, 4)"><i class="fa fa-star"></i></span>
    <span class="3" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 3)" onclick="sendRate(${tv.id}, 3)"><i class="fa fa-star"></i></span>
    <span class="2" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 2)" onclick="sendRate(${tv.id}, 2)"><i class="fa fa-star"></i></span>
    <span class="1" onmouseout="setActive(${tv.id}, ${tv.rates[userDTO.id]})" onmouseover="setActive(${tv.id}, 1)" onclick="sendRate(${tv.id}, 1)"><i class="fa fa-star"></i></span>
</div>

<c:if test="${not empty tv.rates[userDTO.id]}">
    <span><fmt:message key="msg.your-rate"/>: <c:out value="${tv.rates[userDTO.id]}"/></span>
    <button onclick="removeRate(${tv.id})" type="button" class="btn btn-dark"><fmt:message key="msg.remove-rate"/></button>
</c:if>

<div>
    <p><c:out value="${tv.description}"/></p>
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
