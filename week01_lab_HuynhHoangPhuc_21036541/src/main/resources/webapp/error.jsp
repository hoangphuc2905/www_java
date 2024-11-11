
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java"
        errorPage="error.jsp"
        isErrorPage="true"
%>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Error</h1>
    <p><%=exception.toString()%></p>
</body>
</html>
