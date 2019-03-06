<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Expense maneger</title>
</head>
<body>
    <h2 align="center">Report2</h2>
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
    <h4 align="center">TOTAL SUM: <c:out value="${totalSumUnreq}"></c:out></h4>

</body>
</html>