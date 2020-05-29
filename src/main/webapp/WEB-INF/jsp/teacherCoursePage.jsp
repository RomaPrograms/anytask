<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${course.name}</title>
    <jsp:include page="common.jsp"></jsp:include>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <style>
        .table tr {
            cursor: pointer;
        }

        .hiddenRow {
            padding: 0 4px !important;
            background-color: #eeeeee;
            font-size: 13px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row mt-3">
        <div class="col-lg-3 col-md-12">
            <div class="card">
                <h5 class="card-header">${course.name}</h5>
                <div class="card-body">
                    <p class="card-text"><b>Teacher</b>: ${course.teacher.name} ${course.teacher.surname}</p>
                </div>
            </div>

            <div class="card" style="margin-top: 20px">
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action active" id="list-tasks-list" data-toggle="list"
                       href="#tasks" role="tab" aria-controls="tasks">Tasks</a>
                    <a class="list-group-item list-group-item-action" id="list-desc-list" data-toggle="list"
                       href="#desc" role="tab" aria-controls="desc">Description</a>
                    <a class="list-group-item list-group-item-action" id="list-inProgress-list" data-toggle="list"
                       href="#inProgress" role="tab" aria-controls="desc">Tasks in progress</a>
                    <a class="list-group-item list-group-item-action" id="list-ready-list" data-toggle="list"
                       href="#readyForReview" role="tab" aria-controls="ready">Tasks ready for checking</a>
                </div>
            </div>
        </div>

        <div class="col-lg-9 col-md-12">
            <div class="card">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="tasks" role="tabpanel" aria-labelledby="list-tasks-list">
                        <p class="card-header">
                            <b>Tasks </b><a href="/${course.id}/task/taskAdd"
                                            class="text-decoration-none md-2"> Create task</a>
                        </p>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${not empty taskList}">
                                    <c:forEach items="${taskList}" var="studentItem">
                                        <table class="table display table-condensed" id="myTable"
                                               style="border-collapse:collapse;">
                                            <thead>
                                            <tr>
                                                <th>Course name</th>
                                                <th>Due date</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr data-toggle="collapse" data-target="#id${studentItem.id}"
                                                class="accordion-toggle">
                                                <td>
                                                    <a href="/${studentItem.course.id}/task/${studentItem.id}">
                                                            ${studentItem.name}
                                                    </a>
                                                </td>
                                                <td>${studentItem.dueDate}</td>
                                                <td>
                                                    <form:form method="get">
                                                    <a href="/${course.id}/task/${studentItem.id}/delete">
                                                        <button type="button" value="${studentItem.id}"
                                                                name="course" class="badge badge-primary">Delete
                                                            task
                                                        </button>
                                                    </a>
                                                    <a href="/${course.id}/task/${studentItem.id}/edit">
                                                        <button type="button" class="badge badge-primary">Edit
                                                        </button>
                                                        </form:form>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="5" class="hiddenRow">
                                                    <div id="id${studentItem.id}" class="accordion-body collapse">
                                                        <div class="card card-body">
                                                            <p>${studentItem.description}</p>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-primary">
                                        There're no tasks=(
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="desc" role="tabpanel"
                         aria-labelledby="list-desc-list">
                        <p class="card-header"><b>Description</b></p>
                        <div class="card-body">
                            <p>${course.description}</p>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="inProgress" role="tabpanel"
                         aria-labelledby="list-inProgress-list">
                        <p class="card-header"><b>Tasks in progress</b></p>
                        <div class="card-body">

                            <c:choose>
                                <c:when test="${not empty inProgressTasks}">
                                    <c:forEach items="${inProgressTasks}" var="inProgressTaskItem">
                                        <table class="table display table-condensed"
                                               style="border-collapse:collapse;">
                                            <thead>
                                            <tr>
                                                <th>Student name</th>
                                                <th>Task name</th>
                                                <th>Start date</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>${inProgressTaskItem.student.name} ${inProgressTaskItem.student.surname}</td>
                                                <td>${inProgressTaskItem.task.name}</td>
                                                <td>${inProgressTaskItem.startDate}</td>
                                            </tr>
                                            </tbody>
                                        </table>

                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-primary">
                                        There're no tasks or students dont't work=(
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="readyForReview" role="tabpanel"
                         aria-labelledby="list-inProgress-list">
                        <p class="card-header"><b>Ready for checking tasks</b></p>
                        <div class="card-body">

                            <c:choose>
                                <c:when test="${not empty readyForReviewTasks}">
                                    <c:forEach items="${readyForReviewTasks}" var="readyForReviewTaskItem">
                                        <table class="table display table-condensed"
                                               style="border-collapse:collapse;">
                                            <thead>
                                            <tr>
                                                <th>Student name</th>
                                                <th>Task name</th>
                                                <th>Start date</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>${readyForReviewTaskItem.student.name} ${readyForReviewTaskItem.student.surname}</td>
                                                <td>${readyForReviewTaskItem.task.name}</td>
                                                <td>${readyForReviewTaskItem.startDate}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-primary">
                                        There're no tasks or students dont't work=(
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('.accordian-body').on('show.bs.collapse', function () {
        $(this).closest("table")
            .find(".collapse.in")
            .not(this)
            .collapse('toggle')
    });
</script>
</body>
</html>
