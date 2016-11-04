<%@page import="java.util.List"%>
<%@page import="br.com.modelo.Produto"%>
<%@ page import="br.com.controller.LoginController" %>

<% if (LoginController.estaLogado(request)) { %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
   
%>
<html>
    <head>
        <%@include file="/WEB-INF/includes/head.jsp" %>  
        <title>Produtos</title>
    </head>
    <body>
        <a href="<%="/TrabWeb_2016"%>"> <button>Sair</button></a>
          
        <br />
        <% if (produtos != null) { %>
        <table border="1">
            <tr>
                <th>Código</th>
                <th>Produto</th>
                <th>Informação</th>
                <th>Valor</th>
                <th>Alterar</th>
                <th>Excluir</th>
            </tr>
            <% for (Produto prod: produtos){ %>
            <tr>
                <td><%= prod.getIdProduto()%></td>
                <td><%= prod.getDescricao()%></td>
                <td><%= prod.getQuantidade()%></td>
                <td><%= prod.getValor()%></td>
                <td><a href="/TrabWeb_2016/ServletProduto?acao=editar&idProduto=<%= prod.getIdProduto()%>"><b>Editar</b></a></td>
                <td><a href="/TrabWeb_2016/ServletProduto?acao=excluir&idProduto=<%= prod.getIdProduto()%>"><b>Excluir</b></a></td>
            </tr>    
            <%  } %>
            
            <tr>
                <td colspan="2"><button><a href="produto.jsp">Novo Produto</a></button></td>
            </tr>
        </table>
            <% } %>
    </body>
</html>