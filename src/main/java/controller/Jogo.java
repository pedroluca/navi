package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Aluno;
import model.Fase;
import modelDAO.DAO;
import modelDAO.FaseDAO;

@WebServlet(urlPatterns = {"/jogar", "/proximaFase"})
public class Jogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAO dao = new DAO();
	Aluno alunoJogador = new Aluno();
	FaseDAO faseDAO = new FaseDAO();

    public Jogo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			case "/jogar":
				doPlay(request, response);
				break;
			case "/proximaFase":
				doNextFase(request, response);
				break;
			default:
	            throw new RuntimeException("Action inválida: " + action);
		}
	}
	
	protected void doPlay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        alunoJogador = (Aluno) session.getAttribute("loggedInUser");
        
        if (alunoJogador.getIdFaseAtual().equals("0")) doFaseAvaliativa(request, response);
        else carregarFase(request, response);
    }
	
	protected void carregarFase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	faseDAO = new FaseDAO();
    	try {
        	Fase fase = faseDAO.buscarFase(alunoJogador.getIdFaseAtual());
        	HttpSession session = request.getSession();
        	session.setAttribute("faseAtual", fase);
        	response.sendRedirect("jogo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doNextFase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doFaseAvaliativa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
