package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Aluno;
import model.DAO;

@WebServlet(urlPatterns = {"/controller", "/home", "/login", "/insert"})
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
			response.sendRedirect("home.html");
		} else if(action.equals("/insert")) {
			novoUsuario(request, response);
			
		} else if(action.equals("/login")){
			login(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	// fazer Login
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String senha = request.getParameter("senha");
	    
	    if (email != null && senha != null) {
	        Aluno aluno = new Aluno();
	        aluno.setEmail(email);
	        aluno.setSenha(senha);
	        
	        if (dao.validarUsuario(aluno)) {
	        	System.out.println("Login Realizado com Sucesso!!!"); //tirar depois
		        response.sendRedirect("home.html");
	        } else {
	        	System.out.println("Login Falhou!!!"); //tirar depois
		        response.sendRedirect("index.html");
	        }
	    }
	}

	// cadastrar usuario
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
}