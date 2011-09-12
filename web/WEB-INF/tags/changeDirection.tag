<%@ attribute name="root" required="true" %>
<%@ attribute name="sortBy" required="true" %>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="order" value="${page.sort.iterator().next()}"/>
<c:set var="direction" value="ASC"/>

<c:if test="${sortBy eq order.property}">
    <c:set var="direction" value="${order.direction == 'ASC' ? 'DESC': 'ASC'}"/>
</c:if>

<a href='<c:url value="${root}?page=0&sortBy=${sortBy}&direction=${direction}"/>'>
    <jsp:doBody/>
</a>