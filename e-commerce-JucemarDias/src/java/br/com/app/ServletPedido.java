package br.com.app;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.conexao.Mysql;
import br.com.controller.LoginController;
import br.com.controller.PedidoDAO;
import br.com.controller.ProdutoDAO;
import br.com.modelo.ItensPedido;
import br.com.modelo.Pedido;
import br.com.modelo.Produto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class ServletPedido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        String id = (String) req.getParameter("idproduto");

        if (acao.equals("addProduto")) {
            int idProduto = Integer.parseInt(id);
            boolean existe = false;

            HttpSession sessao = req.getSession();
            Pedido pedido = (Pedido) sessao.getAttribute("pedido");

            if (pedido == null) {
                pedido = new Pedido();
                sessao.setAttribute("pedido", pedido);
            }

            if (pedido.getItens() != null) {
                for (ItensPedido pItem : pedido.getItens()) {
                    if (pItem.getProduto().getIdProduto() == idProduto) {
                        pItem.setQuantidade(pItem.getQuantidade() + 1);
                        existe = true;
                    }
                }
            }

            if (existe == false) {
                Produto produto = null;
                try {
                    produto = new ProdutoDAO().retornaProdutoId(idProduto);
                } catch (SQLException ex) {
                    Logger.getLogger(ServletPedido.class.getName()).log(Level.SEVERE, null, ex);
                }

                ItensPedido pedidoItem = new ItensPedido();
                pedidoItem.setProduto(produto);
                pedidoItem.setQuantidade(1);

                pedido.adicionarProduto(pedidoItem);
            }

            RequestDispatcher rd = req.getRequestDispatcher("/listarcliente.jsp");
            rd.forward(req, resp);
        } else if (acao.equals("excluirSessao")) {
            HttpSession sessao = req.getSession();

            Pedido pedido = (Pedido) sessao.getAttribute("pedido");

            int idProduto = Integer.parseInt(req.getParameter("idproduto"));

            ItensPedido pedidoItem = new ItensPedido();
            Produto produto = new Produto();
            produto.setIdProduto(idProduto);
            pedidoItem.setProduto(produto);

            pedido.removerProduto(pedidoItem);
            RequestDispatcher rd = req.getRequestDispatcher("/carrinho.jsp");
            rd.forward(req, resp);
        } else if (acao.equals("cancelarCompra")) {
            HttpSession sessao = req.getSession();

            sessao.removeAttribute("pedido");

            RequestDispatcher rd = req.getRequestDispatcher("/carrinho.jsp");
            rd.forward(req, resp);

        } else if (acao.equals("comprar")) {
            HttpSession sessao = req.getSession();
            //   ArrayList<Pedido> produtos = (ArrayList) sessao.getAttribute("pedido");

            PedidoDAO pedidoDAO = new PedidoDAO();
            Pedido pedido = new Pedido();

            Cookie[] cookies = req.getCookies();
            LoginController login = new LoginController();
            int idUsuario = 0;
            try {
                idUsuario = login.getIdUsuario(cookies[1].getValue());
            } catch (SQLException ex) {
                Logger.getLogger(ServletPedido.class.getName()).log(Level.SEVERE, null, ex);
            }

            pedido.setIdUsuario(idUsuario);
            try {
                pedidoDAO.inserir(pedido);
            } catch (SQLException ex) {
                Logger.getLogger(ServletPedido.class.getName()).log(Level.SEVERE, null, ex);
            }

            RequestDispatcher rd = req.getRequestDispatcher("/carrinho.jsp");
            rd.forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
