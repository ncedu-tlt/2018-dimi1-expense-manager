<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Expense maneger</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"></link>
    <link href="/css/report.css" rel="stylesheet" type="text/css"/></link>

    <script src="https://code.jquery.com/jquery-3.3.1.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <script src="/js/pieModal.js" rel="stylesheet" type="text/javascript"></script>
    <script src="/js/report2.js" rel="stylesheet" type="text/javascript"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/canvasjs/1.7.0/canvasjs.min.js"></script>

    <div class="modal fade" id="Required" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Required expenses</h5>
                </div>
                <div class="modal-body">
                    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center" style="display:none;">
                        <tr>
                            <td>ID</td><td>NAME</td><td>REQUIRED</td><td>SUMMA</td>
                        </tr>
                        <c:forEach items="${report2req}" var="rep">
                            <tr>
                                <td >${rep.getId()}</td>
                                <td id="nameReq">${rep.getName()}</td>
                                <td>${rep.getRequired()}</td>
                                <td id="sumReq">${rep.getSum()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="hidden" class="form-control" name="sumRequired" id="sumRequired" value="${totalSumReq}">
                    <h4 align="center">TOTAL SUM: <c:out value="${totalSumReq}"></c:out></h4>
                    <div class="test" id="chartRequired"></div>
                </div>
                <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="Unrequired" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Unrequired expenses</h5>
                </div>
                <div class="modal-body">
                    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center" style="display:none;">
                        <tr>
                            <td>ID</td><td>NAME</td><td>REQUIRED</td><td>SUMMA</td>
                        </tr>
                        <c:forEach items="${report2unreq}" var="rep">
                            <tr>
                                <td>${rep.getId()}</td>
                                <td id="nameUnr">${rep.getName()}</td>
                                <td>${rep.getRequired()}</td>
                                <td id="sumUnr">${rep.getSum()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="hidden" class="form-control" name="sumUnrequired" id="sumUnrequired" value="${totalSumUnreq}">
                    <h4 align="center">TOTAL SUM: <c:out value="${totalSumUnreq}"></c:out></h4>
                    <div class="test" id="chartUnrequired"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</head>
<body>
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow"></nav>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand text-nowrap" href="">Manager</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/accounts?showModal" id="btnModal">Purse</a>
                </li>
            </ul>
        </div>
        <ul class="navbar-nav sing-right">
            <li class="nav-item text-nowrap">
                <a class="nav-link " href="/logout">Sign out</a>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </li>
        </ul>
    </nav>
    <br>
    <h3 align="center">Required expenses</h3>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>ID</td><td>NAME</td><td>REQUIRED</td><td>SUMMA</td>
        </tr>
        <c:forEach items="${report2req}" var="rep">
            <tr>
                <td>${rep.getId()}</td>
                <td>${rep.getName()}</td>
                <td>${rep.getRequired()}</td>
                <td>${rep.getSum()}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <input type="hidden" class="form-control" name="sumRequired" id="sumRequired" value="${totalSumReq}">
    <h4 align="center">TOTAL SUM: <c:out value="${totalSumReq}"></c:out></h4>
    <h3 align="center">Unrequired expenses</h3>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>ID</td><td>NAME</td><td>REQUIRED</td><td>SUMMA</td>
        </tr>
        <c:forEach items="${report2unreq}" var="rep">
            <tr>
                <td>${rep.getId()}</td>
                <td>${rep.getName()}</td>
                <td>${rep.getRequired()}</td>
                <td>${rep.getSum()}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" class="form-control" name="sumUnrequired" id="sumUnrequired" value="${totalSumUnreq}">
    <h4 align="center">TOTAL SUM: <c:out value="${totalSumUnreq}"></c:out></h4>
    <div id="chartCommon" style="height: 450px; width: 100%;">
</body>
</html>