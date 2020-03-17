<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:useBean id="date" class="java.util.GregorianCalendar"/>

<html>
<head>
    <title>Startup</title>
</head>
<body>
    <h1>Hello!</h1>
    <br>
    <h1>Time now is ${date.time}</h1>
</body>
</html>