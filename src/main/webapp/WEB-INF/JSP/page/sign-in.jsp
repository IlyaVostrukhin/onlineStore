<%@ page import="net.onlineStore.Constants" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="form-group">
    <form action="/sign-in-handler" method="post">
        <sec:csrfInput/>
        <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION == null}">
            <h4 align="center"><b>Рады приветствовать Вас в нашем магазине!</b></h4>
            <h5 align="center"><b>Для продолжения авторизуйтесь или зарегистрируйтесь в системе.</b></h5><br/>
        </c:if>
        <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null}">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                Пользователь с таким именем и паролем не найден!
                <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
            </div>
        </c:if>
        <div class="form-group">
            <label for="uid">Логин</label>
            <input type="text" class="form-control" id="uid" name="uid" aria-describedby="loginHelp"
                   placeholder="Введите логин" required>
            <small id="loginHelp" class="form-text text-muted">Введите логин, e-mail или номер телефона в формате
                +7-999-999-99-99</small>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="Пароль" required>
        </div>
        <div class="form-group">
            <label><input type="checkbox" name="remember-me" value="true"> Запомнить меня</label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Войти</button>
            <a href="/registration?target=${encodedUrl }" class="btn btn-primary">Регистрация</a>
        </div>
    </form>
</div>