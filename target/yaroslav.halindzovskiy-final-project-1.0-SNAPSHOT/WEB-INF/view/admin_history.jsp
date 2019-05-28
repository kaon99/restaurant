<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><fmt:message key="text.title"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<div class="container">
    <table>
        <thead>

            <th><fmt:message key="text.id"></fmt:message></th>
            <th><fmt:message key="text.note"></fmt:message></th>
        <br/>
        </tr>
        </thead>
        <tbody>
        <tr>
        <c:forEach items="${orders}" var="order">
           <td>${order.getId()}</td>
           <td>${order.getNote()}</td>
        </tr>
        </c:forEach>

    </tbody>
    </table>
    <jsp:include page="pagintaion.jsp"/>

</div>


</body>
<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</html>
