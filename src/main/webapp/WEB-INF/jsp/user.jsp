<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="user" value="${pageContext.request.getAttribute('user')}"/>
<c:set var="shows" value="${pageContext.request.getAttribute('shows')}"/>
<html>
    <head>
        <jsp:directive.include file="../static/header.jsp"/>
    </head>
    <body>
        <jsp:directive.include file="../static/navbar.jsp"/>

        <div>
            First name: <span><c:out value="${user.firstName}"/></span>
            <br/>
            Last name: <span><c:out value="${user.lastName}"/></span>
            <br/>
            Birth date: <span><c:out value="${user.birthDate}"/></span>
            <br/>
            Login: <span><c:out value="${user.login}"/></span>
            <br/>
            Email: <span><c:out value="${user.email}"/></span>
            <br/>
            Registration date: <span><c:out value="${user.registrationDate}"/></span>
            <br/>
            <a role="button" href="${pageContext.request.contextPath}/app/edit_user">Edit</a>
        </div>
        <table class="table table-dark table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Short description</th>
                <th scope="col">Rate</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${shows}" var="show">
                <tr>
                    <td>
                        <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                            <c:out value="${show.title}"/>
                        </p>
                    </td>
                    <td>
                        <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                            <c:out value="${show.shortDescription}"/>
                        </p>
                    </td>
                    <td>
                        <p style="max-width: 20rem; white-space:nowrap; overflow: hidden; text-overflow: ellipsis">
                            <c:out value="${show.rates[userDTO.id]}"/>
                        </p>
                    </td>
                    <td>
                        <button onclick="removeRate(${show.id})" type="button" class="btn btn-dark"><i class="fa fa-trash"></i></button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </body>
    <jsp:directive.include file="../static/footer.jsp"/>
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
    </script>
</html>

