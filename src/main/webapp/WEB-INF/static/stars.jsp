<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mb-5">
        <div class="star-widget">
                <form method="post" action="${pageContext.request.contextPath}/app/rate">
                        <input hidden name="movie_id" value="${movie.id}"/>
                        <label class="fas fa-star">
                                <input type="submit" name="rate" value="5">
                        </label>
                        <label class="fas fa-star">
                                <input type="submit" name="rate" value="4">
                        </label>
                        <label class="fas fa-star">
                                <input type="submit" name="rate" value="3">
                        </label>
                        <label class="fas fa-star">
                                <input type="submit" name="rate" value="2">
                        </label>
                        <label class="fas fa-star">
                                <input type="submit" name="rate" value="1">
                        </label>
                </form>
        </div>
</div>