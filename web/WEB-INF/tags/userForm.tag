<%@ attribute name="userForm" required="true" type="com.controller.UserForm" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tags.tld" prefix="tt" %>

<c:choose>
    <c:when test="${userForm.user.new}">
        <c:url var="formSend" value="/users/new"/>
    </c:when>
    <c:otherwise>
        <c:url var="formSend" value="/users/${userForm.user.id}"/>
    </c:otherwise>
</c:choose>

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
                <td><sf:errors path="user.email*" cssClass="error"/></td>
                <td><input type="hidden" value="${userForm.user.email}" id="initialMail"/>
            </tr>
            <tr>
                <td>Password:</td>
                <td><sf:input path="user.password" size="10"/></td>
                <td><sf:errors path="user.password" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Date of birthday:</td>
                <td><sf:input path="user.birthday" size="10"/></td>
                <td><sf:errors path="user.birthday" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td>
                    <sf:select path="user.gender">
                        <sf:option value="MALE">Male</sf:option>
                        <sf:option value="FEMALE">Female</sf:option>
                    </sf:select>
                </td>
                <td><sf:errors path="user.gender" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Avatar:</td>
                <td><input type="file" name="avatarFile"/>
                    <sf:errors path="avatarFile" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Roles:</td>
                <td>
                    <table>
                        <tt:rolesEditor user="${userForm.user}" path="user.role"/>
                    </table>
                    <input type="file" name="avatarFile"/>
                    <sf:errors path="avatarFile" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan=3><input type="submit"/></td>
            </tr>
        </table>
    </fieldset>
</sf:form>
<link rel="stylesheet" href="http://jqueryui.com/themes/base/jquery.ui.all.css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/calendar_styles.css" />" media="all"/>
<link rel="stylesheet" href="<c:url value="/resources/css/validation_styles.css" />" media="all"/>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.ui.datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.ui.widget.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.ui.core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.js" />"></script>
<script type="text/javascript">

    $(function() {
        $.validator.addMethod("serverCheck", function() {
            var retResp;
            if ($("#initialMail").val() == $("#user\\.email").val()) {
                retResp = "true";
            } else {
                $.ajax({
                            url:  "/users/emails/available",    //url is absolute - can be trouble with changing context
                            type:"GET",
                            async:false,
                            data: {
                                email:function() {
                                    return $("#user\\.email").val()
                                }
                            },
                            success: function(data) {
                                retResp = data;
                            }
                        });
            }
            return retResp == "true";
        });

        $("#user\\.birthday").datepicker();

        $("#userForm").validate({
                    rules:{
                        "user.name":{
                            required: true,
                            minlength:2,
                            maxlength:12
                        },
                        "user.email":{
                            required:true,
                            email:true,
                            serverCheck:true
                        },
                        "user.password":{
                            required: true ,
                            minlength:6,
                            maxlength:18
                        },
                        "user.birthday":{
                            required:true,
                            date:true
                        },
                        avatarFile:{
                            accept: "png|jpe?g|gif|bmp"
                        },
                        "user.gender":{
                            required:true
                        }
                    },
                    messages:{
                        "user.name":{
                            required: "Enter the name, please.",
                            minlength:"Length of the name should be at least 2 symbols",
                            maxlength:"Length of the name should be at most 12 symbols"
                        },
                        "user.email":{
                            required: "Enter the mail, please.",
                            email:"Email is not valid",
                            serverCheck:"Email is not availiable."
                        },
                        "user.password":{
                            required: "Enter the password, please." ,
                            minlength:"Length of the password should be at least 6 symbols",
                            maxlength:"Length of the password should be at most 18 symbols"
                        },
                        "user.birthday":{
                            required: "Enter the birthday, please.",
                            date:"Birthday is not valid"
                        },
                        avatarFile:{
                            accept:"Only files PNG, JPEG, GIF, BMP  can be uploaded"
                        },
                        "user.gender":{
                            required: "Are you Women or Man?"
                        }
                    },
                    onfocusout:true,
                    onclick: false
                })
    });
</script>
