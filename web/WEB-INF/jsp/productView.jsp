<%--
  User: andrii.loboda
  Date: 06.09.11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<table>
    <tr>
        <td>Id</td>
        <td><c:out value="${product.id}"/></td>
    </tr>
    <tr>
        <td>Title</td>
        <td><c:out value="${product.title}"/></td>
        <td><a href="<c:url value="/products" />">View all products</a></td>
    </tr>
    <tr>
        <td>Price</td>
        <td><c:out value="${product.price}"/></td>
    </tr>
    <tr>
        <td>User</td>
        <td>
            <a href="<c:url value="/users/${product.user.id}"/>">
                <c:out value="${product.user.name}"/>
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