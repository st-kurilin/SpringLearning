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
<head>
    <title>Simple jsp page</title>
    <script src="<c:url value="/resources/js/jquery-1.6.2.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" />"></script>


    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.3/themes/base/jquery-ui.css" />\
    <link rel="stylesheet" href="<c:url value="/resources/css/calendar_styles.css" />" media="all" />

    <script>
        $(function() {
            $( "#datepicker" ).datepicker();
        });
	</script>
</head>
<body>
1
<c:url var="formSend" value="/users/new"/>
<sf:form method="POST"  modelAttribute="user" action="${formSend}">
    <fieldset>
        <table>
           <tr><td>Name:</td><td> <sf:input path="name" size="10" /></td></tr>
           <tr><td>Mail:</td><td><sf:input path="email" size="10" /></td></tr>
           <tr>
               <td>Date of birthday:</td>
               <td><input type="text" name="birthday" size="10" id="datepicker"></td>
           </tr>
           <tr><td colspan=2><input type="submit"/></td></tr>
        </table>
    </fieldset>
</sf:form>
    <a href="<c:url value="/users/" />">Cancel and view all users</a>

2Place your content here
</body>
</html>