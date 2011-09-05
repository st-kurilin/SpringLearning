<%@ attribute name="formSend" required="true" %>
<%@ attribute name="userForm" required="true" type="com.controller.UserForm" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.ui.datepicker.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.ui.widget.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.ui.core.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.validate.js" />"></script>

<script>
    $(function() {
        $("#user\\.birthday").datepicker();

        $("#userForm").validate({
                rules:{
                  "user.name":{
                      required: true,
                      minlength:6,
                      maxlength:12
                  },
                  "user.email":{
                      required:true,
                      email:true,
                      remote:{
                          url:location.href.substring(0,location.href.lastIndexOf('users/'))+"users/isEmailAvailable",
                          type:"GET",
                          data:{
                               email:function() {
                                            return $("#user\\.email").val()
                                    }
                          }
                      }
                  },
                  "user.birthday":{
                      required:true,
                      date:true
                  },
                  avatarFile:{
                      accept: "png|jpe?g|gif|bmp"
                  },
                  "user.sex":{
                      required:true
                  }
                },
                messages:{
                   "user.name":{
                       required: "Enter the name, please.",
                       minlength:"Length of the name should be at least 6 symbols",
                       maxlength:"Length of the name should be at most 12 symbols"
                   },
                   "user.email":{
                         required: "Enter the mail, please.",
                         email:"Email is not valid",
                         remote:"Email is not availiable."
                    },
                    "user.birthday":{
                         required: "Enter the birthday, please.",
                         date:"Date is not valid"
                    },
                    avatarFile:{
                       accept:"Only files PNG, JPEG, GIF, BMP  can be uploaded"
                    },
                    "user.sex":{
                          required: "Are you Women or Man?"
                    }

                }
            })
    });
</script>

<link rel="stylesheet" href="http://jqueryui.com/themes/base/jquery.ui.all.css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/calendar_styles.css" />" media="all"/>
<link rel="stylesheet" href="<c:url value="/resources/css/validation_styles.css" />" media="all"/>

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
                <td><sf:errors path="user.email.*" cssClass="error"/></td>
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