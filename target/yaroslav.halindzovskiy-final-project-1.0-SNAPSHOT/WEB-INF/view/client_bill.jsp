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
    <div class="input-field col s12">
        <form method="post" action="${pageContext.request.contextPath}/restaurant/client/bill/pay">
            <select class="browser-default" name="bill">
                <option disabled><fmt:message key="text.pay.bill"/></option>
                <c:forEach items="${billList}" var="bill">
                    <option value="${bill.getId()}">
                        <fmt:message key="text.id"/>
                            ${bill.getId()}
                        <fmt:message key="text.date"/>
                            ${bill.getDate()}
                        <fmt:message key="text.sum"/>
                            ${bill.getSum()}

                    </option>
                </c:forEach>

            </select>
            <div>
                <input class="btn" type="submit" value="<fmt:message key="text.pay.bill"/>">
            </div>
        </form>
    </div>

</div>
</div>
</body>
<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</html>