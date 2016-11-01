<%-- 
    Document   : editarprodutos
    Created on : 31/10/2016, 22:08:34
    Author     : Jucemar Dias
--%>
<%@page import="br.com.modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
    Produto prod = (Produto) request.getAttribute("produto");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Produto</title>
    </head>
    <body>
        <h1>Editar Produto</h1>
        <% if (mensagemErro != null) {%>
        <p class="erro"><%= mensagemErro%></p>
        <% }%>
    <f<head>
            <title>Produto</title>
        </head>
        <bodyorm action="ServletProduto" method="POST">
            <input type="hidden" name="idproduto" value="<%=prod.getIdProduto()%>" />
            <label>Descrição</label><br />
            <input type="text" name="produto" value="<%=prod.getDescricao()%>" />
            <br /><br />
            <label>Informação</label><br />
            <textarea name="informacao" rows="10" cols="50" value="<%=prod.getQuantidade()%>"></textarea>
            <br /><br />
            <label>Valor</label><br />
            <input type="text" name="valor" value="<%=prod.getValor()%>" />
            <br /><br />
            <input type="submit" value="Publicar" />
            </form>
            </body>
            </html>
