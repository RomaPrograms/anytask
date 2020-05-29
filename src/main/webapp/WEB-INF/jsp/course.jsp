<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Anytask</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>
<body>
<div class="container mt-3">
    <div class="col-lg-6 col-md-9">
        <div class="card">
            <div class="card-header">
                <span><b>Course</b> ${course.name}</span>
            </div>
            <div class="card-body">
                <b>Teacher: </b> ${course.teacher.name} ${course.teacher.surname}
                <br>
                <b>Description:</b>
                <br>
                <span>${course.description}</span>
                <div style="padding-top: 10px">
                    <form:form method="get">
                        <a href="/course/${course.id}/join">
                            <button type="button" value="${course.id}"
                                    name="course" class="btn btn-primary">Join course
                            </button>
                        </a>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>