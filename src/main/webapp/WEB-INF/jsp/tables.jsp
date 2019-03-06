<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <%--<meta charset="UTF-8">--%>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Expense maneger</title>
</head>
<body>
    <h2 align="center">Tables from database</h2>

    <h3 align="center">PERSONS</h3>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>person_id</td><td>login</td><td>pass</td><td>email</td><td>description</td><td>reg_date</td><td>phone_number</td>
        </tr>
        <c:forEach items="${persons}" var="rep">
            <tr>
                <td>${rep.getPersonId()}</td>
                <td>${rep.getLogin()}</td>
                <td>${rep.getPass()}</td>
                <td>${rep.getEmail()}</td>
                <td>${rep.getDescription()}</td>
                <td>${rep.getRegDate()}</td>
                <td>${rep.getPhonenumber()}</td>
            </tr>
        </c:forEach>
    </table>

    <h3 align="center">ACCOUNTS</h3>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>account_id</td><td>account_number</td><td>person_id_fk</td><td>currency</td><td>balance</td><td>description</td>
        </tr>
        <c:forEach items="${accounts}" var="account">
            <tr>
                <td>${account.getAccountId()}</td>
                <td>${account.getAccountNumber()}</td>
                <td>${account.getPersonId()}</td>
                <td>${account.getCurrency()}</td>
                <td>${account.getBalance()}</td>
                <td>${account.getDescription()}</td>
            </tr>
        </c:forEach>
    </table>

    <h3 align="center">BUDGET_TYPE</h3>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>budget_type_id</td><td>group_id</td><td>name</td><td>required</td><td>check_max</td>
        </tr>
        <c:forEach items="${budget_types}" var="budget_type">
            <tr>
                <td>${budget_type.getBudgetTypeId()}</td>
                <td>${budget_type.getGroupId()}</td>
                <td>${budget_type.getName()}</td>
                <td>${budget_type.getRequired()}</td>
                <td>${budget_type.getCheckMax()}</td>
            </tr>
        </c:forEach>
    </table>

    <h3 align="center">BUDGET</h3>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>budget_id</td><td>operation_type</td><td>budget_type_id_fk</td><td>description</td><td>account_id_fk</td>
            <td>operation_date</td><td>charge_value</td>
        </tr>
        <c:forEach items="${budgets}" var="budget">
            <tr>
                <td>${budget.getBudgetId()}</td>
                <td>${budget.getOperationType()}</td>
                <td>${budget.getBudgetTypeId()}</td>
                <td>${budget.getDescription()}</td>
                <td>${budget.getAccountId()}</td>
                <td>${budget.getOperationDate()}</td>
                <td>${budget.getChargeValue()}</td>
            </tr>
        </c:forEach>
    </table>

    <h3 align="center">PLAN_BUDGET</h3>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>plan_budget_id</td><td>operation_type</td><td>budget_type_id_fk</td><td>description</td><td>account_id_fk</td>
            <td>operation_date</td><td>charge_value</td><td>regular_mask</td><td>repeat_count</td><td>start_date</td>
            <td>end_date</td>
        </tr>
        <c:forEach items="${plan_budgets}" var="plan_budget">
            <tr>
                <td>${plan_budget.getPlanBudgetId()}</td>
                <td>${plan_budget.getOperationType()}</td>
                <td>${plan_budget.getBudgetTypeId()}</td>
                <td>${plan_budget.getDescription()}</td>
                <td>${plan_budget.getAccountId()}</td>
                <td>${plan_budget.getOperationDate()}</td>
                <td>${plan_budget.getChargeValue()}</td>
                <td>${plan_budget.getRegularMask()}</td>
                <td>${plan_budget.getRepeatCount()}</td>
                <td>${plan_budget.getStartDate()}</td>
                <td>${plan_budget.getEndDate()}</td>
            </tr>
        </c:forEach>
    </table>


    <form align="center" method="GET" action="/showReport1">
        <p>
            <input type="submit" value="Report1"/>
        </p>
    </form>
    <form align="center" method="GET" action="/showReport2">
        <p>
            <input type="submit" value="Report2"/>
        </p>
    </form>
    <form align="center" method="GET" action="/showReport3">
        <p>
            <input type="submit" value="Report3"/>
        </p>
    </form>

    <%--<p><ul>
        <li><a href="/hello">http://localhost:8080/hello</a>
        <li><a href="/hello?name=Student">http://localhost:8080/hello?name=Student</a>
    </ul></p>--%>
</body>
</html>
