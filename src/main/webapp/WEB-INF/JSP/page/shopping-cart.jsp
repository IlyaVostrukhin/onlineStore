<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="shoppingCart">
    <sec:authorize access="!isAuthenticated()">
        <div class="alert alert-warning hidden-print" role="alert">Для создания заказа, пожалуйста, авторизуйтесь</div>
    </sec:authorize>
    <table class="table table-bordered">
        <thead style="color: #FFFFFF; background-color: #595959">
        <tr>
            <th class="text-center" width="20%">Наименование товара</th>
            <th class="text-center" width="15%">Изображение</th>
            <th class="text-center" width="15%">Количество</th>
            <th class="text-center" width="15%">Цена</th>
            <th class="text-center" width="15%">Сумма</th>
            <th class="hidden-print text-center" width="20%">Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${currentShoppingCart.items}">
            <tr id="product${item.product.id}" class="item">
                <td class="text-center">${item.product.name}</td>
                <td class="text-center"><img style="max-width: 85%" src="${item.product.imageLink}"></td>
                <td class="text-center price">₽ ${item.product.price}</td>
                <td class="text-center count">${item.count}</td>
                <td class="text-center">₽ ${item.product.price * item.count}</td>
                <td class="text-center hidden-print">
                    <c:choose>
                        <c:when test="${item.count > 1}">
                            <a class="btn btn-danger remove-product" data-id-product="${item.product.id}"
                               data-count="1">Удалить 1 шт.</a><br><br>
                            <a class="btn btn-danger remove-product remove-all" data-id-product="${item.product.id}"
                               data-count="${item.count}"> Удалить всё </a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-danger remove-product" data-id-product="${item.product.id}"
                               data-count="1">Удалить 1 шт.</a><br><br>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="4" class="text-right"><strong>Итого:</strong></td>
            <td colspan="2" class="total">₽ ${currentShoppingCart.totalCost}</td>
        </tr>
        </tbody>
    </table>
        <div class="row hidden-print">
            <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
                <sec:authorize access="!isAuthenticated()">
                <form action="/sign-in" method="get">
                    <c:if test="${fn:startsWith(CURRENT_REQUEST_URL,'/search') or fn:startsWith(CURRENT_REQUEST_URL, '/products') or
						CURRENT_REQUEST_URL == '/shopping-cart' }">
                        <u:urlEncode url="${CURRENT_REQUEST_URL }" var="encodedUrl"/>
                        <input type="hidden" name="target" value="${encodedUrl }">
                    </c:if>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </form>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <sec:csrfInput/>
                    <a href="/checkout" class="btn btn-primary">Сформировать заказ</a>
                </sec:authorize>
            </div>
        </div>
</div>