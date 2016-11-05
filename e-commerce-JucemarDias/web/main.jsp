<%@page import="br.com.modelo.Produto"%>
<%@ page import="br.com.controller.LoginController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

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
        <a href="<%="/e-commerce-JucemarDias"%>"> <button>Sair</button></a>
          
        <br />
        <% if (produtos != null) { %>
        <table border="1">
            <tr>
                <th>Código</th>
                <th>Descrição</th>
                <th>Quantidade</th>
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
                <td><a href="/e-commerce-JucemarDias/ServletProduto?acao=editar&idproduto=<%= prod.getIdProduto()%>"><b>Editar</b></a></td>
                <td><a href="/e-commerce-JucemarDias/ServletProduto?acao=excluir&idproduto=<%= prod.getIdProduto()%>"><b>Excluir</b></a></td>
            </tr>    
            <%  } %>
            
            <tr>
                <td colspan="2"><button><a href="produtos.jsp">Novo Produto</a></button></td>
            </tr>
        </table>
            <% } %>
    </body>
</html>