<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>home</title>
</head>
<body>
    <h1>User List View V2</h1>
    <table border="1">
        <thead>
            <tr>
                <th>#</th>
                <th>아이디</th>
                <th>이름</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user" varStatus="status">
                <tr>
                    <th scope="row">${status.count}</th>
                    <td>${user.userId}</td>
                    <td>${user.name}</td>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <hr>
    <div>
        <a href="/v2/users/form">회원가입</a>
    </div>

</body>
</html>