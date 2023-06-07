<%--
  Created by IntelliJ IDEA.
  User: SamNar
  Date: 2.06.2023
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>
<h2>User Registration</h2>
<form action="/user/registration" method="post">
    <div>
        <label>Username:</label>
        <input type="text" name="userName" />
    </div>
    <div>
        <label>User Email:</label>
        <input type="text" name="userEmail" />
    </div>
    <div>
        <label>Password:</label>
        <input type="password" name="password" />
    </div>

    <div>
        <button type="submit">Register</button>
    </div>
</form>
</body>
</html>
