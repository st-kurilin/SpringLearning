<%@ attribute name="formSend" required="true" %>
<%@ attribute name="product" required="true" type="com.domain.shop.Product" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.ui.widget.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.ui.core.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.validate.js" />"></script>

<script>
    $(function() {

        $("#product").validate({
                rules:{
                  title:{
                      required: true,
                      remote:{
                          url:location.href.substring(0,location.href.lastIndexOf('products/'))+"products/isTitleAvailable",
                          type:"GET"
                      }
                  },
                  price:{
                      required:true,
                      number:true
                  }
                },
                messages:{
                   title:{
                       required: "Enter the title, please.",
                       remote:"This title of product is unavailable"
                   },
                   price:{
                         required: "Enter the price, please.",
                         number:"Price is not a number."
                    }
                }
            })
    });
</script>

<link rel="stylesheet" href="http://jqueryui.com/themes/base/jquery.ui.all.css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/validation_styles.css" />" media="all"/>

<sf:form method="POST" modelAttribute="product" action="${formSend}" enctype="multipart/form-data">
    <fieldset>
        <table>
            <tr>
                <td>Title:</td>
                <td><sf:input path="title" size="10"/></td>
                <td><sf:errors path="title" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><sf:input path="price" size="10"/></td>
                <td><sf:errors path="price" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan=3><input type="submit"/></td>
            </tr>
        </table>
    </fieldset>
</sf:form>