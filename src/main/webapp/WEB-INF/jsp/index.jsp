<%@ page import="com.epam.jwd.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>



<c:set var="user" value="${pageContext.session.getAttribute('user')}"/>

<head>
    <jsp:directive.include file="../static/header.jsp"/>
    <style type="text/css">
        <%@ include file="../assets/css/stars.css" %>
    </style>
</head>

<body>
<jsp:directive.include file="../static/navbar.jsp"/>

<button class="block_check btn btn-primary" id="movies_check" onclick="sendInfo('movies')">
    Movies
</button>

<button class="block_check btn btn-primary" id="tv_check" onclick="sendInfo('tv')">
    Tv
</button>


<div id="main_block">
    <div id="result">

    </div>
</div>

<script>
    function sendInfo(commandParam) {
        let url = "http://localhost:8080/ajax?command=" + commandParam;

        let req = new XMLHttpRequest();

        try {
            let str = "<table class='table table-dark table-hover'><tbody>";
            fetch(url)
                .then(function(response) {
                    return response.json();
                })
                .then(function(jsonResponse) {
                    const arr = Object.values(jsonResponse);
                    arr.forEach(show => {str = str +
                        "<tr>" +
                            "<th scope='row'><img height='300px' src=" + show.imageLink + " class='card-img-top' alt=" + show.title + "></th>"+
                        "<td>" +
                            "<h2>" + show.title + "</h2>" +
                            "<br/>" +
                            "<p>" + show.shortDescription + "</p>" +
                        "</td>" +
                        "</tr>"
                    });
                    str = str + "</tbody></table>"
                    document.getElementById("result").innerHTML = str;
                });


        } catch(e) { alert("Unable to connect to server"); }
    }

</script>



<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>

<jsp:directive.include file="../static/footer.jsp"/>
</html>
