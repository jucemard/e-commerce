<%@ page import="br.com.controller.LoginController" %>

<% if (LoginController.estaLogado(request)) { %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        
        <p> FUNCIONOu</p>
    </body>
</html>

<% } else {
        response.sendRedirect("/e-commerce");
    }
%>