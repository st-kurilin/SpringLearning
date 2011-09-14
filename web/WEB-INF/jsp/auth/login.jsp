<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<html>
<head><title>Login Page</title></head>
<body onload='document.f.email.focus()'>
<tags:menu/>

<h3>Login with Username and Password</h3>
<c:if test="${signinError}">
    Please retry.
</c:if>
<form name='f' action='/signin_form' method='POST'>
    <table>
        <tr>
            <td>Email:</td>
            <td><input type='text' name='email' value='${login}'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td><input type='checkbox' name='_spring_security_remember_me'/></td>
            <td>Remember me on this computer.</td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit"/></td>
        </tr>

        <tr>
            <td colspan='2'><input name="reset" type="reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>