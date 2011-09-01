<%--
  Created by IntelliJ IDEA.
  User: stanislav.kurilin
  Date: 8/23/11
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
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
            <td>Name</td>
            <td>Email</td>
            <td colspan=2>Links</td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.email.value}"/></td>
            <td><a href="<c:url value="/users/${user.id}"/>">View</a></td>
            <td><a href="<c:url value="/users/${user.id}/edit"/>">Edit</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5"><a href="<c:url value="/users/new" />"> Add new user</a></td>
    </tr>
</table>
</body>
</html>