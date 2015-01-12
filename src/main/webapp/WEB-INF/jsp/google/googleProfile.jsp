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
<h2>Your Google+ Profile</h2>
<sec:authentication property="principal.socialSignInProvider" var="provider"/>



<c:choose>
  <c:when test="${provider == 'GOOGLE'}">
    <sec:authorize access="isAuthenticated()">

      <div>${profileName}</div>
      <div><img src="${profilePicture}"></div>
      <form action="<c:url value="/connect/google/share" />" method="post" >
        <input type="text" name="status-msg" id="shareMessage"/>
        <button type="submit" class="btn btn-primary" id="btnShare">Share</button>
      </form>

    </sec:authorize>
  </c:when>
  <c:when test="${provider == null}">
    <sec:authorize access="isAuthenticated()">

      <form action="${pageContext.request.contextPath}/auth/google" method="post">
        <button type="submit" class="btn btn-google-plus"><i class="fa fa-google-plus"></i> | <spring:message code="label.google.sign.in.button"/>
        </button>
        <input type="hidden" name="scope" value="email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/tasks https://www.googleapis.com/auth/plus.stream.write https://www.googleapis.com/auth/plus.stream.read" />
      </form>
    </sec:authorize>
  </c:when>


</c:choose>
</body>
</html>
