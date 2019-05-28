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
    <h1 class="flow-text"><fmt:message key="text.admin.page"/></h1>
    <div class="center-pill">

            <div class="row">
                <div class="col s12 m6">

                    <form method="post"
                          action="${pageContext.request.contextPath}/restaurant/admin/bill">
                        <input type="hidden">
                        <button class="btn" type="submit"><fmt:message key="text.create.bill"/></button>
                    </form>

                </div>
            </div>
        </div>

        <div class="row">
                <div class="col s12 m6">

                    <form method="post"
                          action="${pageContext.request.contextPath}/restaurant/admin/history">
                        <input type="hidden">
                        <button class="btn" type="submit"><fmt:message key="text.history"/></button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</html>
