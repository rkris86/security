<%--
  Created by IntelliJ IDEA.
  User: 433142
  Date: 8/20/2019
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
<h3> </h3><br>
<h3>Admin Page</h3>
</div>
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
