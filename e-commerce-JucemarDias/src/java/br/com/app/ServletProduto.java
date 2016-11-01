package br.com.app;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.conexao.Mysql;
import br.com.controller.ProdutoDAO;
import br.com.modelo.Produto;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServletProduto extends HttpServlet {

    Produto produto = new Produto();
    ProdutoDAO produtoDAO = new ProdutoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");
        String idproduto = req.getParameter("idproduto");

        switch (acao) {
            case "listar":
                listarProdutos(req, resp);
                break;

            case "editar":  {
                editarProduto(req, resp, idproduto);
            }
            break;

            case "excluir":
                excluirProduto(req, resp, idproduto);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idproduto = req.getParameter("idproduto");
        String descricao = req.getParameter("descricao");
        String quantidade = req.getParameter("quantidade");
        String valor = req.getParameter("valor");

        if (!idproduto.equals("")) {
            produto.setIdProduto(Integer.parseInt(idproduto));
        }
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        produto.setValor(Double.parseDouble(valor));

        if (idproduto.equals("")) {
            cadastrarProduto(produto, req, resp);
        } else {
            try {
                editarProduto(produto, req, resp);
            } catch (SQLException ex) {
                Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cadastrarProduto(Produto produto, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!produto.getDescricao().equals("")) {
            try {
                produtoDAO.inserir(produto);;
                listarProdutos(req, resp);
            } catch (SQLException ex) {
                Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void listarProdutos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conexao = (Connection) req.getAttribute("conexao");
            ProdutoDAO produtoDAO = new ProdutoDAO();

            List<Produto> produtosList = produtoDAO.ConsultaGeral();

            req.setAttribute("produtos", produtosList);
            /*Load or List*/
        } catch (SQLException ex) {
            Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }

    private void excluirProduto(HttpServletRequest req, HttpServletResponse resp, String id) throws ServletException, IOException {
        produto.setIdProduto(Integer.parseInt(id));
        try {
            produtoDAO.remover(produto);
            listarProdutos(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editarProduto(Produto produto, HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        produtoDAO.atualizar(produto);
        listarProdutos(req, resp);
    }

    private void editarProduto(HttpServletRequest req, HttpServletResponse resp, String id) throws ServletException, IOException {
        produto.setIdProduto(Integer.parseInt(id));
        {
            req.setAttribute("produto", produto);
            req.getRequestDispatcher("/ProdutoAlt.jsp").forward(req, resp);

        }
    }

}
