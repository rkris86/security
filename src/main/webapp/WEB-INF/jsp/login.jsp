<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page session="true" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body onload='document.loginForm.username.focus();'>


<div id="login-box">

    <h3>Login with Username and Password</h3>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form:form name='loginForm' modelAttribute="login" action="loginProcess"
           method='POST'>
        <table align="center">
            <tr>
                <td>
                    <form:label path="username">Username: </form:label>
                </td>
                <td>
                    <form:input path="username" name="username" id="username" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">Password:</form:label>
                </td>
                <td>
                    <form:password path="password" name="password" id="password" />
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
                    <img src="./resources/images/refresh.png" width="40" height="40"/>
                </a><form:input path="captcha" /></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">
                    <form:button id="login" name="login">Login</form:button>
                </td>
            </tr>
            <tr></tr>
            <tr>
                <td></td>
                <td><a href="welcome">Home</a>
                </td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

    </form:form>
</div>

</body>
</html>