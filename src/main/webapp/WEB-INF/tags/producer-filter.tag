<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="producers" required="true" type="java.util.Collection" %>
<%@ attribute name="searchForm" required="true" type="net.onlineStore.form.SearchForm" %>

<div class="panel-heading">Фильтр по производителю</div>
<div class="panel-body producers">
    <label> <input type="checkbox" id="allProducers"> Все </label>
    <c:forEach var="producer" items="${producers }">
        <div class="form-group">
            <div class="checkbox">
                <label><input type="checkbox" name="producer" value="${producer.id }"
                    ${searchForm.producers.contains(producer.id) ? 'checked' : ''}
                              class="search-option">${producer.name } (${producer.productCount })
                </label>
            </div>
        </div>
    </c:forEach>
</div>