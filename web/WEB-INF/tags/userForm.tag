<%@ attribute name="formSend" required="true" %>
<%@ attribute name="user" required="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <script src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" />"></script>
    <script>
        $(function() {
            $( "#birthday" ).datepicker();
        });
    </script>

    <link rel="stylesheet" href="http://jqueryui.com/themes/base/jquery.ui.all.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/calendar_styles.css" />" media="all" />

    <sf:form method="POST"  modelAttribute="user" action="${formSend}" enctype="multipart/form-data">
        <fieldset>
            <table>
               <tr><td>Name:</td><td> <sf:input path="name" size="10" /></td></tr>
               <tr><td>Mail:</td><td><sf:input path="email" size="10" /></td></tr>
               <tr>
                   <td>Date of birthday:</td>
                   <td><sf:input path="birthday" size="10" /></td>
               </tr>
                <tr><td>Avatar:</td><td><input type="file" name="avatar"/></td></tr>

               <tr><td colspan=2><input type="submit"/></td></tr>
            </table>
        </fieldset>
    </sf:form>