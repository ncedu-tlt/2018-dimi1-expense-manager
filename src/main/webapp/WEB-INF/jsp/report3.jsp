<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Expense maneger</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"></link>
    <link href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/css/bootstrap.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css" />
    <link href="/css/report3.css" rel="stylesheet" type="text/css"/></link>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <script src="/js/report3.js" rel="stylesheet" type="text/javascript"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand text-nowrap" href="">Manager</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
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

    <p><em><h2 align="center">Report 3</h2></em></p>
    <br>

    <form name="dateForm" action="/showReport3/${accountId}" method="post">
        <div class="container">
            <div class="row" >
                <div class='col-sm-6' style="max-width: 300px;margin-right: auto;margin-left: auto;">
                    <div class="form-group" style="width: 300px;" >
                        <div class='input-group date' id='datetimepicker'>
                            <input type='text' required pattern="#[0-9]{2})\/([0-9]{2})\/([0-9]{4}" class="form-control"  name="addDate" id="addDate" />
                            <span class="input-group-addon" style="border-radius: 0 4px 4px 0px;">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                            <input type="hidden" class="form-control" id="${accountId}" name="accountId" value="${accountId}"/>
                            <button  type="submit" class="btn btn-success" style="vertical-align: 12.1px;margin-left: 3px;" data-toggle="tooltip" data-placement="left" title="Tooltip on left">Click</button>
                        </div>
                     </div>
                 </div>
            </div>
        </div>
    </form>
    <br>
    <h2 align="center">Planned expenses - Table</h2>
    <br>
    <div class="container">
        <div class="container" style="width: 800px;">
            <table class="table demotable">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Date</th>
                        <th scope="col">Summa</th>
                        <th scope="col">Description</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${report3}" var="rep">
                        <tr>
                            <td id="date">${rep.getValue().getDate()}</td>
                            <td id="sum">${rep.getValue().getSum()}</td>
                            <td>${rep.getValue().getDescription()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="chartContainer" style="height: 300px; width: 100%;"></div>
    </div>
</body>
</html>