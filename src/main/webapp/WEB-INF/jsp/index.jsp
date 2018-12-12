<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello, world</title>
</head>
<body>
    <p>Hello, world</p>

    <ul>
        <c:forEach items="${cats}" var="cat">
            <li>"${cat.name}"</li>
        </c:forEach>
    </ul>

    <form method="POST" action="/add">
        <label>New cat name <input type="text" name="name"/></label>
        <input type="submit"/>
    </form>

    <p><ul>
        <li><a href="/hello">http://localhost:8080/hello</a>
        <li><a href="/hello?name=Student">http://localhost:8080/hello?name=Student</a>
    </ul></p>
</body>
</html>