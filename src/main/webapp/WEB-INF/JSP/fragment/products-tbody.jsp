<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="product" items="${allProducts }">
    <tr>
        <td class="text-center">${product.id }</td>
        <td class="text-center hidden-xs">${product.name}</td>
        <td class="text-center hidden-xs">${product.description}</td>
        <td class="text-center"><img style="max-width: 85%" src="${product.imageLink }" alt="${product.name }"></td>
        <td class="text-center">${product.category.name}</td>
        <td class="text-center">${product.producer.name}</td>
        <td class="text-center">${product.price}</td>
            <%--        TODO: popup --%>
        <td class="text-center">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default" title="Оприходовать">
                    <a href="/manager/products/add?id=${product.id }">
                        <span class="fa fa-plus" style="color: #595959"></span></a>
                </button>
                <button type="button" class="btn btn-default" title="Списать">
                    <a href="/manager/products/remove?id=${product.id }">
                        <span class="fa fa-remove" style="color: #595959"></span></a>
                </button>
                <button type="button" class="btn btn-default" title="Редактировать">
                    <a href="/manager/products/edit?id=${product.id }">
                        <span class="fa fa-edit" style="color: #595959"></span></a>
                </button>
            </div>
        </td>
    </tr>
</c:forEach>