<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link href="..\css\common.css" rel="stylesheet">
</head>
<body style="background-color: #d6e0f5">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="/start"><h5 style="color: #8c8c8c">Anytask</h5></a>
        <ul class="nav navbar-nav">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">

                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <div class="navbar-text mr-3"> ${pageContext.request.userPrincipal.name}</div>
                    <%--                    <li style="float:right" class="nav-item">--%>
                    <%--                        <a class="nav-link" onclick="document.forms['logoutForm'].submit()" style="color: #8c8c8c">Log--%>
                    <%--                            out</a>--%>
                    <%--                    </li>--%>
                    <li>
                        <div class="pos-f-t">
                            <div class="btn-group" role="group">
                                <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                    <a class="dropdown-item" style="color: #8c8c8c" href="/profile">Profile</a>
                                    <a class="dropdown-item" style="color: #8c8c8c" href="/course/courseCreate">Create
                                        course</a>
                                    <a class="dropdown-item" style="color: #8c8c8c" href="/start">Join course</a>
                                    <a class="dropdown-item" onclick="document.forms['logoutForm'].submit()"
                                       style="color: #8c8c8c">Log out</a>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li style="float:right" class="nav-item">
                        <a class="nav-link" href="/login" style="color: #8c8c8c">Sign in</a>
                    </li>
                    <li style="float:right" class="nav-item">
                        <a class="nav-link" href="/registration" style="color: #8c8c8c">Sign up</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="../js/bootstable.js"></script>
<script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script>
    $(document).ready(function () {
        $('#mySecondTable').DataTable();
    });

    $(document).ready(function () {
        $('#myTeacherTable').DataTable();
    });

    // $('mySecondTable').SetEditable({
    //     columnsEd: "0,1"
    // });
    //
    // $('myTeacherTable').SetEditable({
    //     columnsEd: "0,1"
    // });
</script>

</body>
</html>
