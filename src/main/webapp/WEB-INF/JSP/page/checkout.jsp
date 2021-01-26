<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="CURRENT_PROFILE"/>
    <c:set var="showEdit" value="${CURRENT_PROFILE.id == profile.id }"/>
</sec:authorize>
<div class="container-fluid">
    <h3><b>Формирование заказа</b></h3>
</div>

<hr>
<%--@elvariable id="orderForm" type="net.onlineStore.entities.Order"--%>
<form:form accept-charset="UTF-8" action="/checkout" method="post" modelAttribute="orderForm">
    <sec:csrfInput/>
    <div class="form-row">
        <h4>1. Проверка данных получателя:</h4><hr>
        <div class="form-group col-md-12 col-sm-12">
            <form:input type="text" class="form-control" id="recipient" name="recipient"
                        path="recipient" placeholder="Введите данные получателя (ФИО, телефон, адрес)..."
                        value="${CURRENT_PROFILE.fio}, ${CURRENT_PROFILE.recipient}"
                        required="true" cssStyle="width: 100%"></form:input>
        </div>
    </div><br/>
    <div class="form-row" style="margin-top: 70px">
        <h4>2. Выберите способ оплаты:</h4><hr>
        <br/>
        <h4>Тут когда-нибудь будет выбор оплаты. Или не будет.</h4>
        <br/><br/><br/><br/><br/>
<%--        ToDo: способ оплаты --%>
    </div>
    <div class="form-row">
        <div class="form-group col-md-12" style="display: flex; align-items: center; justify-content: center;">
            <button type="submit" class="btn btn-primary">Сформировать заказ</button>
        </div>
    </div>
</form:form>