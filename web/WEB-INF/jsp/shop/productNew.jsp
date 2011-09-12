<%--
  User: andrii.loboda
  Date: 06.09.11
--%>
<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<html>
<head>
    <title>Simple jsp page</title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.2.min.js" />"></script>

</head>
<body>
<c:url var="formSend" value="/products/new"/>
<tags:productForm formSend="${formSend}" product="${product}"/>
<a href="<c:url value="/products" />">Cancel and view all products</a>
</body>
</html>