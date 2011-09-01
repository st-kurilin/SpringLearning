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
               <tr>
                   <td>Name:</td>
                   <td> <sf:input path="name" size="10" /></td>
                   <td><sf:errors path="name" cssClass="error"/></td>
               </tr>
               <tr>
                   <td>Mail:</td>
                   <td><sf:input path="email" size="10" /></td>
                   <td><sf:errors path="email" cssClass="error"/></td>
               </tr>
               <tr>
                   <td>Date of birthday:</td>
                   <td><sf:input path="birthday" size="10" /></td>
                   <td><sf:errors path="birthday" cssClass="error"/></td>
               </tr>
                <tr>
                   <td>Sex:</td>
                   <td><sf:select path="sex">
                           <sf:option value="MALE">Male</sf:option>
                           <sf:option value="FEMALE">Female</sf:option>
                        </sf:select>
                   </td>
                   <td><sf:errors path="sex" cssClass="error"/></td>
               </tr>
                <tr>
                    <td>Avatar:</td>
                    <td><input type="file" name="avatar"/></td>
                </tr>
               <tr><td colspan=3><input type="submit"/></td></tr>
            </table>
        </fieldset>
    </sf:form>