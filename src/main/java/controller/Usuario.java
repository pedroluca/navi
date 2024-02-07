package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Aluno;
import modelDAO.DAO;
import modelDAO.UsuarioDAO;

@WebServlet(urlPatterns = {"/insert", "/update"})
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAO dao = new DAO();
	Aluno aluno = new Aluno();
	UsuarioDAO userDAO = new UsuarioDAO();

    public Usuario() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();
    	aluno.setNome(request.getParameter("nome"));
		aluno.setUsername(request.getParameter("username"));
		aluno.setTelefone(request.getParameter("telefone"));
		aluno.setEmail(request.getParameter("email"));
		aluno.setSenha(request.getParameter("senha"));
		String generoParam = request.getParameter("genero");
		char genero = generoParam.charAt(0);
		aluno.setSexo(genero);
		
		if (userDAO.validarCadastro(aluno) == false && action.equals("/insert")) {
			userDAO.inserirUsuario(aluno);
			response.sendRedirect("index.html");
		} else if (userDAO.validarCadastro(aluno) == true && action.equals("/insert")) response.sendRedirect("cadastro.html");
    	else if (userDAO.validarCadastro(aluno) == true && action.equals("/update")) {
    		aluno.setId(userDAO.buscarId(aluno));
    		userDAO.updateUsuario(aluno);
    		
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", userDAO.validarUsuario(aluno)); 
            
    		response.sendRedirect("perfil.jsp");
    	} else response.sendRedirect("home.jsp");
    }
}