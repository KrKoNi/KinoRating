<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<div class="star-rating">
        <label class="fas fa-star">
                <input type="submit" name="rate" value="1">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="2">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="3">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="4">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="5">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="6">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="7">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="8">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="9">
        </label>
        <label class="fas fa-star">
                <input type="submit" name="rate" value="10">
        </label>
</div>
<select>
        <option onclick="sendRate(${movie.id}, 10)" value="10" name="rate" class="rate">10</option>
        <option onclick="sendRate(${movie.id}, 9)" value="9" name="rate" class="rate">9</option>
        <option onclick="sendRate(${movie.id}, 8)" value="8" name="rate" class="rate">8</option>
        <option onclick="sendRate(${movie.id}, 7)" value="7" name="rate" class="rate">7</option>
        <option onclick="sendRate(${movie.id}, 6)" value="6" name="rate" class="rate">6</option>
        <option onclick="sendRate(${movie.id}, 5)" value="5" name="rate" class="rate">5</option>
        <option onclick="sendRate(${movie.id}, 4)" value="4" name="rate" class="rate">4</option>
        <option onclick="sendRate(${movie.id}, 3)" value="3" name="rate" class="rate">3</option>
        <option onclick="sendRate(${movie.id}, 2)" value="2" name="rate" class="rate">2</option>
        <option onclick="sendRate(${movie.id}, 1)" value="1" name="rate" class="rate">1</option>
</select>