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
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<div class="container">
    <div class="input-field col s12" >
        <form method="post" action="${pageContext.request.contextPath}/restaurant/admin/bill/create">
        <select class="browser-default" name="subject">
            <option disabled><fmt:message key="text.create.bill"/></option>
            <c:forEach items="${orderList}" var="order">
                <option value="${order.getId()}">
                    ${order.getId()}
                        ${order.getNote()}
                </option>
            </c:forEach>

        </select>
            <div>
            <input class="btn"type="submit" value="<fmt:message key="text.create.bill"/>">
            </div>
        </form>
    </div>

    </div>
</div>
</body>
<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</html>
