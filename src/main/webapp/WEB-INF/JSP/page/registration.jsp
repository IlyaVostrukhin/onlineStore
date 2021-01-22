<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<%--<div class="container">--%>
<h3 class="col-sm-12"><b>Регистрация</b></h3>
<h5 class="col-sm-12">Пожалуйста, заполните все обязательные поля для регистрации.</h5>
<h5 class="col-sm-12">Просим Вас заполнять предельно внимательно и корректно, чтобы мы могли оперативно с Вами
    связаться.</h5>
<hr>
<%--@elvariable id="profileForm" type="net.onlineStore.entities.Profile"--%>
<form:form accept-charset="UTF-8" action="/registration" method="post" modelAttribute="profileForm">
    <sec:csrfInput/>
    <div class="form-row">
        <div class="form-group col-md-4 col-sm-12">
            <label for="login">Логин</label>
            <form:input type="text" class="form-control" id="login" name="login"
                        path="login" placeholder="Логин..." autofocus="true" required="true"></form:input>
            <form:errors path="login"></form:errors>
                ${loginError}
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="inputPassword">Пароль</label>
            <form:input type="password" class="form-control" id="inputPassword" name="inputPassword"
                        path="password" placeholder="Пароль..." required="true"></form:input>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="confirmPassword">Повторите пароль</label>
            <form:input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                        path="confirmPassword" placeholder="Повторите пароль..." required="true" ></form:input>
            <form:errors path="password" ></form:errors>
            <div class="form-row">${passwordError}</div>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-4 col-sm-12">
            <label for="surName">Фамилия</label>
            <form:input type="text" class="form-control" id="surName" name="surName"
                        path="surName" placeholder="Иванов" required="true"></form:input>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="firstName">Имя</label>
            <form:input type="text" class="form-control" id="firstName" name="firstName"
                        path="name" placeholder="Иван" required="true"></form:input>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="lastName">Отчество</label>
            <form:input type="text" class="form-control" id="lastName" name="lastName"
                        path="lastName" placeholder="Иванович"></form:input>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-4 col-sm-12">
            <label for="inputCity">Город</label>
            <form:input type="text" class="form-control" id="inputCity" name="inputCity"
                        path="city" placeholder="Новосибирск" required="true"></form:input>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="phone">Номер телефона</label>
            <form:input type="text" class="form-control" id="phone" name="phone"
                        path="phone" placeholder="+7-999-999-99-99" required="true"></form:input>
            <form:errors path="phone"></form:errors>
                ${phoneError}
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="inputEmail">Email</label>
            <form:input type="email" class="form-control" id="inputEmail" name="inputEmail"
                        path="email" placeholder="Email..." required="true"></form:input>
            <form:errors path="email"></form:errors>
                ${emailError}
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="postcode">Почтовый индекс</label>
            <form:input type="text" class="form-control" id="postcode" name="postcode"
                        path="postcode" placeholder="Должен соответствовать введенному адресу"></form:input>
        </div>
        <div class="form-group col-md-8">
            <label for="inputAddress">Адрес</label>
            <form:input type="text" class="form-control" id="inputAddress" name="inputAddress"
                        path="address" placeholder="ул.3-я Строителей, д.25, кв.12" required="true"></form:input>
        </div>
    </div>
    <div class="form-group col-md-12">
        <div class="custom-checkbox">
            <label class="form-check-label">
                <input class="custom-control-input" type="checkbox"> Запомнить меня
            </label>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6" style="display: flex; align-items: center; justify-content: center;">
            <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
        </div>
        <div class="form-group col-md-6" style="display: flex; align-items: center; justify-content: center;">
            <a class="btn btn-primary">Восстановить доступ</a>
        </div>
    </div>
</form:form>
<%--</div>--%>