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
    <c:out value="${user.id}"/>--!--<c:out value="${user.name}"/>
    <a href="<c:url value="${user.id}/edit" />">Edit</a>   &nbsp;
    <a href="<c:url value="/users/" />">View all users</a>
<br/></body>
</html>