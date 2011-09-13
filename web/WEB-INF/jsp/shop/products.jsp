<%--
  User: andrii.loboda
  Date: 06.09.11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
  <%@ taglib uri="/WEB-INF/tags.tld"  prefix="tt" %>
<html>
<head><title>Simple jsp page</title></head>
<body>

<c:choose>
    <c:when test="${page.totalPages == 0}">
        There are no any products.
    </c:when>
    <c:when test="${page.number >= page.totalPages}">
        Please, return to <a href="<c:url value="/products" />">All products</a>
    </c:when>
    <c:otherwise>
        <table>
            <tr>

                <td><tags:changeDirection root="/products" sortBy="title"
                                          page="${page}">Title</tags:changeDirection></td>
                <td><tags:changeDirection root="/products" sortBy="price"
                                          page="${page}">Price</tags:changeDirection></td>
                <td><tags:changeDirection root="/products" sortBy="seller"
                                          page="${page}">Seller</tags:changeDirection></td>
                <td>Links</td>
            </tr>
            <c:forEach var="product" items="${page.content}">
                <tr>

                    <td><c:out value="${product.title}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td>
                        <a href="<c:url value="/users/${product.seller.id}"/>">
                            <c:out value="${product.seller.name}"/>
                        </a>
                    </td>
                    <td><a href="<c:url value="/products/${product.title}"/>">View</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <tt:pagingBar page="${page}" root="/products"/>
        <%--<tags:pagesView root="/products" page="${page}" range="3"/>--%>
    </c:otherwise>
</c:choose>
<br/>
<a href="<c:url value="/products/new" />"> Add new product</a>
</body>
</html>