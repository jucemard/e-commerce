<%-- 
    Document   : listarcliente
    Created on : 04/11/2016, 01:25:24
    Author     : Jucemar Dias
--%>

<%@page import="br.com.controller.ProdutoDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="br.com.modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
    Connection conexao = (Connection) request.getAttribute("conexao");
    ProdutoDAO produtoDAO = new ProdutoDAO();
    List<Produto> produtos = produtoDAO.ConsultaGeral();
     
%>
<html>
    <head> 
        <title>Produtos</title>
    </head>
    <body>
        <br />
         <% if (produtos != null) { %>
        <table border="1">
              <td><a href="/TrabWeb_2016/carrinho.jsp"><b>Ver Carrinho</b></a></td>
          
            <tr>
                <th>Código</th>
                <th>Produto</th>
                <th>Informação</th>
                <th>Valor</th>
            </tr>
            <% for (Produto prod: produtos){ %>
            <tr>
                <td><%= prod.getIdProduto()%></td>
                <td><%= prod.getDescricao()%></td>
                <td><%= prod.getQuantidade()%></td>
                <td><%= prod.getValor()%></td>
                <td><a href="/TrabWeb_2016/ServletPedido?acao=addProduto&idProduto=<%= prod.getIdProduto()%>"><b>Adicionar Carrinho</b></a></td>
            </tr>
            <%  } %>
        </table>
        <%  } %>
          
    </body>
</html>
