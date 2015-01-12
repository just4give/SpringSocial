<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
  <title>Insert title here</title>
  <style type="text/css">
    .error-block {
      color: #ff0000;
      background-color: #ffeeee;
      border: 3px solid #ff0000;
      padding: 8px;
      margin: 16px;
    }
  </style>
  <script>
//    function login(){
//      alert("submitting form...");
//      return true;
//    }

  </script>
</head>
<body onload="document.f.email.focus();">
<h3>Fitness Tracker Custom Login</h3>
<c:if test="${not empty error }">
  <div class="error-block">Your login was unsuccessful <br>
    Caused: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
  </div>
</c:if>
<form action="j_spring_security_check" name="f" method="post">
  <table>
    <tr>
      <td>Username:</td>
      <td><input name="email" type="text" value=""/></td>
    </tr>
    <tr>
      <td>Password:</td>
      <td><input name="password" type="password" /></td>
    </tr>
    <tr>
      <td><input type="submit" value="Login" name="Submit" /></td>
    </tr>
  </table>

</form>

<!-- FACEBOOK SIGNIN -->
<p><a href="<c:url value="/auth/facebook" />">Login With Facebook</a>
</p>
</body>
</html>