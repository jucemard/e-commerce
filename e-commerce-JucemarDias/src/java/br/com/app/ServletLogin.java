package br.com.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.controller.LoginController;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if (LoginController.login(usuario, senha)) {
            response.addCookie(LoginController.getCookie("usuario", usuario));
            response.addCookie(LoginController.getCookie("senha", senha));
            
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario_logado", usuario);
            
            if (usuario.equals("admin")){
                response.sendRedirect("/e-commerce-JucemarDias/produtos.jsp");
            }else{
                response.sendRedirect("/e-commerce-JucemarDias/listarcliente.jsp");
            }

        } else {
            response.sendRedirect("/e-commerce-JucemarDias");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
