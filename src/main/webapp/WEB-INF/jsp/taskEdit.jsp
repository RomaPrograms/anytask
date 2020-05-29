<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit task</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>
<body>
<div class="container mt-3">
    <form:form method="POST" modelAttribute="task" class="form-signin">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="course" value="${course}">
        <form:hidden path="course" value="${course.id}"></form:hidden>

        <h2 class="form-signin-heading">Edit ${task.name}:</h2>

        <spring:bind path="dueDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="dueDate" class="control-label col-xs-4">Due date:</label>
                <form:input type="date" path="dueDate" class="form-control" placeholder="dd/mm/yyyy"
                            id="dueDate"></form:input>
                <form:errors path="dueDate"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="description" class="control-label col-xs-4">Task description:</label>
                <form:textarea cols="25" rows="4" path="description" class="form-control"
                               placeholder="Describe task"></form:textarea>
                <form:errors path="description"></form:errors>
            </div>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit" value="submit">Submit</button>
    </form:form>
</div>
</body>
</html>
