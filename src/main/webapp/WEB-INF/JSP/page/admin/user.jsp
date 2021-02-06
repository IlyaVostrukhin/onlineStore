<%@ page import="net.onlineStore.entities.Profile" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="noname" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <div id="editUser">
        <h3 class="text-center">Редактирование пользователя с ID = ${user.id}</h3>
        <hr>
        <%--@elvariable id="profileForm" type="net.onlineStore.entities.Profile"--%>
        <form:form accept-charset="UTF-8" action="/admin/user?id=${user.id}" method="post" modelAttribute="profileForm">
            <sec:csrfInput/>
            <div class="form-row">
                <div class="form-group col-md-4 col-sm-12">
                    <label for="login">Логин</label>
                    <form:input type="text" class="form-control" id="login" name="login" value="${user.login}"
                                path="login" placeholder="Логин..." autofocus="true" required="true"></form:input>
                    <form:errors path="login"></form:errors>
                        ${loginError}
                </div>
                <div class="form-group col-md-4 col-sm-12">
                    <label for="inputPassword">Пароль</label>
                    <form:input type="password" class="form-control" id="inputPassword" name="inputPassword"
                                value="${user.password}"
                                path="password" placeholder="Введите НОВЫЙ пароль..."></form:input>
                </div>
                <div class="form-group col-md-4 col-sm-12">
                    <label for="confirmPassword">Повторите пароль</label>
                    <form:input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                                value="${user.password}"
                                path="confirmPassword" placeholder="Повторите НОВЫЙ пароль..."></form:input>
                    <form:errors path="password"></form:errors>
                    <div class="form-row">${passwordError}</div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4 col-sm-12">
                    <label for="surName">Фамилия</label>
                    <form:input type="text" class="form-control" id="surName" name="surName" value="${user.surName}"
                                path="surName" placeholder="Иванов" required="true"></form:input>
                </div>
                <div class="form-group col-md-4 col-sm-12">
                    <label for="firstName">Имя</label>
                    <form:input type="text" class="form-control" id="firstName" name="firstName" value="${user.name}"
                                path="name" placeholder="Иван" required="true"></form:input>
                </div>
                <div class="form-group col-md-4 col-sm-12">
                    <label for="lastName">Отчество</label>
                    <form:input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}"
                                path="lastName" placeholder="Иванович"></form:input>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4 col-sm-12">
                    <label for="inputCity">Город</label>
                    <form:input type="text" class="form-control" id="inputCity" name="inputCity" value="${user.city}"
                                path="city" placeholder="Новосибирск" required="true"></form:input>
                </div>
                <div class="form-group col-md-4 col-sm-12">
                    <label for="phone">Номер телефона</label>
                    <form:input type="text" class="form-control" id="phone" name="phone" value="${user.phone}"
                                path="phone" placeholder="+7-999-999-99-99" required="true"></form:input>
                    <form:errors path="phone"></form:errors>
                        ${phoneError}
                </div>
                <div class="form-group col-md-4 col-sm-12">
                    <label for="inputEmail">Email</label>
                    <form:input type="email" class="form-control" id="inputEmail" name="inputEmail"
                                value="${user.email}"
                                path="email" placeholder="Email..." required="true"></form:input>
                    <form:errors path="email"></form:errors>
                        ${emailError}
                </div>
                <div class="form-group col-md-4 col-sm-12">
                    <label for="postcode">Почтовый индекс</label>
                    <form:input type="text" class="form-control" id="postcode" name="postcode" value="${user.postcode}"
                                path="postcode" placeholder="Должен соответствовать введенному адресу"></form:input>
                </div>
                <div class="form-group col-md-8">
                    <label for="inputAddress">Адрес</label>
                    <form:input type="text" class="form-control" id="inputAddress" name="inputAddress"
                                value="${user.address}"
                                path="address" placeholder="ул.3-я Строителей, д.25, кв.12"
                                required="true"></form:input>
                </div>
            </div>
            <div class="form-row"><br/>
                <c:forEach var="role" items="${roles }">
                    <div class="checkbox-inline">
                        <input type="checkbox" name="selectedRoles" id="${role.id}" value="${role.id }"
                            ${user.roles.toString().contains(role.name) ? 'checked' : ''} class="custom-checkbox">
                        <label for="${role.id}">
                                ${role.name.equals("ROLE_USER") ? "Пользователь" : role.name.equals("ROLE_MANAGER") ? "Менеджер" : "Администратор" }
                        </label>
                    </div>
                </c:forEach>
            </div>
            <br/>
            <div class="form-row">
                <div class="col-xs-4"></div>
                <div class="form-group col-xs-4 text-center">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>
                <div class="form-group col-xs-4 text-right">
                    <a href="/admin/users" class="btn btn-primary">Вернуться назад</a>
                </div>
            </div>
        </form:form>
    </div>
</div>