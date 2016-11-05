<%-- 
    Document   : produto
    Created on : 24/10/2016, 21:57:35
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
        <h1>Produto</h1>
        <% if (mensagemErro != null) {%>
        <p class="erro"><%= mensagemErro%></p>
        <% }%>
        <a href="<%="/e-commerce-JucemarDias/ServletProduto?acao=listar"%>"> <button>Lista de Produto</button></a>
      
        <form action="ServletProduto" method="POST">
            <input type="hidden" name="idproduto" value="" />
            <label>Descrição</label><br />
            <input type="text" name="descricao" value="" />
            <br /><br />
            <label>Quantidade</label><br />
            <input name="quantidade" value=""> </input>
            <br /><br />
            <label>Valor</label><br />
            <input type="text" name="valor" value="" />
            <br /><br />
            <input type="submit" value="Salvar" />
        </form>
    </body>
</html>
