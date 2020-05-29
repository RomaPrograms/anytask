<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>
<body>
<div class="container mt-3">
    <form:form method="POST" action="/login" class="form-signin">
    <h2 class="form-heading">Log in: </h2>

    <div class="form-group ${error != null ? 'has-error' : ''}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <span>${message}</span>
        <label for="username" class="control-label col-xs-4">Login:</label>
        <input name="username" type="text" class="form-control" placeholder="Login"
               id="username"/>
        <label for="password" class="control-label col-xs-4">Password:</label>
        <input name="password" type="password" class="form-control" placeholder="Password" id="password"/>
        <span>${error}</span>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
        <a href="/registration">
            <button class="btn btn-lg btn-primary btn-block" type="button">Create an account</button>
        </a>
    </div>
    </form:form>
</body>
</html>