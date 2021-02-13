<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="noname" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="order">
    <c:if test="${CURRENT_MESSAGE != null}">
        <div class="alert alert-success hidden-print" role="alert">${CURRENT_MESSAGE}</div>
    </c:if>
    <h3 class="text-center">Заказ № ${order.id } (${order.status.name})</h3>
    <hr/>
    <h5>Получатель: ${order.recipient}</h5>
    <hr/>
    <c:if test="${order.modify != null}">
        <h5>Дата изменения статуса: <fmt:formatDate value="${order.created }" pattern="dd.MM.yyyy - HH:mm" /></h5>
        <hr/>
    </c:if>
    <noname:product-table items="${order.items }" totalCost="${order.totalCost }" showActionColumn="false"/>
    <div class="row hidden-print">
        <div class="col-xs-4"></div>
        <div class="text-center hidden-print col-xs-4">
            <a href="/orders" class="btn btn-primary">Мои заказы</a>
        </div>
        <c:if test="${order.status.id == 1}">
            <div class="text-right col-xs-4">
                <a class="btn btn-danger hidden-print cancel-order" data-id-product="${order.id}">
                    Отменить заказ
                </a>
            </div>
        </c:if>
    </div>
</div>