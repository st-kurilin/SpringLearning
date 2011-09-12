<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="root" required="true" %>
<%@ attribute name="desc" required="true" %>
<%@ attribute name="range" required="true" %>
<%--
<c:set var="order" value="${page.sort.iterator().next()}"/>
--%>
<c:set var="addOrderToLink" value="sortBy=title&direction=ASC"/>
<c:choose>
    <c:when test="${desc}">
        <c:if test="${!page.firstPage}">
            <a href="<c:url value="${root}?page=0&${addOrderToLink}"/>">First</a>
            <a href="<c:url value="${root}?page=${page.number-1}&${addOrderToLink}"/>">Previous</a>

            <c:set var="start" value="${ (page.number-range) > 0 ? page.number - range : 0}"/>
            <c:forEach begin="${start}" end="${page.number-1}" var="index">
                <a href="<c:url value="${root}?page=${index}&${addOrderToLink}"/>">
                    <c:out value="${index}"/>
                </a>
            </c:forEach>
        </c:if>
    </c:when>
    <c:otherwise>
        <c:if test="${!page.lastPage}">
            <c:forEach begin="1" end="${(page.number+range < page.totalPages) ? range: page.totalPages - page.number-1}"
                       var="offset">
                <c:set var="index" value="${page.number+offset}"/>
                <a href="<c:url value="${root}?page=${index}&${addOrderToLink}"/>">
                    <c:out value="${index}"/>
                </a>
            </c:forEach>
            <a href="<c:url value="${root}?page=${page.number+1}&${addOrderToLink}"/>">Next</a>
            <a href="<c:url value="${root}?page=${page.totalPages-1}&${addOrderToLink}"/>">Last</a>
        </c:if>
    </c:otherwise>
</c:choose>