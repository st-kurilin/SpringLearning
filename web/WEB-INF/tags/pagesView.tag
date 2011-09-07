<%@ attribute name="pageOfElements" type="org.springframework.data.domain.Page" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!--now only for products -  change links-->
<c:choose>
    <c:when test="${pageOfElements.firstPage}">
        it's a first Page
        <c:if test="${!pageOfElements.lastPage}">
            <c:forEach begin="1" end="2" var="page">
                <c:if test="${(pageOfElements.number+page)<pageOfElements.totalPages}">
                    <a href="<c:url value="/products?page=${pageOfElements.number+page}"/>">
                        <c:out value="${pageOfElements.number+page}"/>
                    </a>
                </c:if>
            </c:forEach>
            <a href="<c:url value="/products?page=${pageOfElements.totalPages-1}"/>">Last</a>
        </c:if>
    </c:when>
    <c:when test="${pageOfElements.lastPage}">
        <a href="<c:url value="/products?page=0"/>">First</a>
        <c:forEach begin="1" end="2" var="page" varStatus="loop">
            <c:if test="${(pageOfElements.number-(loop.end-page))>=0}">
                <a href="<c:url value="/products?page=${pageOfElements.number-(loop.end-page)}"/>">
                    <c:out value="${pageOfElements.number-(loop.end-page)}"/>
                </a>
            </c:if>
        </c:forEach>
        it's a last Page
    </c:when>
    <c:otherwise>
        <a href="<c:url value="/products?page=0"/>">First</a>
        <c:forEach begin="1" end="2" var="page" varStatus="loop">
            <c:if test="${(pageOfElements.number-(loop.end-page))>=0}">
                <a href="<c:url value="/products?page=${pageOfElements.number-(loop.end-page)}"/>">
                    <c:out value="${pageOfElements.number-(loop.end-page)}"/>
                </a>
            </c:if>
        </c:forEach>
        <c:forEach begin="1" end="2" var="page">
            <c:if test="${(pageOfElements.number+page)<pageOfElements.totalPages}">
                <a href="<c:url value="/products?page=${pageOfElements.number+page}"/>">
                    <c:out value="${pageOfElements.number+page}"/>
                </a>
            </c:if>
        </c:forEach>
        <a href="<c:url value="/products?page=${pageOfElements.totalPages-1}"/>">Last</a>
    </c:otherwise>
</c:choose>
