<%--
  User: andrii.loboda
  Date: 06.09.11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
Place your content here
<table>
    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Price</td>
        <td>User</td>
        <td>Links</td>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.title}"/></td>
            <td><c:out value="${product.price}"/></td>
            <td><c:out value="${product.user.name}"/></td>
            <td><a href="<c:url value="/products/${product.title}"/>">View</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5"><a href="<c:url value="/products/new" />"> Add new product</a></td>
    </tr>
</table>
</body>
</html>