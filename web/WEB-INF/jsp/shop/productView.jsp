<%--
  User: andrii.loboda
  Date: 06.09.11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<tags:menu currentUser="${currentUser}"/>
<table>
    <tr>
        <td>Title</td>
        <td><c:out value="${product.title}"/></td>
    </tr>
    <tr>
        <td>Price</td>
        <td><c:out value="${product.price}"/></td>
    </tr>
    <tr>
        <td>User</td>
        <td>
            <a href="<c:url value="/users/${product.seller.id}"/>">
                <c:out value="${product.seller.name}"/>
            </a>
        </td>
    </tr>
    <tr>
        <td>Description</td>
        <td>
            <c:choose>
                <c:when test="${empty product.description}">
                    No description available.
                </c:when>
                <c:otherwise>
                    <c:out value="${product.description}"/>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
</body>
</html>