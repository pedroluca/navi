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
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAO dao = new DAO();
	Aluno aluno = new Aluno();
	UsuarioDAO userDAO = new UsuarioDAO();

    public Registro() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String action = request.getServletPath();    	
    	aluno.setNome(request.getParameter("nome"));
		aluno.setUsername(request.getParameter("username"));
		aluno.setTelefone(request.getParameter("telefone"));
		aluno.setEmail(request.getParameter("email"));
		aluno.setSenha(request.getParameter("senha"));
		String generoParam = request.getParameter("genero");
		char genero = generoParam.charAt(0);
		aluno.setSexo(genero);
		
		switch (action) {
			case "/insert":
				doInsert(request, response, aluno);
				break;
			case "/update":
				doUpdate(request, response, aluno);
				break;
			default:
				response.sendRedirect("index.jsp");
		}
    }
    
    protected void doInsert(HttpServletRequest request, HttpServletResponse response, Aluno aluno) throws ServletException, IOException {
    	if (!request.getParameter("senha").equals(request.getParameter("confirmarSenha"))) {
            HttpSession session = request.getSession();
            session.setAttribute("alerta-cadastro", "<div id='mensagem' class='corpo-mensagem mensagem-erro'> Erro! As senhas devem ser iguais </div>"); 
			response.sendRedirect("cadastro.jsp");
		} else if (userDAO.validarCadastro(aluno) == true) {
            HttpSession session = request.getSession();
            session.setAttribute("alerta-cadastro", "<div id='mensagem' class='corpo-mensagem mensagem-erro'> Erro! Usuário ou email já em uso </div>"); 
			response.sendRedirect("cadastro.jsp");
		} else if (userDAO.validarCadastro(aluno) == false) {
			userDAO.inserirUsuario(aluno);
            HttpSession session = request.getSession();
            session.setAttribute("alerta-login", "<div id='mensagem' class='corpo-mensagem mensagem-sucesso'> Usuário cadastrado! </div>");
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("cadastro.jsp");
		}
    }
    
    protected void doUpdate(HttpServletRequest request, HttpServletResponse response, Aluno aluno) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Aluno alunoLogado = (Aluno) session.getAttribute("loggedInUser");
    	aluno.setId(alunoLogado.getId());
		if (userDAO.updateUsuario(aluno)) {
            session.setAttribute("loggedInUser", userDAO.validarUsuario(aluno));
            session.setAttribute("alerta-perfil", "<div id='mensagem' class='corpo-mensagem mensagem-sucesso'> Dados atualizados com sucesso! </div>"); 
            response.sendRedirect("perfil.jsp");
		} else {
    		response.sendRedirect("index.jsp");
    	}
    }
}