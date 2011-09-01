<%@ attribute name="formSend" required="true" %>
<%@ attribute name="user" required="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.ui.datepicker.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.ui.widget.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.ui.core.js"/>"></script>

<link rel="stylesheet" href="http://jqueryui.com/themes/base/jquery.ui.all.css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/calendar_styles.css" />" media="all"/>
<link rel="stylesheet" href="http://hotlink.jquery.com/jqueryui/themes/base/jquery.ui.base.css"/>
<link rel="stylesheet" href="http://hotlink.jquery.com/jqueryui/themes/base/jquery.ui.theme.css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/calendar_styles.css" />" media="all"/>

<script src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" />"></script>
    <script>
        $(function() {
            $( "#birthday" ).datepicker();
        });
    </script>
