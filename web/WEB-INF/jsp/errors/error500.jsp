<%--
  Created by IntelliJ IDEA.
  User: andrii.loboda
  Date: 02.09.11
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
Error 500<br/>
Sorry, Error has happened. Please, try again later.
<br/>
For developers:<br/>
Message:<c:out value="${exception.message}"/><br/>

</body>
</html>