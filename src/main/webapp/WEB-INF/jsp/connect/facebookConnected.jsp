<%--
  Created by IntelliJ IDEA.
  User: mithundas
  Date: 11/27/14
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"
          type="text/javascript"></script>
</head>
<body>
<a href="<c:url value='/j_spring_security_logout'/>">Logout</a>



<p>Now you are connected to Facebook, you can ask the following thinks:</p>
<ul>
  <li><a href="<c:url value="/connect/facebook/friends" />" >Friends</a></li>
  <li><a href="<c:url value="/connect/facebook/profile" />" >My Profile</a></li>
</ul>


<form action="<c:url value="/connect/facebook" />" method="post">
    <div class="formInfo">
        <p>
            Spring Social Showcase is connected to your Facebook account.
            Click the button if you wish to disconnect.
        </p>
    </div>
    <button type="submit">Disconnect</button>
    <input type="hidden" name="_method" value="delete" />
</form>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        User : ${pageContext.request.userPrincipal.name} | <a
            href="javascript:formSubmit()"> Logout</a>
    </h2>
</c:if>
<h2><sec:authentication property="principal.firstName"/>
    <sec:authentication property="principal.lastName"/>
</h2>
</body>
</html>
