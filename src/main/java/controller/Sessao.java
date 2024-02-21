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
    private UsuarioDAO userDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/login":
                doLogin(request, response);
                break;
            default:
                throw new RuntimeException("Action inválida: " + action);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/logout":
                doLogout(request, response);
                break;
            default:
                throw new RuntimeException("Action inválida: " + action);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Aluno aluno = new Aluno();
        aluno.setEmail(request.getParameter("email"));
        aluno.setSenha(request.getParameter("senha"));

        if (aluno.getEmail() != null && aluno.getSenha() != null) {
            Aluno foundAluno = userDAO.validarUsuario(aluno);

            if (foundAluno != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", foundAluno);
                String redirectUrl = userDAO.validarUsuario(foundAluno).getIsAdm() ? "admin" : "home.jsp";
                response.sendRedirect(redirectUrl);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("alerta-login", "<div id='mensagem' class='corpo-mensagem mensagem-erro'> Erro! Usuário ou senha incorretos </div>");
                response.sendRedirect("index.jsp");
            }
        }
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    }
}
