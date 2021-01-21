<%@ page import="com.epam.jwd.dto.impl.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<% UserDTO user = (UserDTO) session.getAttribute("user"); %>

<c:set var="user" value="${pageContext.session.getAttribute('user')}"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}?command=home">KinoRating</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=movies">Movies</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=tv">TV-Series</a>
                </li>
                <c:choose>
                    <c:when test="${user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}?command=login">Log in</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}?command=signup">Sign up</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}?command=logout">Log out</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>

            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="search" onkeyup="searchShow(document.getElementById('search').value)">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>


        </div>
    </div>
</nav>

<div hidden id="search_result" style="position: absolute; z-index: 100; width: 40%; margin: auto; border-radius: 4px; height: 300px; overflow-y: auto;">

</div>

<script>
    function searchShow(strParam) {
        if(strParam.length < 3) {
            document.getElementById("search_result").innerHTML = "";
            document.getElementById("search_result").hidden = true;
            return;
        }
        let url = "http://localhost:8080/ajax?command=search&str=" + strParam;

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
                            "<th scope='row'><img height='150px' src=" + show.imageLink + " class='card-img-top' alt=" + show.title + "></th>"+
                            "<td>" +
                                "<h2>" + show.title + "</h2>" +
                                "<br/>" +
                                "<p>" + show.shortDescription + "</p>" +
                            "</td>" +
                        "</tr>"
                    });
                    str = str + "</tbody></table>"
                    document.getElementById("search_result").innerHTML = str;
                    document.getElementById("search_result").hidden = false;
                });


        } catch(e) { alert("Unable to connect to server"); }
    }

</script>