<%--
  Created by IntelliJ IDEA.
  User: Ramakrishna Gurram
  Date: 8/16/2019
  Time: 8:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration</title>
</head>
<body>
<form:form id="regForm" modelAttribute="user" action="registerProcess" method="post">
    <table align="center">
        <tr>
            <td>
                <form:label path="name">Name</form:label>
            </td>
            <td>
                <form:input path="name" name="name" id="name"/>
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
            <td><input type="reset" value="reset" ></td>
            <td>
                <form:button id="register" name="register">Register</form:button>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="/welcome">Home</a>
            </td>
        </tr>
    </table>
</form:form>
<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
    </tr>
</table>
</body>
</html>
