package br.com.app;

import br.com.controller.ProdutoDAO;
import br.com.modelo.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jucemar Dias
 */
public class ServletPesquisar extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        try {
           
            Connection conexao = (Connection) req.getAttribute("conexao");
            ProdutoDAO produtoDao = new ProdutoDAO();

            List<Produto> Listprodutos = produtoDao.ConsultaGeral();

            req.setAttribute("produtos", Listprodutos);
        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("mensagem_erro", "Não foi possível carregar os dados.");
            Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
        

}
