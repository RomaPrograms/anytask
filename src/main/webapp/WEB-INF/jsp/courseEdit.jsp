<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit course</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>
<body>
<div class="container mt-3">
    <br>
    <form:form method="POST" modelAttribute="course" class="form-signin">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <h2 class="form-heading">Edit ${course.name} :</h2>
        <br>

        <spring:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="description" class="control-label col-xs-4">Course description:</label>
                <form:textarea cols="25" rows="4" path="description" class="form-control"
                               placeholder="Describe your course"></form:textarea>
                <form:errors path="description"></form:errors>
            </div>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit" value="submit">Submit</button>
    </form:form>
</div>
</body>
</html>