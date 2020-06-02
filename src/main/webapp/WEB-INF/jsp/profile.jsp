<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>
<body>
<div class="container mt-3">
    <div class="card">
        <div class="card-header">
            <h4>User</h4>
        </div>
        <div class="card-body">
            <h3>${user.name} ${user.surname}</h3>
            <h5>Login: ${user.username}</h5>
        </div>
    </div>

    <div class="row" style="padding-top: 10px; padding-bottom: 20px">
        <sec:authorize access="hasAuthority('TEACHER')">
            <div class="col-lg-6 col-md-12">
                <div class="card">
                    <p class="card-header">Teacher's courses</p>
                    <table class="table" id="myTeacherTable" style="border-collapse:collapse;">
                        <thead>
                        <tr>
                            <th>Course name</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty teacherList}">
                                <c:forEach items="${teacherList}" var="listItem">
                                    <tr>
                                        <td><a href="/course/${listItem.id}">${listItem.name}</a></td>
                                        <td>${listItem.description}</td>
                                        <td>
                                            <form:form method="get">
                                                <a href="/course/${listItem.id}/delete">
                                                    <button type="button" value="${listItem.id}"
                                                            name="course" class="badge badge-primary">Delete
                                                        course
                                                    </button>
                                                </a>
                                                <a href="/course/${listItem.id}/edit">
                                                    <button type="button" class="badge badge-primary">Edit
                                                    </button>
                                                </a>
                                            </form:form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <%--                <c:otherwise>--%>
                            <%--                    <div class="alert alert-primary">--%>
                            <%--                        You're not a student of any course. Would you like to join one?--%>
                            <%--                        <br>--%>
                            <%--                        <a href="/start">--%>
                            <%--                            <button type="button" name="addCourseStart" value="AddCourseStart"--%>
                            <%--                                    class="btn btn-primary  btn-md mt-2">Join--%>
                            <%--                            </button>--%>
                            <%--                        </a>--%>
                            <%--                    </div>--%>
                            <%--                </c:otherwise>--%>

                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </sec:authorize>
        <sec:authorize access="hasAuthority('STUDENT')">
            <div class="col-lg-6 col-md-12">
                <div class="card">
                    <p class="card-header">Student's courses</p>
                    <table class="table" id="mySecondTable" style="border-collapse:collapse;">
                        <thead>
                        <tr>
                            <th>Course name</th>
                            <th>Teacher</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty studentList}">
                                <c:forEach items="${studentList}" var="listItem">
                                    <tr>
                                        <td>
                                            <a href="/course/${listItem.id}">${listItem.name}</a></td>
                                        <td>${listItem.teacher.name} ${listItem.teacher.surname}</td>
                                        <td>
                                            <a href="/course/${listItem.id}/leave">
                                                <button type="button" value="${listItem.id}"
                                                        name="course" class="badge badge-primary">Leave course
                                                </button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <%--                <c:otherwise>--%>
                            <%--                    <div class="alert alert-primary">--%>
                            <%--                        You're not a student of any course. Would you like to join one?--%>
                            <%--                        <br>--%>
                            <%--                        <a href="/start">--%>
                            <%--                            <button type="button" name="addCourseStart" value="AddCourseStart"--%>
                            <%--                                    class="btn btn-primary  btn-md mt-2">Join--%>
                            <%--                            </button>--%>
                            <%--                        </a>--%>
                            <%--                    </div>--%>
                            <%--                </c:otherwise>--%>

                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </sec:authorize>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('#myTeacherTable').Tabledit({
            url: '/${task.course.id}/task/${task.id}/check',
            editButton: false,
            deleteButton: false,
            hideIdentifier: true,
            columns: {
                editable: [1, 'Description']
            }
        });
    });
</script>
</body>
</html>
