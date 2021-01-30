<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="${language}">
<head>
    <jsp:directive.include file="../static/header.jsp"/>
</head>

<body>

<jsp:directive.include file="../static/navbar.jsp"/>

<script>
    function sendInfo(commandParam) {
        let url = "http://localhost:8080/ajax/" + commandParam;
        try {
            let str = "<table class='table table-dark table-hover'><tbody>";
            fetch(url)
                .then(function(response) {
                    return response.json();
                })
                .then(function(jsonResponse) {
                    const arr = Object.values(jsonResponse);
                    let str2 = "";
                    arr.forEach(show => {
                        show.genres.forEach(genre => {str2 = str2 + genre.name + ", "});
                        str = str +
                            "<tr>" +
                            "<th scope='row'><img height='300px' width='80px' src=" + show.imageLink + " class='card-img-top' alt=" + show.title + "></th>"+
                            "<td>" +
                            "<h2>" + show.title + "</h2>" +
                            "<br/>" +
                            "<p>" + show.shortDescription + "</p>" +
                            "<p>" + str2.slice(0, -2) + "</p>" +
                            "</td>" +
                            "</tr>";
                        str2 = "";
                    });
                    str = str + "</tbody></table>"
                    document.getElementById("result").innerHTML = str;
                });
        } catch(e) { alert("Unable to connect to server"); }
    }

</script>
</body>
<jsp:directive.include file="../static/footer.jsp"/>
</html>
