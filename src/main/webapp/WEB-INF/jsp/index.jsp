<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Expense maneger</title>
</head>
<body>
    <h2 align="center">Tables from database</h2>

    <h3 align="center">PERSONS</h3>
    <table border="2" width="450" cellspacing="2" cellpadding="5" align="center">
        <tr>
            <td>person_id</td><td>login</td><td>pass</td><td>email</td><td>description</td><td>reg_date</td><td>phone_number</td>
        </tr>
        <c:forEach items="${persons}" var="person">
            <tr>
                <td>${person.person_id}</td>
                <td>${person.login}</td>
                <td>${person.pass}</td>
                <td>${person.email}</td>
                <td>${person.description}</td>
                <td>${person.reg_date}</td>
                <td>${person.phone_number}</td>
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
                <td>${account.account_id}</td>
                <td>${account.account_number}</td>
                <td>${account.person_id_fk}</td>
                <td>${account.currency}</td>
                <td>${account.balance}</td>
                <td>${account.description}</td>
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
                <td>${budget_type.budget_type_id}</td>
                <td>${budget_type.group_id}</td>
                <td>${budget_type.name}</td>
                <td>${budget_type.required}</td>
                <td>${budget_type.check_max}</td>
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
                <td>${budget.budget_id}</td>
                <td>${budget.operation_type}</td>
                <td>${budget.budget_type_id_fk}</td>
                <td>${budget.description}</td>
                <td>${budget.account_id_fk}</td>
                <td>${budget.operation_date}</td>
                <td>${budget.charge_value}</td>
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
                <td>${plan_budget.plan_budget_id}</td>
                <td>${plan_budget.operation_type}</td>
                <td>${plan_budget.budget_type_id_fk}</td>
                <td>${plan_budget.description}</td>
                <td>${plan_budget.account_id_fk}</td>
                <td>${plan_budget.operation_date}</td>
                <td>${plan_budget.charge_value}</td>
                <td>${plan_budget.regular_mask}</td>
                <td>${plan_budget.repeat_count}</td>
                <td>${plan_budget.start_date}</td>
                <td>${plan_budget.end_date}</td>
            </tr>
        </c:forEach>
    </table>


    <%--<form method="POST" action="/add">
        <label>New cat name <input type="text" name="name"/></label>
        <input type="submit"/>
    </form>--%>

    <%--<p><ul>
        <li><a href="/hello">http://localhost:8080/hello</a>
        <li><a href="/hello?name=Student">http://localhost:8080/hello?name=Student</a>
    </ul></p>--%>
</body>
</html>