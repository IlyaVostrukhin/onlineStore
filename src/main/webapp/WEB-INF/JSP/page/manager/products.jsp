<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div class="container" style="width: 90%; margin-bottom: 20px">
    <h4 style="text-align: center">АРМ Менеджера. Работа с товаром.</h4>
    <hr>
    <form action="/manager/search-products" method="get">
        <div class="row">
            <div class="col-md-4">
                <label for="searchProducts">Найти по артикулу, наименованию, описанию, производителю или
                    категории</label>
            </div>
            <div class="input-group col-md-4">
                <input type="text" name="query" class="form-control" placeholder="Поиск..."
                       value="${query}">
                <span class="input-group-btn">
                    <button id="searchProducts" class="btn btn-default" type="submit">Найти!</button>
                  </span>
            </div>
        </div>
    </form>
    <hr>
        <table class="table table-bordered select-row" width="100%"
               id="products" data-page-count="${page.totalPages}" data-page-number="1">
            <thead style="color: #FFFFFF; background-color: #595959">
            <th class="text-center" width="10%">Артикул</th>
            <th class="text-center" width="10%">Наименование</th>
            <th class="text-center" width="15%">Описание</th>
            <th class="text-center" width="10%">Изображение</th>
            <th class="text-center" width="10%">Категория</th>
            <th class="text-center" width="10%">Производитель</th>
            <th class="text-center" width="10%">Цена</th>
            <th class="text-center" width="10%">Остаток</th>
            <th class="text-center" width="15%">Действие</th>
            </thead>
            <tbody>
            <jsp:include page="../../fragment/products-tbody.jsp"/>
            </tbody>
        </table>
        <div class="row">
            <div class="col-xs-4"></div>
            <div class="text-center hidden-print col-xs-4">
                <c:if test="${page.totalPages > 1}">
                    <a id="loadMoreProductsFromManager" class="btn btn-success">Показать еще</a>
                </c:if>
            </div>
            <div class="text-right col-xs-4">
                <a href="/manager" class="btn btn-primary">Вернуться назад</a>
            </div>
        </div>
</div>