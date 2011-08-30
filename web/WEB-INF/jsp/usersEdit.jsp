<%--
  Created by IntelliJ IDEA.
  User: stanislav.kurilin
  Date: 8/25/11
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
1
<c:url var="formSend" value="/users/${user.id}"/>
<sf:form method="POST"  modelAttribute="user" action="${formSend}">
    <fieldset>
        <sf:input path="name" size="10" />
        <sf:input path="email" size="10" />
        <input type="submit"/>
    </fieldset>
</sf:form>
    <a href="<c:url value="/users/" />">Cancel and view all users</a>
    <a href="<c:url value="/users/${user.id}" />">Cancel and view this user</a>

2Place your content here
</body>
</html>