<%--
  Created by IntelliJ IDEA.
  User: Ramakrishna gurram
  Date: 8/20/2019
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div align="center">
    <h3>User Update Page</h3>
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
</div>
<form:form id="regForm" modelAttribute="user" action="updateAccount" method="post">
    <table align="center">
        <tr>
            <td>
                <form:label path="name">Name</form:label>
            </td>
            <td>
                <form:input path="name" name="name" id="name" value="${user.name}"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">Email</form:label>
            </td>
            <td>
                <form:input path="email" name="email" id="email"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="username">Username</form:label>
            </td>
            <td>
                <form:input path="username" name="username" id="username"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">Password</form:label>
            </td>
            <td>
                <form:password path="password" name="password" id="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <img id="captcha_id" name="imgCaptcha" src="captcha.jpg">
                </div>
            </td>

            <td align="left"><a href="javascript:;"
                                title="refresh captcha Image"
                                onclick="document.getElementById('captcha_id').src = 'captcha.jpg?' + Math.random();  return false">
                <img src="./resources/images/refresh.png" width="35" height="35"/>
            </a>    <form:input path="captcha" placeholder="Enter Captcha"/></td>
        </tr>
        <tr>

            <td>
                <form:button id="update" name="update">Update</form:button>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="welcome">Home</a>
            </td>
        </tr>
    </table>
</form:form>
<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
    </tr>
</table>


