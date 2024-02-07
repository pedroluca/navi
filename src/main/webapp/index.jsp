<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="model.Aluno" %>
<%
    HttpSession currentSession = request.getSession(false);
    Aluno aluno = (Aluno) currentSession.getAttribute("loggedInUser");

    if (aluno != null) {
        response.sendRedirect("home.jsp");
    } else {
%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="imagens/logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <title>Entre em sua conta!</title>
  </head>
  <body class="pagina-login">
    <section class="section1">
      <h1 class="system-name">Navi</h1>
      <img src="imagens/logo.png" class="logo-l" alt="Logo Navi">
    </section>
    <section class="section2">
	  <h2>Bem-vindo(a) ao Navi</h2>
      <form class="login-form" name="frmLogin" action="login">
        <label for="email">Email</label>
        <input type="email" name="email" placeholder="ex: antonio@gmail.com">
        <label for="senha">Senha</label>
        <div class="password-container">
          <input type="password" name="senha" id="senha">
          <i class="fa-solid fa-eye" id="togglePassword"></i>
        </div>
        <a href="">Recuperar senha</a>
        <p>Não possui conta? <a href="cadastro.jsp">Cadastre-se aqui</a></p>
        <button onclick="validarLogin()">Entrar</button>
      </form>
    </section>
    <script src="./js/visualizadorSenha.js"></script>
  </body>
</html>

<%
    }
%>