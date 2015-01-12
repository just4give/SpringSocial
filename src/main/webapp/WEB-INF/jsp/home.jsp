<%--
  Created by IntelliJ IDEA.
  User: mithundas
  Date: 11/27/14
  Time: 5:06 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <jsp:include page="head.jsp"/>
  <script type="text/javascript">
//    $(document).ready(function(){
//      $("#btnShare").click(function(){
//        var data = $("#shareMessage").val();
//        alert(location.host);
//        $.ajax({
//          url: location.host+'SpringSocial/connect/facebook/share',
//          type: 'post',
//          data: {param:data},
//          success: function (msg) {
//            alert('success');
//          },
//          error: function(err, errorThrown){
//            alert('Error:'+err.responseText+" "+errorThrown);
//          }
//
//        });
//      });
//    });

  </script>
</head>

<body class="container">
<h2>Spring Social Tutorial</h2>
<%--<a href="<c:url value="/connect/facebook" />">Connect to Facebook</a>--%>
<h3><sec:authentication property="principal.firstName"/>
  <sec:authentication property="principal.lastName"/><br>
  <sec:authentication property="principal.username"/>
  <sec:authentication property="principal.socialSignInProvider" var="provider"/>

</h3>

<p>Now you are connected to Facebook, you can ask the following thinks:</p>
<ul>

  <li><a href="<c:url value="/connect/facebook/profile" />" >Facebook Profile</a></li>
  <li><a href="<c:url value="/connect/linkedin/profile" />" >LinkedIn Profile</a></li>
  <li><a href="<c:url value="/connect/google/profile" />" >Google+ Profile</a></li>
</ul>

<%--<form action="<c:url value="/connect/facebook" />" method="post">--%>
  <%--<div class="formInfo">--%>
    <%--<p>--%>
      <%--Spring Social Showcase is connected to your Facebook account.--%>
      <%--Click the button if you wish to disconnect.--%>
    <%--</p>--%>
  <%--</div>--%>
  <%--<button type="submit">Disconnect</button>--%>
  <%--<input type="hidden" name="_method" value="delete" />--%>
<%--</form>--%>





<%--<c:choose>--%>
<%--<c:when test="${provider != null}">--%>
  <%--<sec:authorize access="isAuthenticated()">--%>

    <%--<form action="<c:url value="/connect/facebook/share" />" method="post" >--%>
      <%--<input type="text" name="status-msg" id="shareMessage"/>--%>
      <%--<button type="submit" class="btn btn-primary" id="btnShare">Share</button>--%>
    <%--</form>--%>

  <%--</sec:authorize>--%>
<%--</c:when>--%>
<%--<c:when test="${provider == null}">--%>
    <%--<sec:authorize access="isAuthenticated()">--%>

      <%--<a href="${pageContext.request.contextPath}/auth/facebook"><button class="btn btn-facebook">--%>
        <%--<i class="fa fa-facebook"></i> | <spring:message code="label.facebook.sign.in.button"/>--%>
      <%--</button>--%>
      <%--</a>--%>
    <%--</sec:authorize>--%>
  <%--</c:when>--%>
<%--</c:choose>--%>


<sec:authorize access="isAuthenticated()">

  <form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-default navbar-btn">
      <spring:message code="label.navigation.logout.link"/>
    </button>
    <input type="hidden" name="_method" value="delete" />
  </form>

</sec:authorize>

</body>
</html>
