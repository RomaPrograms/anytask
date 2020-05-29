<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<head>
    <title>Sign up</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>

<body>
<div class="container mt-3">

    <form:form method="POST" modelAttribute="user" action="/registration">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <h2 class="form-signin-heading">Create your account:</h2>

        <spring:bind path="name">
            <div class="form-group row" ${status.error? 'has-error': ''}>
                <label for="name" class="col-sm-2 col-form-label">Name:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="name" placeholder="Name"
                                autofocus="true" class="form-control"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="surname">
            <div class="form-group row" ${status.error? 'has-error': ''}>
                <label for="surname" class="col-sm-2 col-form-label">Surname:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="surname" placeholder="Surname"
                                autofocus="true" class="form-control"></form:input>
                    <form:errors path="surname"></form:errors>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="username">
            <div class="form-group row"  ${status.error? 'has-error': ''}>
                <label for="username" class="col-sm-2 col-form-label">Login:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="username" placeholder="Login"
                                autofocus="true" class="form-control"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </div>
        </spring:bind>

        <fieldset class="form-group">
            <div class="row">
                <label class="col-sm-2 col-form-label">Sign up as: </label>
                    <%--                <legend class="col-form-label col-sm-2 pt-0">Sign up as</legend>--%>
                <div class="col-sm-10">
                    <div class="form-check">
                        <form:radiobutton path="roles" value="${student}" cssClass="form-check-input"/>Student
                    </div>
                    <div class="form-check">
                        <form:radiobutton path="roles" value="${teacher}" cssClass="form-check-input"/>Teacher
                    </div>

                </div>
            </div>
        </fieldset>

        <spring:bind path="password">
            <div class="form-group row"  ${status.error? 'has-error': ''}>
                <label for="password" class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-10">
                    <form:input type="password" path="password" placeholder="Password"
                                class="form-control"></form:input>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group row"  ${status.error? 'has-error': ''}>
                <label for="passwordConfirm" class="col-sm-2 col-form-label">Confirm password:</label>
                <div class="col-sm-10">
                    <form:input type="password" path="passwordConfirm"
                                placeholder="Confirm your password" class="form-control"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </div>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    </form:form>
</div>
</body>
</html>