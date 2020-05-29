<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Anytask</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <form:form method="get">
        <c:choose>
            <c:when test="${not empty courseList}">
                <div class="row mt-3" style="margin-bottom: 10px">
                    <c:forEach items="${courseList}" var="studentItem">
                        <div class="col-sm-5" style="margin-bottom: 20px">
                            <div class="card border-light">
                                <h5 class="card-header">${studentItem.name}</h5>
                                <div class="card-body">
                                    <p class="card-text">
                                        Teacher: ${studentItem.teacher.name} ${studentItem.teacher.surname}</p>
                                    <a href="/course/${studentItem.id}">
                                        <button type="button" value="${studentItem.id}"
                                                name="course" class="btn btn-primary">Show course page
                                        </button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-primary">
                    There's no courses. Would you like adding new one?
                    <br>
                    <a href="/course/courseCreate">
                        <button type="button" name="addCourseStart" value="AddCourseStart"
                                class="btn btn-primary  btn-md">Add
                            course
                        </button>
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </form:form>
</div>
</body>
</html>
