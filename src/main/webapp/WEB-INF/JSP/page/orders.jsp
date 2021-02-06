<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="noname" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h4 class="text-center">Мои заказы</h4>
<hr/>
<table id="myOrders" class="table table-bordered" data-page-number="1" data-page-count="${page.totalPages}">
    <thead style="color: #FFFFFF; background-color: #595959">
    <tr>
        <th width="5%" class="text-center" style="vertical-align: top"><div class="fa fa-edit"></div></th>
        <th width="10%" class="text-center" style="vertical-align: top">Номер заказа</th>
        <th width="15%" class="text-center" style="vertical-align: top">Дата</th>
        <th width="35%" class="text-center hidden-xs" style="vertical-align: top">Получатель</th>
        <th width="5%" class="text-center" style="vertical-align: top">Количество</th>
        <th width="10%" class="text-center" style="vertical-align: top">Сумма</th>
        <th width="10%" class="text-center" style="vertical-align: top">Статус</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty orders }">
        <tr>
            <td colspan="7" class="text-center">У Вас еще нет заказов. Самое время создать первый!<br>
                Для создания заказа добавьте товар в корзину и нажмите Создать заказ в корзине.</td>
        </tr>
    </c:if>
    <jsp:include page="../fragment/orders-tbody.jsp" />
    </tbody>
</table>
<div class="text-center hidden-print">
    <c:if test="${page.totalPages > 1 }">
        <a id="loadMoreMyOrders" class="btn btn-success">Показать еще</a>
    </c:if>
</div>