<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="Page navigation">
    <ul class="pagination justify-content-end">
        <c:choose>
            <c:when test="${pageContext.request.getParameter('page') != null}">
               <c:set var="page" value="${pageContext.request.getParameter('page')}"/>
            </c:when>
            <c:otherwise>
                <c:set var="page" value="1"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${page != 1 && page != 2}">
                <li class="page-item">
                    <a class="page-link btn-dark" href="${pageContext.request.contextPath}?page=1">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">First</span>
                    </a>
                </li>

                <li class="page-item">
                    <a class="page-link btn-dark" href="${pageContext.request.contextPath}?page=${page - 1}">
                        <c:out value="${page - 1}"/>
                    </a>
                </li>

                <li class="page-item btn-dark active">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=${page}">
                        <c:out value="${page}"/>
                    </a>
                </li>

                <li class="page-item btn-dark">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=${page + 1}">
                        <c:out value="${page + 1}"/>
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item btn-dark ${page == 1 ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=1">
                        1
                    </a>
                </li>

                <li class="page-item btn-dark ${page == 2 ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=2">
                        2
                    </a>
                </li>

                <li class="page-item btn-dark">
                    <a class="page-link" href="${pageContext.request.contextPath}?page=3">
                        3
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>