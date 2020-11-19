<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="u" uri="/WEB-INF/tags.tld" %>

<div id="shoppingCart">
    <c:if test="${CURRENT_ACCOUNT == null}">
        <div class="alert alert-warning hidden-print" role="alert">Для создания заказа, пожалуйста, авторизуйтесь</div>
    </c:if>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Наименование товара</th>
            <th>Цена</th>
            <th>Количество</th>
            <th class="hidden-print">Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${CURRENT_SHOPPING_CART.items}">
            <tr id="product${item.product.id}" class="item">
                <td class="text-center"><img class="small" src="${item.product.imageLink}"
                                             alt="${item.product.name}"><br>${item.product.name}</td>
                <td class="price">₽ ${item.product.price}</td>
                <td class="count">${item.count}</td>
                <td class="hidden-print">
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
            <td colspan="2" class="text-right"><strong>Итого:</strong></td>
            <td colspan="2" class="total">₽ ${CURRENT_SHOPPING_CART.totalCost}</td>
        </tr>
        </tbody>
    </table>
        <div class="row hidden-print">
            <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
                <c:if test="${CURRENT_ACCOUNT == null}">
                <form action="/sign-in" method="get">
                    <c:if test="${fn:startsWith(CURRENT_REQUEST_URL,'/search') or fn:startsWith(CURRENT_REQUEST_URL, '/products') or
						CURRENT_REQUEST_URL == '/shopping-cart' }">
                        <u:urlEncode url="${CURRENT_REQUEST_URL }" var="encodedUrl"/>
                        <input type="hidden" name="target" value="${encodedUrl }">
                    </c:if>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </form>
                </c:if>
                <c:if test="${CURRENT_ACCOUNT != null}">
                    <a href="javascript:void(0);" class="post-request btn btn-primary" data-url="/order">Создать заказ</a>
                </c:if>
            </div>
        </div>
</div>