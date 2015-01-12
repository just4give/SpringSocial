<%--
  Created by IntelliJ IDEA.
  User: mithundas
  Date: 12/2/14
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title></title>
  <jsp:include page="../head.jsp"/>
</head>
<body class="container">
<h2>Your LinkedIn Profile</h2>
<sec:authentication property="principal.socialSignInProvider" var="provider"/>



<c:choose>
  <c:when test="${provider == 'FACEBOOK'}">
    <sec:authorize access="isAuthenticated()">

      <div>${profileName}</div>
      <div><img src="${profilePicture}"></div>
      <form action="<c:url value="/connect/facebook/share" />" method="post" >
        <input type="text" name="status-msg" id="shareMessage"/>
        <button type="submit" class="btn btn-warning" id="btnShare">Share</button>
      </form>

    </sec:authorize>
  </c:when>
  <c:when test="${provider == null}">
    <sec:authorize access="isAuthenticated()">

      <a href="${pageContext.request.contextPath}/auth/facebook"><button class="btn btn-facebook">
        <i class="fa fa-facebook"></i> | <spring:message code="label.facebook.sign.in.button"/>
      </button>
      </a>
    </sec:authorize>
  </c:when>


</c:choose>
</body>
</html>

