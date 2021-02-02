<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="Page navigation">
    <ul class="pagination justify-content-end">
        <c:set var="max_page" value="${pageContext.request.getAttribute('max_page')}"/>
        <c:choose>
            <c:when test="${pageContext.request.getParameter('page') != null}">
               <c:set var="page" value="${pageContext.request.getParameter('page')}"/>
            </c:when>
            <c:otherwise>
                <c:set var="page" value="1"/>
            </c:otherwise>
        </c:choose>
        <c:choose>

            <c:when test="${max_page == 1}">
                <li class="page-item active">
                    <a class="page-link btn-dark" href="${pageContext.request.contextPath}?page=1">
                        1
                    </a>
                </li>
            </c:when>
            <c:when test="${max_page == 2}">
                <li class="page-item ${page == 1 ? 'active' : ''}">
                    <a class="page-link btn-dark" href="${pageContext.request.contextPath}?page=1">
                        1
                    </a>
                </li>

                <li class="page-item ${page == 2 ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=2">
                        2
                    </a>
                </li>
            </c:when>
            <c:when test="${max_page == 3}">
                <li class="page-item ${page == 1 ? 'active' : ''}">
                    <a class="page-link btn-dark" href="${pageContext.request.contextPath}?page=1">
                        1
                    </a>
                </li>

                <li class="page-item ${page == 2 ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=2">
                        2
                    </a>
                </li>

                <li class="page-item ${page == 3 ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=3">
                        2
                    </a>
                </li>
            </c:when>
            <c:when test="${max_page == page}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=1">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">First</span>
                    </a>
                </li>

                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=${page - 1}">
                        <c:out value="${page - 1}"/>
                    </a>
                </li>

                <li class="page-item active">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=${page}">
                        <c:out value="${page}"/>
                    </a>
                </li>
            </c:when>
            <c:when test="${page != 1 && page != 2}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=1">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">First</span>
                    </a>
                </li>

                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=${page - 1}">
                        <c:out value="${page - 1}"/>
                    </a>
                </li>

                <li class="page-item active">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=${page}">
                        <c:out value="${page}"/>
                    </a>
                </li>

                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=${page + 1}">
                        <c:out value="${page + 1}"/>
                    </a>
                </li>

                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=${max_page}">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Last</span>
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item ${page == 1 ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=1">
                        1
                    </a>
                </li>

                <li class="page-item ${page == 2 ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=2">
                        2
                    </a>
                </li>

                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=3">
                        3
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>