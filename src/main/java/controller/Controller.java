package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Aluno;
import model.DAO;

@WebServlet(urlPatterns = {"/controller", "/home", "/login", "/insert", "/logout"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAO dao = new DAO();
	Aluno aluno = new Aluno();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();
        System.out.println(action);
        if(action.equals("/home")) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("loggedInUser") != null) response.sendRedirect("home.jsp");
            else response.sendRedirect("index.html");
        } else if(action.equals("/insert")) novoUsuario(request, response);
        else if(action.equals("/login")) login(request, response);
        else if(action.equals("/logout")) logout(request, response);
        else response.sendRedirect("index.html");
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        if (email != null && senha != null) {
            Aluno aluno = new Aluno();
            aluno.setEmail(email);
            aluno.setSenha(senha);
            
            Aluno foundAluno = dao.validarUsuario(aluno);
            
            if (foundAluno != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", foundAluno);                
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("index.html");
            }
        }
    }
    
	protected void novoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aluno.setNome(request.getParameter("nome"));
		aluno.setUsername(request.getParameter("usuario"));
		aluno.setTelefone(request.getParameter("telefone"));
		aluno.setEmail(request.getParameter("email"));
		aluno.setSenha(request.getParameter("senha"));
		String generoParam = request.getParameter("genero");
		char genero = generoParam.charAt(0);
		aluno.setSexo(genero);
		
		dao.inserirUsuario(aluno);
		
		response.sendRedirect("index.html");
	}
	
	protected void logout (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		    response.sendRedirect("index.html");
		}

	}
}