<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add course</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>
<body>
<div class="container mt-3">
    <br>

    <form:form method="POST" modelAttribute="course">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <h2 class="form-heading">Create your course:</h2>
        <br>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="name" class="control-label col-xs-4">Course name:</label>
                <form:input type="text" path="name" class="form-control" placeholder="name"
                            autofocus="true" id="name"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="description" class="control-label col-xs-4">Course description:</label>
                <form:textarea cols="25" rows="4" path="description" class="form-control"
                               placeholder="Describe your course"></form:textarea>
                <form:errors path="description"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit" value="submit">Submit</button>
        <a href="/start">
            <button class="btn btn-lg btn-primary btn-block" type="button">Cancel</button>
        </a>
    </form:form>
</div>
</body>
</html>

