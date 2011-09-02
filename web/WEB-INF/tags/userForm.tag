<%@ attribute name="formSend" required="true" %>
<%@ attribute name="user" required="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.ui.datepicker.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.ui.widget.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.ui.core.js"/>"></script>

    <link rel="stylesheet" href="http://jqueryui.com/themes/base/jquery.ui.all.css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/calendar_styles.css" />" media="all" />

    <script>
        $(function() {
            $( "#user\\.birthday" ).datepicker();
	    });
    </script>

    <link rel="stylesheet" href="http://hotlink.jquery.com/jqueryui/themes/base/jquery.ui.base.css"/>
    <link rel="stylesheet" href="http://hotlink.jquery.com/jqueryui/themes/base/jquery.ui.theme.css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/calendar_styles.css" />" media="all" />

<sf:form method="POST" modelAttribute="userForm" action="${formSend}" enctype="multipart/form-data">
    <fieldset>
        <table>
            <tr>
                <td>Name:</td>
                <td><sf:input path="user.name" size="10"/></td>
                <td><sf:errors path="user.name" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Mail:</td>
                <td><sf:input path="user.email" size="10"/></td>
                <td><sf:errors path="user.email.value" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Date of birthday:</td>
                <td><sf:input path="user.birthday" size="10"/></td>
                <td><sf:errors path="user.birthday" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Sex:</td>
                <td>
                    <sf:select path="user.sex">
                        <sf:option value="MALE">Male</sf:option>
                        <sf:option value="FEMALE">Female</sf:option>
                    </sf:select>
                </td>
                <td><sf:errors path="user.sex" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Avatar:</td>
                <td><input type="file" name="avatarFile"/>
                <sf:errors path="avatarFile" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan=3><input type="submit"/></td>
            </tr>
        </table>
    </fieldset>
</sf:form>