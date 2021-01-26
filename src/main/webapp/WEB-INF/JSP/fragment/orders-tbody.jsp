<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach var="order" items="${orders }">
    <tr class="item" style="height: 100px">
        <td class="text-center"><a href="/order?id=${order.id }"><h5 href="/order?id=${order.id }" class="fa fa-edit"></h5></a></td>
        <td class="text-center">№ ${order.id }</td>
        <td class="text-center"><fmt:formatDate value="${order.created }" pattern="dd.MM.yyyy - HH:mm" /></td>
<%--        <td>${order.}</td>--%>
        <td class="text-center">${order.recipient}</td>
        <td class="text-center">${order.totalCount}</td>
        <td class="text-center">${order.totalCost}</td>
        <td class="text-center">${order.status.name}</td>
<%-- TODO: Сумма, количество товара, статус заказа, кнопки управления (Редактировать, просмотреть, удалить)       --%>
    </tr>
</c:forEach>