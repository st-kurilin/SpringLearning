<%--
  Created by IntelliJ IDEA.
  User: stanislav.kurilin
  Date: 8/25/11
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<table>
    <tr>
        <td>Id</td>
        <td><c:out value="${user.id}"/></td>
        <td><a href="<c:url value="/users/${user.id}/edit" />">Edit</a></td>
    </tr>
    <tr>
        <td>Name</td>
        <td><c:out value="${user.name}"/></td>
        <td><a href="<c:url value="/users" />">View all users</a></td>
    </tr>
    <tr>
        <td>Sex</td>
        <td><c:out value="${user.sex}"/></td>
    </tr>
    <tr>
        <td>Email</td>
        <td><c:out value="${user.email.value}"/></td>
    </tr>
</table>
</body>
</html>