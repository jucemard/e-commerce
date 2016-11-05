<%-- 
    Document   : carrinho
    Created on : 04/11/2016, 01:24:35
    Author     : Jucemar Dias
--%>

<%@page import="br.com.modelo.ItensPedido"%>
<%@page import="br.com.modelo.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <title>Carrinho</title>
    </head>
    <body>
        <br />
        <h1>Carrinho de Compras</h1>         
        <table border="1">
            <td colspan="5"><a href="4/e-commerce-JucemarDias/listarcliente.jsp"><b>Continuar Comprando</b></a></td>
            <tr>
                <th>Código</th>
                <th>Produto</th>
                <th>Valor</th>
                <th>Quantidade</th>
            </tr>
            <% Pedido pedido = (Pedido) session.getAttribute("pedido");
                for (ItensPedido pIten : pedido.getItens()) { %>
            <tr>
                <td><%= pIten.getProduto().getIdProduto()%></td>
                <td><%= pIten.getProduto().getDescricao()%></td>
                <td><%= pIten.getProduto().getValor()%></td>
                <td><%= pIten.getQuantidade()%></td>
                <td><a href="/e-commerce-JucemarDias/ServletPedido?acao=excluirSessao&idproduto=<%= pIten.getProduto().getIdProduto()%>"><b>Excluir</b></a></td>
            </tr>                                                               
            <% } %>
            <td colspan="5"><a href="/e-commerce-JucemarDias/ServletPedido?acao=comprar"><b>Efetuar Compra</b></a></td>
        </table>
        
    </body>
</html>
