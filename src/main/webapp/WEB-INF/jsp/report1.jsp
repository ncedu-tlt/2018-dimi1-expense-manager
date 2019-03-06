<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Expense maneger</title>
</head>
<body>
    <h2 align="center">Report1</h2>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>ID</td><td>NAME</td><td>SUMMA</td><td>PERCENT</td>
        </tr>
        <c:forEach items="${report1}" var="rep">
            <tr>
                <td>${rep.getId()}</td>
                <td>${rep.getName()}</td>
                <td>${rep.getSum()}</td>
                <td>${rep.getPercent()}%</td>
            </tr>
        </c:forEach>
    </table>
    <h4 align="center">TOTAL SUM: <c:out value="${totalSum}"></c:out></h4>

</body>
</html>