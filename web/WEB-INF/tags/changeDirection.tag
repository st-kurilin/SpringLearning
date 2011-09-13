<%@ attribute name="root" required="true" %>
<%@ attribute name="sortBy" required="true" %>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="sortByLowerCase" value="${sortBy.toLowerCase()}"/>
<c:set var="order" value="${page.sort.getOrderFor(sortBy)}"/>
<c:choose>
    <c:when test="${sortByLowerCase eq order.property.toLowerCase()}">
        <c:set var="direction" value="${order.direction == 'ASC' ? 'DESC': 'ASC'}"/>
    </c:when>
    <c:otherwise>
        <c:set var="direction" value="ASC"/>
    </c:otherwise>
</c:choose>

<a href='<c:url value="${root}?page=0&sortBy=${sortByLowerCase}&direction=${direction}"/>'>
    <jsp:doBody/>
</a>