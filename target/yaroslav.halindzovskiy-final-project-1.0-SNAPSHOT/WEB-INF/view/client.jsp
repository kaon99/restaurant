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
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="${language}">
<head>
    <jsp:include page="/WEB-INF/parts/header.jsp"/>
    <title><fmt:message key="text.title"/></title>
    <style>
        .mb15{
            margin: 0 0 15px;
        }
        .row{
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>
<div class="container" >
    <div class="row">
        <div class="col s12 m6">
            <h1 class="flow-text mb15"><fmt:message key="text.client"/></h1>
            <form method="post"
                  action="${pageContext.request.contextPath}/restaurant/client/order">
                <input type="hidden">
                <button class="btn" type="submit"><fmt:message key="text.client.order"/></button>
            </form>

            <form method="post"
                  action="${pageContext.request.contextPath}/restaurant/client/bill">
                <input type="hidden">
                <button class="btn" type="submit"><fmt:message key="text.client.bill"/></button>
            </form>
        </div>
        <div class="col s12 m6">
            <div class="card blue-grey darken-1">
                <div class="card-content white-text">
                    <div>
                                <fmt:message key="text.name"/>
                                <c:out value="${name}"/>
                                <br>
                        <br>
                        <fmt:message key="text.email"/>
                        <c:out value="${email}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</body>
</html>
