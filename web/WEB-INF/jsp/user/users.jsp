<%--
  User: stanislav.kurilin
  Date: 8/23/11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<tags:menu currentUser="${currentUser}"/>
<c:choose>
    <c:when test="${fn:length(users)!=0}">
        <table>
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Email</td>
                <td colspan=2>Links</td>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email.value}"/></td>
                    <td><a href="<c:url value="/users/${user.id}"/>">View</a></td>
                    <sec:authorize url="/users/*/edit">
                        <td><a href="<c:url value="/users/${user.id}/edit"/>">Edit</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>There are no any users</c:otherwise>
</c:choose>
</body>
</html>