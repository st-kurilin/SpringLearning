<%@ attribute name="currentUser" type="com.domain.customer.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="menu">
    <ul>
        <c:choose>
            <c:when test="${currentUser != null}">
                <li>Hello, <c:out value="${currentUser.name}"/></li>
                <li><a href='<c:url value="/users/${currentUser.id}"/>'>My profile</a></li>
                <sec:authorize url="/products">
                    <li><a href='<c:url value="/products"/>'>Products</a></li>
                    <li><a href='<c:url value="/products/new"/>'>Create new product</a></li>
                </sec:authorize>
                <li><a href='<c:url value="/signout"/>'>Sign out</a></li>
            </c:when>
            <c:otherwise>
                <li><a href='<c:url value="/signin"/>'>Sign in</a></li>
                <li><a href='<c:url value="/users/new"/>'>Register</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href='<c:url value="/users"/>'>All users</a></li>
    </ul>
</div>
