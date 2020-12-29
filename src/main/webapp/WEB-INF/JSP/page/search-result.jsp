<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="alert alert-info">
    <p><%
        int productCount = (Integer) request.getAttribute("productCount");
        int preLastDigit = productCount % 100 / 10;

        if (preLastDigit == 1) {
            out.print("Найдено <strong>" + productCount + "</strong>  товаров");
        }

        switch (productCount % 10) {
            case 1:
                out.print("Найден <strong>" + productCount + "</strong>  товар");
                break;
            case 2:
            case 3:
            case 4:
                out.print("Найдено <strong>" + productCount + "</strong>  товара");
                break;
            default:
                out.print("Найдено <strong>" + productCount + "</strong>  товаров");
                break;
        }
    %>
    </p>
</div>

<jsp:include page="products.jsp"/>
