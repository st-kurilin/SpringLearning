<%--
  User: stanislav.kurilin
  Date: 8/25/11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<tags:menu currentUser="${currentUser}"/>
<table>
    <tr>
        <td>Id</td>
        <td><c:out value="${user.id}"/></td>
        <sec:authorize url="/users/*/edit">
        <td><a href="<c:url value="/users/${user.id}/edit" />">Edit</a></td>
            </sec:authorize>
    </tr>
    <tr>
        <td>Name</td>
        <td><c:out value="${user.name}"/></td>
    </tr>
    <tr>
        <td>Gender</td>
        <td><c:out value="${user.gender}"/></td>
    </tr>
    <tr>
        <td>Email</td>
        <td><c:out value="${user.email.value}"/></td>
    </tr>
    <sec:authorize url="/products">
    <tr>
        <td>Products of this user</td>
        <td>
            <c:choose>
                <c:when test="${fn:length(products)!=0}">
                    <table>
                        <tr>
                            <td>Title</td>
                            <td>Link</td>
                        </tr>

                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.title}</td>
                                <td><a href="<c:url value="/products/${product.title}"/>">View</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>No products</c:otherwise>
            </c:choose>
        </td>
    </tr>
    </sec:authorize>
</table>
</body>
</html>