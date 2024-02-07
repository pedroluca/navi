package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelDAO.UsuarioDAO;
import model.Aluno;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class Sessao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioDAO userDAO = new UsuarioDAO();
    
    public Sessao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if(action.equals("/login")) doLogin(request, response);
		else if (action.equals("/logout")) doLogout(request, response);
	}
	
	protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        if (email != null && senha != null) {
            Aluno aluno = new Aluno();
            aluno.setEmail(email);
            aluno.setSenha(senha);
            
            Aluno foundAluno = userDAO.validarUsuario(aluno);
            
            if (foundAluno != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", foundAluno);                
                response.sendRedirect("home.jsp");
            } else response.sendRedirect("index.jsp");
        }
	}
	
	protected void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		    response.sendRedirect("index.jsp");
		}
	}
		
		
}
