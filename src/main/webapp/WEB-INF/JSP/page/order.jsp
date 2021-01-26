<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="noname" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="order">
    <c:if test="${CURRENT_MESSAGE != null}">
        <div class="alert alert-success hidden-print" role="alert">${CURRENT_MESSAGE}</div>
    </c:if>
    <h3 class="text-center">Заказ № ${order.id } (${order.status.name})</h3>
    <hr/>
    <h5>Получатель: ${order.recipient}</h5>
    <hr/>
    <noname:product-table items="${order.items }" totalCost="${order.totalCost }" showActionColumn="false" />
    <div class="row hidden-print">
        <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
            <a href="/orders" class="btn btn-primary btn-block">Мои заказы</a>
        </div>
    </div>
</div>