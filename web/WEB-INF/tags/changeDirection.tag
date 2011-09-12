<%@ attribute name="root" required="true" %>
<%@ attribute name="orderBy" required="true" %>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="order" value="${page.sort.getOrderFor(orderBy.toLowerCase())}"/>
<c:choose>
    <c:when test="${orderBy.equalsIgnoreCase(order.property)}">
        <c:set var="direction" value="${order.direction == 'ASC' ? 'DESC': 'ASC'}"/>
    </c:when>
    <c:otherwise>
        <c:set var="direction" value="ASC"/>
    </c:otherwise>
</c:choose>

<a href='<c:url value="${root}?page=0&sortBy=${orderBy}&direction=${direction}"/>'>
    <jsp:doBody/>
</a>