<%--
  User: stanislav.kurilin
  Date: 8/25/11
--%>
<!DOCTYPE html>

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
<tags:userForm userForm="${userForm}"/>

<a href="<c:url value="/users/${userForm.user.id}" />">View</a>

</body>
</html>