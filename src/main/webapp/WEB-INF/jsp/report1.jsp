<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Expense maneger</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"></link>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="/js/report1.js" rel="stylesheet" type="text/javascript"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/canvasjs/1.7.0/canvasjs.min.js"></script>

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


    <h2 align="center">Report1</h2>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>NAME</td><td>SUMMA</td><td>PERCENT</td>
        </tr>
        <c:forEach items="${report1}" var="rep">
            <tr>
                <td id="name">${rep.getName()}</td>
                <td id="sum">${rep.getSum()}</td>
                <td>${rep.getPercent()}%</td>
            </tr>
        </c:forEach>
    </table>
    <h4 align="center">TOTAL SUM: <c:out value="${totalSum}"></c:out></h4>
    <div id="chartContainer" style="height: 450px; width: 100%;"></div>
</body>
</html>