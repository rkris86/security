<%--
  Created by IntelliJ IDEA.
  User: 433142
  Date: 8/20/2019
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="hasRole('ROLE_USER')">
<a href="${pageContext.request.contextPath}/userPage"> User</a> |
<a href="${pageContext.request.contextPath}/adminPage">Admin</a> |
<a href="javascript:document.getElementById('logout').submit()">Logout</a>

<h3>Welcome to New Menu </h3>
<ul>
    <li>Java 8 tutorial</li>
    <li>Spring tutorial</li>
    <li>Gradle tutorial</li>
    <li>BigData tutorial</li>
</ul>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</sec:authorize>
<a href="welcome"> Home</a>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if>