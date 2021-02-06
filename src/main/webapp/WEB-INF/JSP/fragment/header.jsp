<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="CURRENT_PROFILE"/>
    <c:set var="showEdit" value="${CURRENT_PROFILE.id == profile.id }"/>
</sec:authorize>

<nav class="navbar navbar-default navbar-height">
    <div class="container-fluid">
        <!-- Brand и toggle сгруппированы для лучшего отображения на мобильных дисплеях -->
        <div class="navbar-header">

            <a id="currentShoppingCartSmall"
               class="navbar-toggle navbar-brand navbar-right ${currentShoppingCart == null ? 'hidden' : ''}"
               href="/shopping-cart" style="height: 44px; width: 44px">
                <span class="fa fa-shopping-cart"></span>
            </a>

            <button type="button" class="navbar-toggle navbar-right collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false"
                    style="height: 44px; width: 44px">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand navbar-right hidden-xlg" href="/products"
               style="max-width: 220px; vertical-align: center; margin-top: -10px">
                <img alt="photo" src="../../../static/img/logo1_small.png" class="img-responsive">
            </a>
            <a class="navbar-brand visible-xlg" href="/products"
               style="width: 450px; vertical-align: center; margin-top: -5px; margin-left: -30px">
                <img alt="photo" src="../../../static/img/logo1.png" class="img-responsive">
            </a>
        </div>

        <!-- Соберите навигационные ссылки, формы, и другой контент для переключения -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:choose>
                <c:when test="${CURRENT_PROFILE != null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="visible-lg" style="vertical-align: top;"><a href="/orders">Добро пожаловать,
                            <br>${CURRENT_PROFILE.name}!</a></li>
                        <c:if test="${fn:contains(CURRENT_PROFILE.roles.toString(), 'ROLE_MANAGER')}">
                            <li style="vertical-align: top;"><a href="/manager">АРМ Менеджер</a></li>
                        </c:if>
                        <c:if test="${fn:contains(CURRENT_PROFILE.roles.toString(), 'ROLE_ADMIN')}">
                            <li style="vertical-align: top;"><a href="/admin">АРМ Администратор</a></li>
                        </c:if>
                        <li style="vertical-align: top;"><a href="/orders">Мои заказы</a></li>
                        <li style="vertical-align: top;">
                            <a href="javascript:store.logout('83anbx76-${_csrf.token}-srf32a');">Выйти</a>
                        </li>
                        <li style="vertical-align: top;"
                            id="currentShoppingCart"
                            class="hidden-xs hidden-sm ${currentShoppingCart == null ? 'hidden' : ''}">
                            <a href="/shopping-cart">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> Корзина <br>
                                Количество: <span class="total-count">${currentShoppingCart.totalCount}</span> шт <br>
                                Сумма: <span class="total-cost">${currentShoppingCart.totalCost}</span> ₽
                            </a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav navbar-right">
                        <li style="vertical-align: top;"><a href="/sign-in">Войти</a></li>
                        <li style="vertical-align: top;"
                            id="currentShoppingCart"
                            class="hidden-xs hidden-sm ${currentShoppingCart == null ? 'hidden' : ''}">
                            <a href="/shopping-cart">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> Корзина <br>
                                Количество: <span class="total-count">${currentShoppingCart.totalCount}</span> шт <br>
                                Сумма: <span class="total-cost">${currentShoppingCart.totalCost}</span> ₽
                            </a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>