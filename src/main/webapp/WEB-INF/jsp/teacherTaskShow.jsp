<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${task.name}</title>
    <jsp:include page="common.jsp"></jsp:include>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
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
                <h5 class="card-header">
                    <a href="/course/${task.course.id}">
                        ${task.course.name}
                    </a>
                </h5>
                <div class="card-body">
                    <p class="card-text"><b>Teacher</b>: ${task.course.teacher.name} ${task.course.teacher.surname}</p>
                </div>
            </div>

            <div class="card" style="margin-top: 20px">
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action active" id="list-tasks-list" data-toggle="list"
                       href="#tasks" role="tab" aria-controls="tasks">Tasks</a>
                    <a class="list-group-item list-group-item-action" id="list-desc-list" data-toggle="list"
                       href="#desc" role="tab" aria-controls="desc">Description</a>
                </div>
            </div>
        </div>

        <div class="col-lg-9 col-md-12">
            <div class="card">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade" id="desc" role="tabpanel"
                         aria-labelledby="list-desc-list">
                        <p class="card-header"><b>Task description</b></p>
                        <div class="card-body">
                            <p>${task.description}</p>
                        </div>
                    </div>

                    <div class="tab-pane fade show active" id="tasks" role="tabpanel" aria-labelledby="list-tasks-list">
                        <p class="card-header">
                            <b>${task.name}</b>
                        </p>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${not empty studentList}">
                                    <table class="table display table-condensed" id="myTable"
                                           style="border-collapse:collapse;">
                                        <thead>
                                        <tr>
                                            <th>Student name</th>
                                            <th>Mark</th>
                                            <th>Status</th>
                                            <th>Start date</th>
                                            <th>End date</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <c:forEach items="${studentList}" var="studentItem">
                                            <c:forEach items="${studentItem.studentTaskStatusSet}"
                                                       var="studentTaskStatus">
                                                <form:form method="post"
                                                           action="/${task.course.id}/task/${task.id}/check"
                                                           modelAttribute="studentTaskStatus">
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                           value="${_csrf.token}"/>
                                                    <c:if test="${task.id==studentTaskStatus.task.id}">
                                                        <tbody>
                                                        <tr data-toggle="collapse" data-target="#id${studentItem.id}"
                                                            class="accordion-toggle">
                                                            <td>${studentItem.name} ${studentItem.surname}</td>
                                                            <td>
                                                                <label>
                                                                    <input type="number"
                                                                           value="${studentTaskStatus.mark}"
                                                                           name="mark"
                                                                           placeholder="${studentTaskStatus.mark}"
                                                                           min="0"
                                                                           max="10">
                                                                </label>
                                                            </td>
                                                            <td><span
                                                                    class="badge badge-success">${studentTaskStatus.label}</span>
                                                            </td>
                                                            <td>${studentTaskStatus.startDate}</td>
                                                            <td>${studentTaskStatus.endDate}</td>
                                                            <td>
                                                                <button type="submit"
                                                                        class="badge badge-primary"
                                                                        name="user"
                                                                        value="${studentTaskStatus.student.username}">
                                                                    Check
                                                                </button>
                                                                <form:form method="get">
                                                                    <a href="/${task.course.id}/task/${task.id}/setReopen">
                                                                        <button type="button"
                                                                                class="badge badge-primary">
                                                                            Set reopen
                                                                        </button>
                                                                    </a>
                                                                </form:form>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="5" class="hiddenRow">
                                                                <div id="id${studentItem.id}"
                                                                     class="accordion-body collapse">
                                                                    <div class="card card-body">
                                                                        <p><b>Solution:</b></p>
                                                                        <c:choose>
                                                                            <c:when test="${!studentTaskStatus.url.equals('')}">
                                                                                <img src="${studentTaskStatus.url}"
                                                                                     alt="No image found" height="400"
                                                                                     width="500">
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <p>No solution yet.</p>
                                                                            </c:otherwise>

                                                                        </c:choose>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </c:if>
                                                </form:form>
                                            </c:forEach>
                                        </c:forEach>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-primary">
                                        There're no students=(
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
