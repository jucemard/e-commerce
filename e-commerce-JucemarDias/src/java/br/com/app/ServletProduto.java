package br.com.app;

import br.com.controller.ProdutoDAO;
import br.com.modelo.Produto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletProduto extends HttpServlet {
    
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        String idProduto = request.getParameter("idproduto");

        switch (acao) {
            case "listar":
                listaProdutos(request, response);
                break;

            case "editar":
                editarProduto(request, response, idProduto);
                break;

            case "excluir":
                excluirProduto(request, response, idProduto);
                break;
        }
    }
        Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Create*/
        
       String idProduto = request.getParameter("idproduto");
       String descricao = request.getParameter("descricao");
       String quantidade = request.getParameter("quantidade");
       String valor = request.getParameter("valor");

        if (!idProduto.equals("")) {
            produto.setIdProduto(Integer.parseInt(idProduto));
        }
        produto.setDescricao(descricao);
        produto.setQuantidade(Double.parseDouble(quantidade));
        produto.setValor(Double.parseDouble(valor));

        if (idProduto.equals("")) {
            cadastrarProduto(produto, request, response);
        } else {
            try {
                editarProduto(produto, request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void listaProdutos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void cadastrarProduto(Produto produto, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!produto.getDescricao().equals("")){
            try {
                produtoDAO.inserir(produto);;
                listaProdutos(req, resp);
            } catch (SQLException ex) {
                Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    private void editarProduto(Produto produto, HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        produtoDAO.atualizar(produto);
        listaProdutos(req, resp);
    }
    
    private void editarProduto(HttpServletRequest req, HttpServletResponse resp, String idProduto) throws ServletException, IOException {
        produto.setIdProduto(Integer.parseInt(idProduto));
        {
            try{
                req.setAttribute("produto", produtoDAO.retornaProdutoId(produto));
                req.getRequestDispatcher("/alterarprodutos.jsp").forward(req, resp);
            }catch(SQLException ex){
                Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void excluirProduto(HttpServletRequest req, HttpServletResponse resp, String id) throws ServletException, IOException {
        produto.setIdProduto(Integer.parseInt(id));
        try {
            produtoDAO.remover(produto);
            listaProdutos(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
