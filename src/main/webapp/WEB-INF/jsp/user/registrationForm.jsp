<%--
  Created by IntelliJ IDEA.
  User: mithundas
  Date: 11/30/14
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
  <script type="text/javascript" src="/WEB-INF/js/user.form.js"></script>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/user/register" commandName="user" method="POST" enctype="utf8" role="form">
  <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
  <c:if test="${user.signInProvider != null}">
    <form:hidden path="signInProvider"/>
  </c:if>
  <div class="row">
    <div id="form-group-firstName" class="form-group col-lg-4">
      <label class="control-label" for="user-firstName">First Name:</label>
      <form:input id="user-firstName" path="firstName" cssClass="form-control"/>
      <form:errors id="error-firstName" path="firstName" cssClass="help-block"/>
    </div>
  </div>
  <div class="row">
    <div id="form-group-lastName" class="form-group col-lg-4">
      <label class="control-label" for="user-lastName">Last Name:</label>
      <form:input id="user-lastName" path="lastName" cssClass="form-control"/>
      <form:errors id="error-lastName" path="lastName" cssClass="help-block"/>
    </div>
  </div>
  <div class="row">
    <div id="form-group-email" class="form-group col-lg-4">
      <label class="control-label" for="user-email">Email:</label>
      <form:input id="user-email" path="email" cssClass="form-control"/>
      <form:errors id="error-email" path="email" cssClass="help-block"/>
    </div>
  </div>
  User Sign-In provider : ${user.signInProvider}
  <c:if test="${user.signInProvider == null}">
    <div class="row">
      <div id="form-group-password" class="form-group col-lg-4">
        <label class="control-label" for="user-password">Password:</label>
        <form:password id="user-password" path="password" cssClass="form-control"/>
        <form:errors id="error-password" path="password" cssClass="help-block"/>
      </div>
    </div>
    <div class="row">
      <div id="form-group-passwordVerification" class="form-group col-lg-4">
        <label class="control-label" for="user-passwordVerification">Password Verification:</label>
        <form:password id="user-passwordVerification" path="passwordVerification" cssClass="form-control"/>
        <form:errors id="error-passwordVerification" path="passwordVerification" cssClass="help-block"/>
      </div>
    </div>
  </c:if>
  <button type="submit" class="btn btn-default">Submit</button>
</form:form>

</body>
</html>
