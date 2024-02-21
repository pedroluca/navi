package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Alternativa;
import model.Aluno;
import model.Fase;
import model.Questao;
import modelDAO.AlternativaDAO;
import modelDAO.DAO;
import modelDAO.FaseDAO;
import modelDAO.UsuarioDAO;

@WebServlet(urlPatterns = {"/jogar", "/proxima", "/fecharJogo"})
public class Jogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAO dao = new DAO();
	Aluno alunoJogador = new Aluno();

    public Jogo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			case "/jogar":
				doPlay(request, response);
				break;
			case "/proxima":
				doNextQuestao(request, response);
				break;
			case "/fecharJogo":
				doFecharJogo(request, response);
				break;
			default:
	            throw new RuntimeException("Action inválida: " + action);
		}
	}
	
	protected void doPlay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        alunoJogador = (Aluno) session.getAttribute("loggedInUser");
    	carregarFase(request, response);
    }
	
	protected void carregarFase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	FaseDAO faseDAO = new FaseDAO();
    	try {
        	Fase fase = faseDAO.buscarFase(alunoJogador.getIdFaseAtual());
        	doMostrarQuestao(request, response, fase);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("trilha.jsp");
		}
	}
	
	protected void doMostrarQuestao(HttpServletRequest request, HttpServletResponse response, Fase fase) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    FaseDAO faseDAO = new FaseDAO();
	    Aluno aluno = (Aluno) session.getAttribute("loggedInUser");

	    @SuppressWarnings("unchecked")
		Set<Questao> answeredQuestions = (Set<Questao>) session.getAttribute("answeredQuestions");
	    if (answeredQuestions == null) {
	        answeredQuestions = new HashSet<>();
	        session.setAttribute("answeredQuestions", answeredQuestions);
	    }

	    Questao questaoAtual;
	    do {
	        questaoAtual = faseDAO.getQuestoes(faseDAO.buscarFase(aluno.getIdFaseAtual())).get(new Random().nextInt(faseDAO.getQuestoes(faseDAO.buscarFase(aluno.getIdFaseAtual())).size()));
	    } while (answeredQuestions.contains(questaoAtual));

	    answeredQuestions.add(questaoAtual);

	    session.setAttribute("questaoAtual", questaoAtual);
	    response.sendRedirect("jogo.jsp");
	}
	
	protected void doNextQuestao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Aluno aluno = (Aluno) session.getAttribute("loggedInUser");		
		FaseDAO faseDAO = new FaseDAO();
		UsuarioDAO alunoDAO = new UsuarioDAO();
		
		String opcaoSelecionada = request.getParameter("opcoes");
        AlternativaDAO alternativaDAO = new AlternativaDAO();
        Alternativa alternativa = alternativaDAO.buscarAlternativa(opcaoSelecionada);
        if (alternativa.getIsCorreta()) alunoDAO.somarXp(aluno.getId(), 100);

        if (alunoDAO.verificarPassarDeFase(aluno.getId()) == true) {
        	doNextFase(request, response);
        } else {
        	Fase fase = faseDAO.buscarFase(aluno.getIdFaseAtual());
        	doMostrarQuestao(request, response, fase);
        }
	}
	
	protected void doNextFase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Aluno aluno = (Aluno) session.getAttribute("loggedInUser");
		UsuarioDAO alunoDAO = new UsuarioDAO();
		if (alunoDAO.avancarFase(aluno)) {
			this.carregarFase(request, response);
		}
	}
	
	protected void doFecharJogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Aluno aluno = (Aluno) session.getAttribute("loggedInUser");
		UsuarioDAO alunoDAO = new UsuarioDAO();
		aluno = alunoDAO.validarUsuario(aluno);
		session.setAttribute("loggedInUser", aluno);
		response.sendRedirect("trilha.jsp");
	}
}
