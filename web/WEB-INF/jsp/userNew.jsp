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
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<html>
<head>
    <title>Simple jsp page</title>
    <script src="<c:url value="/resources/js/jquery-1.6.2.min.js" />"></script>
</head>
<body>
1
    <c:url var="formSend" value="/users/new"/>
    <tags:userForm formSend="${formSend}" user="${user}"/>
    <a href="<c:url value="/users" />">Cancel and view all users</a>

2Place your content here
</body>
</html>