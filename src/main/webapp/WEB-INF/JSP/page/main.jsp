<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="category" items="${categories }">
    <p>${category.id} - ${category.name} - ${category.url} - ${category.productCount}</p>
</c:forEach>