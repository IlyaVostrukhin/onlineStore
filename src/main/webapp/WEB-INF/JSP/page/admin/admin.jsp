<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="container-fluid">
<h3 style="text-align: center">О, Повелитель!</h3>
<h4 style="text-align: center">Какие действия ты будешь вершить сегодня?</h4>
    <div class="row"><hr>
        <div class="col-md-2 hidden-sm hidden-xs"></div>
        <div class="col-md-2 col-sm-3 col-xs-6">
            <div class="panel panel-primary-icon" onclick="location.href='/admin/users';">
                <img src="${pageContext.request.contextPath}/static/img/icons/edit_users.png" height="60%"
                     style="alignment: top; margin-top: 5%; margin-left: 7%;">
                <label style="margin-left: 5%; margin-right: 5%; margin-top: 5%">Редактирование пользователей</label>
            </div>
        </div>
        <div class="col-md-2 col-sm-3 col-xs-6">
            <div class="panel panel-primary-icon" onclick="location.href='/admin/edit-orders';">
                <img src="${pageContext.request.contextPath}/static/img/icons/edit_orders.png" height="60%"
                     style="alignment: top; margin-top: 5%; margin-left: 7%">
                <label style="margin-left: 5%; margin-right: 5%; margin-top: 5%">Редактирование заказов</label>
            </div>
        </div>
        <div class="col-md-2 col-sm-3 col-xs-6">
            <div class="panel panel-primary-icon" onclick="location.href='/admin/system';">
                <img src="${pageContext.request.contextPath}/static/img/icons/system.png" height="60%"
                     style="alignment: top; margin-top: 5%;">
                <label style="margin-left: 5%; margin-right: 5%; margin-top: 5%">Мониторинг системы</label>
            </div>
        </div>
        <div class="col-md-2 col-sm-3 col-xs-6">
            <div class="panel panel-primary-icon" onclick="location.href='/admin/journal';">
                <img src="${pageContext.request.contextPath}/static/img/icons/journal.png" height="60%"
                     style="alignment: top; margin-top: 5%; margin-left: 7%">
                <label style="margin-left: 5%; margin-right: 5%; margin-top: 5%">Журнал операций</label>
            </div>
        </div>
        <div class="col-md-2 hidden-sm hidden-xs"></div>
    </div>
</div>