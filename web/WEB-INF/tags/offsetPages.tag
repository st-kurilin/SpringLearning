<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="root" required="true" %>
<%@ attribute name="asc" required="true" %>
<%@ attribute name="range" required="true" %>
<c:choose>
    <c:when test="${!asc}">
        <c:if test="${!page.firstPage}">
            <a href="<c:url value="${root}?page=0"/>">First</a>
            <a href="<c:url value="${root}?page=${page.number-1}"/>">Previous</a>

            <c:set var="start" value="${ (page.number-range) > 0 ? page.number - range : 0}"/>
            <c:forEach begin="${start}" end="${page.number-1}" var="index">
                <a href="<c:url value="${root}?page=${index}"/>">
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
                <a href="<c:url value="${root}?page=${index}"/>">
                    <c:out value="${index}"/>
                </a>
            </c:forEach>
            <a href="<c:url value="${root}?page=${page.number+1}"/>">Next</a>
            <a href="<c:url value="${root}?page=${page.totalPages-1}"/>">Last</a>
        </c:if>
    </c:otherwise>
</c:choose>