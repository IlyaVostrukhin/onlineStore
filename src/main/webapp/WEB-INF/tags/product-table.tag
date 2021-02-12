<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="items" required="true" type="java.util.Collection"%>
<%@ attribute name="totalCost" required="true" type="java.lang.Number"%>
<%@ attribute name="showActionColumn" required="true" type="java.lang.Boolean"%>

<table class="table table-bordered">
    <thead style="color: #FFFFFF; background-color: #595959">
    <tr>
        <th class="text-center" width="10%">Артикул</th>
        <th class="text-center" width="15%">Наименование товара</th>
        <th class="text-center" width="20%">Описание товара</th>
        <th class="text-center" width="10%">Изображение</th>
        <th class="text-center" width="15%">Количество</th>
        <th class="text-center" width="15%">Цена</th>
        <th class="text-center" width="15%">Сумма</th>
        <c:if test="${showActionColumn }">
            <th class="hidden-print">Действие</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items }">
        <tr id="product${item.product.id }" class="item">
            <td class="text-center">${item.product.id }</td>
            <td class="text-center">${item.product.name }</td>
            <td class="text-center">${item.product.description }</td>
            <td class="text-center"><img style="max-width: 85%" src="${item.product.imageLink }" alt="${item.product.name }"></td>
            <td class="text-center" class="count">${item.count }</td>
            <td class="text-center" class="price">₽ ${item.price }</td>
            <td class="text-center" class="price">₽ ${item.price * item.count }</td>
            <c:if test="${showActionColumn }">
                <td class="hidden-print">
                    <c:choose>
                        <c:when test="${item.count > 1 }">
                            <a class="btn btn-danger remove-product" data-id-product="${item.product.id }" data-count="1">Удалить один</a><br><br>
                            <a class="btn btn-danger remove-product remove-all" data-id-product="${item.product.id }" data-count="${item.count }">Удалить все</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-danger remove-product" data-id-product="${item.product.id }" data-count="1">Удалить один</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="6" class="text-right"><strong>Итого:</strong></td>
        <td colspan="${showActionColumn ? 2 : 1}" class="total">₽ ${totalCost}</td>
    </tr>
    </tbody>
</table>
