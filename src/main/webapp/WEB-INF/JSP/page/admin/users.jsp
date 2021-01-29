<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div class="container">
    <div class="container">
        <form action="/admin/search-users" method="get">
            <label for="userSearch">Найти по фамилии, e-mail, телефону или городу</label>
            <div class="input-group col-md-4">
                <input type="text" name="query" class="form-control" placeholder="Поиск..."
                       value="${query}">
                <span class="input-group-btn">
                    <button id="userSearch" class="btn btn-default" type="submit">Найти!</button>
                  </span>
            </div>
        </form>
    </div>
    <hr>
    <div class="container">
        <table class="table table-bordered" width="100%"
               id="users" data-page-count="${page.totalPages}" data-page-number="1">
            <thead style="color: #FFFFFF; background-color: #595959">
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Город</th>
            <th>Телефон</th>
            <th>E-mail</th>
            <th>Количество заказов</th>
            <th class="text-center">
                <div class="fa fa-edit"></div>
            </th>
            </thead>
            <tbody>
                <jsp:include page="../../fragment/users-tbody.jsp"/>
            </tbody>
        </table>
        <div class="col-md-4"></div>
        <div class="text-center hidden-print col-md-4">
            <c:if test="${page.totalPages > 1}">
                <a id="loadMoreUsers" class="btn btn-success">Показать еще</a>
            </c:if>
        </div>
        <div class="text-right col-md-4">
            <a href="#" class="btn btn-primary" onclick="history.go(-1)">Вернуться назад</a>
        </div>
    </div>
</div>