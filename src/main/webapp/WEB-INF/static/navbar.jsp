<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty cookie['lang'] ? cookie['lang'].value : not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" scope="session"/>

<c:set var="userDTO" value="${pageContext.session.getAttribute('userDTO')}"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/app/">KinoRating</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav" aria-controls="nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="nav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/movies">
                        <fmt:message key="msg.movies"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/tv-series">
                        <fmt:message key="msg.tv-series"/>
                    </a>
                </li>
                <c:if test="${not empty userDTO}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/user">
                            <fmt:message key="msg.profile"/>
                        </a>
                    </li>
                </c:if>
            </ul>


            <form class="d-flex justify-content-end">
                <input class="form-control ms-2" type="search" placeholder="<fmt:message key="msg.search"/>" aria-label="Search" id="search" onkeyup="searchShow(document.getElementById('search').value)">
                <button class="btn btn-dark" type="submit"><i class="fa fa-search"></i></button>
            </form>
            <c:choose>
                <c:when test="${empty userDTO}">
                    <a class="nav-link btn btn-dark" role="button" href="${pageContext.request.contextPath}/app/login">
                        <fmt:message key="msg.login"/>
                    </a>
                    <a class="nav-link btn btn-dark" role="button" href="${pageContext.request.contextPath}/app/signup">
                        <fmt:message key="msg.signup"/>
                    </a>
                </c:when>
                <c:otherwise>

                    <form method="post" action="${pageContext.request.contextPath}/app/logout">
                        <button class="btn btn-dark action-button" type="submit">
                            <fmt:message key="msg.logout"/>
                        </button>
                    </form>
                </c:otherwise>
            </c:choose>
            <span class="btn btn-dark" onclick="changeLang('en')" type="button">EN</span>
            <span class="btn btn-dark" onclick="changeLang('ru')" type="button">RU</span>
        </div>
    </div>
</nav>

<div hidden id="search_result" style="position: absolute; z-index: 100; width: 30%; margin: auto; border-radius: 4px; height: 300px; overflow-y: auto;">

</div>

<script>
    function changeLang(lang) {
        try {
            document.cookie = "lang="+lang;
            location.reload();
        } catch (e) {
            alert("Unable to change lang");
        }
    }

    function searchShow(strParam) {
        if(strParam.length < 3) {
            document.getElementById("search_result").innerHTML = "";
            document.getElementById("search_result").hidden = true;
            return;
        }
        let url = "http://localhost:8080/ajax/search?str=" + strParam;

        let req = new XMLHttpRequest();

        try {
            let str = "<table class='table table-dark table-hover'><tbody>";
            fetch(url)
                .then(function(response) {
                    return response.json();
                })
                .then(function(jsonResponse) {
                    const arr = Object.values(jsonResponse);
                    arr.forEach(show => {
                        link = '/app/show?id=' + show.id;
                        str = str +
                            "<tr>" +
                            "<th scope='row'><a href=" + link + "><img height='150px' src=" + show.imageLink + " class='card-img-top' alt=" + show.title + "></th></a>"+
                            "<td>" +
                                "<h2>" + show.title + "</h2>" +
                                "<br/>" +
                                "<p>" + show.shortDescription + "</p>" +
                            "</td>" +
                        "</tr>"+
                            "</a>"
                    });
                    str = str + "</tbody></table>"
                    document.getElementById("search_result").innerHTML = str;
                    document.getElementById("search_result").hidden = false;
                });


        } catch(e) { alert("Unable to connect to server"); }
    }

</script>