<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true" %>
<%@ attribute name="root" required="true" %>
<%@ attribute name="range" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:choose>
    <c:when test="${page.firstPage}">
        First
        <c:out value="${page.number}"/>
        <tags:offsetPages page="${page}" root="${root}" asc="true" range="${range}"/>
    </c:when>

    <c:when test="${page.lastPage}">
        <tags:offsetPages page="${page}" root="${root}" asc="false" range="${range}"/>
        <c:out value="${page.number}"/>
        Last
    </c:when>
    <c:otherwise>
        <tags:offsetPages page="${page}" root="${root}" asc="false" range="${range}"/>
        <c:out value="${page.number}"/>
        <tags:offsetPages page="${page}" root="${root}" asc="true" range="${range}"/>
    </c:otherwise>
</c:choose>
