<%-- 
    Document   : cadastrarprodutos
    Created on : 02/11/2016, 18:41:16
    Author     : Jucemar Dias
--%>

<%@page import="br.com.modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
    Produto produto = (Produto) request.getAttribute("produto");
    /*
     Se o produto vier NULL significa que estamos fazendo uma INCLUSÃO.
     Porém, se ele estiver NULL teremos problema ao preencher o formulário, ocorrerá NullPointer.
     Por isso é uma boa opção criar um produto vazio.
     */
    if (produto == null) {
        produto = new Produto();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/includes/header.jsp" %>
        <title>Cadastro de Produtos</title>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/menu.jsp" %>
        
        <br />

        <% if (mensagemErro != null) {%>
        <%-- aqui criamos uma "class" em CSS para utilizar em todas as nossas mensagens de erro --%>
        <p class="mensagemErro"><%=mensagemErro%></p> 
        <br />
        <% }%>

        <form method="POST" action="/e-commerce-JucemarDias/ServletProduto">
            <%-- Vamos manter o código como campo oculto, e usar um <span> para exibí-lo na tela :) --%>
            <input type="text" name="idproduto" value="<%=produto.getIdProduto()%>" hidden="hidden" />
            Código: <span><%=produto.getIdProduto()%></span> <br />
            Descrição: <input type="text" name="descricao" value="<%=produto.getDescricao()%>" /> <br />
            Quantidade: <input type="text" name="quantidade" value="<%=produto.getQuantidade()%>" /> <br />
            Valor: <input type="text" name="valor" value="<%=produto.getValor()%>" /> <br />
            <input type="submit" value="Salvar" />
        </form>
    </body>
</html>