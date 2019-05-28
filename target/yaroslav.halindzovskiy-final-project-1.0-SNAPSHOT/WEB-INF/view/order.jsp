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
</head>
<body>
<div class="container">
    <form method="post"  action="${pageContext.request.contextPath}/restaurant/client/order/create">

        <div>
            <input type="text" name="note" required placeholder="<fmt:message key="text.order.note"/>">
        </div>
        <table>
            <thead>
            <tr>

                <th><fmt:message key="text.dish.name"/></th>
                <th><fmt:message key="text.dish.price"/></th>
                <th></th>
                <br/>
            </tr>
            </thead>

            <tbody>

            <c:forEach items="${databaseList}" var="dish">
                <tr>

                    <c:choose>
                        <c:when test="${language == 'ua'}">
                            <td>${dish.getNameUa()}</td>
                        </c:when>
                        <c:otherwise>
                            <td> ${dish.getNameEn()} </td>
                        </c:otherwise>
                    </c:choose>
                    <td>${dish.getPrice()}</td>
                    <td>
                        <p>
                            <label>

                                <input type="checkbox" name="dish"  value="${dish.getId()}"/>
                            </label>
                        </p>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
        <input class="btn" type="submit" value="<fmt:message key="text.client.order"/>">

    </form>
</div>

</body>
</html>
