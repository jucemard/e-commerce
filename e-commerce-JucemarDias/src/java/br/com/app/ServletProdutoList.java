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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Load or List*/
    }

}
