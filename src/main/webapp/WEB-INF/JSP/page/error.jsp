<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="popup" tagdir="/WEB-INF/tags" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="alert alert-danger hidden-print" role="alert">
    <h1>Код ошибки: ${statusCode }</h1>
    <c:choose>
        <c:when test="${statusCode == 403}">У Вас недостаточно прав для просмотра этой страницы</c:when>
        <c:when test="${statusCode == 404}">Страница не найдена</c:when>
        <c:otherwise>Запрос не может быть выполнен. Попробуйте позже.</c:otherwise>
    </c:choose>
</div>