<%--
  Created by IntelliJ IDEA.
  User: mithundas
  Date: 11/27/14
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h2>Connect to Facebook</h2>
<form action="<c:url value="/connect/facebook" />" method="POST">
<div class="formInfo">
  <input type="hidden" name="scope" value="read_stream,publish_actions,user_friends" />
  <p>Click the button to connect your account with your Facebook account.</p>
</div>
<input id="signin" type="submit"/>
</form>

</body>
</html>
