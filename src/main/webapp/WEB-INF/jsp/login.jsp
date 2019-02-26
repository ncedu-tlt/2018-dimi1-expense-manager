<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Spring Security Example </title>
    </head>
    <body>
        <sec:authorize access="hasRole('USER')">
            <c:redirect url="/menu"/>
        </sec:authorize>
        <form action="/login" method="post">
            <div><label> User Name : <input type="text" name="login"/> </label></div>
            <div><label> Password: <input type="password" name="login"/> </label></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div><input type="submit" value="Sign In"/></div>
        </form>

    </body>
</html>