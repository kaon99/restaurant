<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 02.04.2019
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="text.title"/></title></head>
</head>
<body>
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<div class="container">
    <form  action="${pageContext.request.contextPath}/restaurant/registration/create" align="center"  method="post">
        <p>
            <label>
                <input type="text" pattern="^[A-Z][a-z]{1,20}$" required placeholder="<fmt:message key="text.name"/>" name="name"/>
            </label>
        </p>
        <p>
            <label>
                <input  type="email" required placeholder="<fmt:message key="text.email"/>" name="email"/>
            </label>
        </p>
        <p>
            <label>
                <input  type="password" required placeholder="<fmt:message key="text.password"/>" name="password"/>
            </label>
        </p>
        <div>
            <input   type="submit" class="btn"   value="<fmt:message key="text.registration"/>">
            <c:if test="${requestScope.registrationError}">
            <div >
                <fmt:message key="text.registration.error"/>
            </div>
            </c:if>
            <c:if test="${requestScope.userExist}">
            <div >
                <fmt:message key="text.user.exist"/>
            </div>
            </c:if>

    </form>
</div>
</div>
<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</body>
</html>
