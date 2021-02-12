<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="container-fluid">
<h4 style="text-align: center">АРМ Менеджера</h4>
    <div class="row"><hr>
        <div class="col-md-2 hidden-sm hidden-xs"></div>
        <div class="col-md-2 col-sm-3 col-xs-6">
            <div class="panel panel-primary-icon" onclick="location.href='/manager/orders';">
                <img src="${pageContext.request.contextPath}/static/img/icons/edit_orders.png" height="60%"
                     style="alignment: top; margin-top: 5%; margin-left: 7%" alt="Работа с заказами"><br>
                <label style="margin-left: 5%; margin-right: 5%; margin-top: 5%">Работа с заказами</label>
            </div>
        </div>
        <div class="col-md-2 col-sm-3 col-xs-6">
            <div class="panel panel-primary-icon" onclick="location.href='/manager/products';">
                <img src="${pageContext.request.contextPath}/static/img/icons/edit-products.png" height="60%"
                     style="alignment: top; margin-top: 5%; margin-left: 7%;" alt="Работа с товаром"><br>
                <label style="margin-left: 5%; margin-right: 5%; margin-top: 5%">Работа с товаром</label>
            </div>
        </div>
        <div class="col-md-2 col-sm-3 col-xs-6">
            <div class="panel panel-primary-icon" onclick="location.href='/manager/sales';">
                <img src="${pageContext.request.contextPath}/static/img/icons/sale.png" height="60%"
                     style="alignment: top; margin-top: 5%" alt="Акции"><br>
                <label style="margin-left: 5%; margin-right: 5%; margin-top: 5%">Акции</label>
            </div>
        </div>
        <div class="col-md-2 col-sm-3 col-xs-6">
            <div class="panel panel-primary-icon" onclick="location.href='/manager/reports';">
                <img src="${pageContext.request.contextPath}/static/img/icons/system.png" height="60%"
                     style="alignment: top; margin-top: 5%" alt="Отчеты"><br>
                <label style="margin-left: 5%; margin-right: 5%; margin-top: 5%">Отчеты</label>
            </div>
        </div>
        <div class="col-md-2 hidden-sm hidden-xs"></div>
    </div>
</div>