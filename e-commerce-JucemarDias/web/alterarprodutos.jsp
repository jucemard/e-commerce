<%-- 
    Document   : AlterarProdutos
    Created on : 04/11/2016, 01:24:18
    Author     : Jucemar Dias
--%>

<%@page import="br.com.modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
    Produto produto = (Produto) request.getAttribute("produto");
%>
<html>
    <head>
         <title>Produto</title>
    </head>
    <body>
        <h1>Alterar Produto</h1>
        <% if (mensagemErro != null) {%>
        <p class="erro"><%= mensagemErro%></p>
        <% }%>
        <form action="ServletProduto" method="POST">
            <input type="hidden" name="idProduto" value="<%=produto.getIdProduto()%>" />
            <label>Descrição</label><br />
            <input type="text" name="produto" value="<%=produto.getDescricao()%>" />
            <br /><br />
            <label>Informação</label><br />
             <input type="text" name="informacao" value="<%=produto.getQuantidade()%>" />
            <br /><br />
            <label>Valor</label><br />
            <input type="text" name="valor" value="<%=produto.getValor()%>" />
            <br /><br />
            <input type="submit" value="Publicar" />
        </form>
    </body>
</html>
