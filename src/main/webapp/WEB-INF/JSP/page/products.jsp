<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="popup" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<div class="row">
    <div id="productContainer" class="row" data-page-count="${page.totalPages}" data-page-number="${page.number }">
        <jsp:include page="../fragment/product-list.jsp"/>
    </div>
    <c:if test="${page.number < page.totalPages - 1}">
        <div id="loadMoreContainer" class="col-xs-12 text-center">
            <a href="javascript:store.moreProducts('${query }');" class="btn btn-success">Показать еще</a>
        </div>
        <div id="loadMoreIndicator" class="col-xs-12 text-center" style="display:none;">
            <img src="/static/img/large-loading.gif" alt="loading..."/>
        </div>
    </c:if>
</div>
<popup:add-product-popup/>