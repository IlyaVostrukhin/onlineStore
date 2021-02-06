<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach items="${allUsers}" var="user">
    <tr>
        <td>${user.surName}</td>
        <td>${user.name}</td>
        <td>${user.lastName}</td>
        <td>${user.city}</td>
        <td>${user.phone}</td>
        <td>${user.email}</td>
        <td></td>
        <td class="text-center">
            <a href="/admin/user?id=${user.id }"><h5 class="fa fa-edit"></h5></a>
        </td>
    </tr>
</c:forEach>