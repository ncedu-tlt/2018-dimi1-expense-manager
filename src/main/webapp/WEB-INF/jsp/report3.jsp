<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Expense maneger</title>
</head>
<body>
    <h2 align="center">Report3</h2>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>ID категории</td><td>Дата</td><td>Сумма</td><td>Описание</td>
        </tr>
        <c:forEach items="${report3}" var="rep">
            <tr>
                <td>${rep.getId()}</td>
                <td>${rep.getDate()}</td>
                <td>${rep.getSum()}</td>
                <td>${rep.getDescription()}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>