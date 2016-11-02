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
import java.sql.Connection;
import java.util.List;

public class ServletProdutoList extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramDelete = req.getParameter("delete");
        /*
         Se o parâmetro delete for verdadeiro, então invoca o método doDelete,
         caso contrário prossegue com a consulta.
         */
        System.out.println("paramDelete " + paramDelete);
        if ("true".equals(paramDelete)) {
            doDelete(req, resp);
        } else {
            consultar(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conn = (Connection) req.getAttribute("conexao");
            String codigoParam = req.getParameter("codigo");
            new ProdutoDAO(conn).excluir(codigoParam);
        } catch (Exception ex) {
            ex.printStackTrace();
            String mensagemErro = "Não foi possível excluir o produto, tente novamente.";
            req.setAttribute("mensagem_erro", mensagemErro);
        }
        req.getRequestDispatcher("/e-commerce-JucemarDias/listarprodutos.jsp").forward(req, resp);
    }

    private void consultar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conn = (Connection) req.getAttribute("conexao");
            String descricaoParam = req.getParameter("descricao");
            List<Produto> produtos = new ProdutoDAO(conn).consultarPorDescricao(descricaoParam);
            req.setAttribute("produtos", produtos);
        } catch (Exception ex) {
            ex.printStackTrace();
            String mensagemErro = "Não foi possível realizar a consulta de produtos, tente novamente.";
            req.setAttribute("mensagem_erro", mensagemErro);
        }
        req.getRequestDispatcher("/e-commerce-JucemarDias/listarprodutos.jsp").forward(req, resp);
    }

}
