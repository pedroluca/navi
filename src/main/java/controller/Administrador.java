package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Alternativa;
import model.Aluno;
import model.Questao;
import modelDAO.QuestaoDAO;
import modelDAO.UsuarioDAO;

@WebServlet(urlPatterns = {"/admin", "/insertQuestao"})
public class Administrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Administrador() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		String action = request.getServletPath();
		switch (action) {
	        case "/insertQuestao":
	        	doCadastrarQuestao(request, response);
	    	    response.sendRedirect("admin");
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
	
	protected void doCadastrarQuestao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    QuestaoDAO questaoDAO = new QuestaoDAO();
	    Questao questao = new Questao();
	    questao.setEnunciado(request.getParameter("enunciado"));
	    questao.setAssunto(request.getParameter("assunto"));
	    
	    String[] alternativasTexto = request.getParameterValues("alternativas[]");
	    String respostaCorreta = request.getParameter("respostaCorreta");
	    
	    ArrayList<Alternativa> alternativas = new ArrayList<>();
	    for (int i =  0; i < alternativasTexto.length; i++) {
	        Alternativa alternativa = new Alternativa();
	        alternativa.setTexto(alternativasTexto[i]);
	        if (i == Integer.parseInt(respostaCorreta) -  1) {
	            alternativa.setIsCorreta(true);
	        }
	        alternativas.add(alternativa);
	    }
	    questao.setAlternativas(alternativas);
	    questaoDAO.cadastrarQuestao(questao);
	}
}
