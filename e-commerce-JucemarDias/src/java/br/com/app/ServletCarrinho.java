package br.com.app;

import br.com.modelo.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jucemar Dias
 */
public class ServletCarrinho extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        Produto prod = new Produto();

        if (acao.equals("addProduto")) {
            String idProduto = req.getParameter("idProduto");
            String descricao = req.getParameter("produto");
            String valor = req.getParameter("valor");
            String qtd = "1";
          //fazer um array de sessão para guardar varias compras
            
            HttpSession session = req.getSession();
                       
            String idProduto2 = (String) session.getAttribute("idProduto");
            
            if(idProduto.equals(idProduto2)){
                String qtdSessao = (String) session.getAttribute("qtd");
                Integer soma = Integer.parseInt(qtdSessao) + Integer.parseInt(qtd);
                session.setAttribute("idProduto", idProduto);
                session.setAttribute("produto", descricao);
                session.setAttribute("valor", valor);
                session.setAttribute("qtd", soma.toString());
            }else{
                session.setAttribute("idproduto", idProduto);
                session.setAttribute("descricao", descricao);
                session.setAttribute("valor", valor);
                session.setAttribute("qtd", qtd);
            }
            

            RequestDispatcher rd = req.getRequestDispatcher("/ListaCliente.jsp");
            rd.forward(req, resp);
        }
        if (acao.equals("excluirSessao")){
            HttpSession session = req.getSession();
            
            String idProduto2 = (String) session.getAttribute("idProduto");
            String qtd3 = (String) session.getAttribute("qtd");
            
            String idProduto = req.getParameter("idProduto");
            String produto = req.getParameter("produto");
            String valor = req.getParameter("valor");
            String qtd = req.getParameter("qtd");
            
            Integer qtd2 = Integer.parseInt(qtd);
            Integer qtd4 = Integer.parseInt(qtd3);
            
            if (idProduto.equals(idProduto2)){
                if (qtd2 > 1){
                    Integer memos = qtd4 - 1;
                    qtd = memos.toString();
                    session.setAttribute("qtd", qtd);
                }else{
                
                session.removeAttribute("idProduto");
                session.removeAttribute("produto");
                session.removeAttribute("valor");
                session.removeAttribute("qtd");
            }
            RequestDispatcher rd = req.getRequestDispatcher("/carrinho.jsp");
            rd.forward(req, resp);
            
        }
        
         }
        
    }   
    
}
