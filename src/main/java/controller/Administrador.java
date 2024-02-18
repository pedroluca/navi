package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Aluno;
import model.Questao;
import modelDAO.QuestaoDAO;
import modelDAO.UsuarioDAO;

@WebServlet("/admin")
public class Administrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Administrador() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
        switch (action) {
	        case "/admin":
	        	HttpSession session = request.getSession();
	            doMostrarAlunos(request, response, session);
	            doMostrarQuestoes(request, response, session);
	            response.sendRedirect("admin.jsp");
	            break;
	        default:
	            throw new RuntimeException("Action inválida: " + action);
	    }
	}
	
	protected void doMostrarAlunos(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		UsuarioDAO alunoDAO = new UsuarioDAO();
		ArrayList<Aluno> alunos = alunoDAO.getAllAlunos();
		session.setAttribute("alunos", alunos);
	}
	
	protected void doMostrarQuestoes(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		QuestaoDAO questaoDAO = new QuestaoDAO();
		ArrayList<Questao> questoes = questaoDAO.getAllQuestoes();
		session.setAttribute("questoes", questoes);
	}
}
