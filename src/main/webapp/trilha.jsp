<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="model.Aluno" %>
<%@ page import="model.Fase" %>
<%@ page import="modelDAO.FaseDAO" %>
<%
  	HttpSession currentSession = request.getSession(false);
    Aluno aluno = (Aluno) currentSession.getAttribute("loggedInUser");
    
    if (aluno == null) {
    	response.sendRedirect("index.jsp");
    } else {  
    	
     FaseDAO faseDAO = new FaseDAO();
     Fase fase = faseDAO.buscarFase(aluno.getIdFaseAtual());
%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="imagens/logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <title>Navi | trilha</title>
  </head>
  <body class="pagina-home pagina-trilha">
    <section class="section1">
      <h1 class="system-name">Navi</h1>
      <img class="user-icon" src="imagens/profile-icon.jpg">
      <span class="user-info">
        <h2 class="user-name"><%= aluno.getNome() %></h2>
        <h3 class="user-username"><%= aluno.getUsername() %></h3>
      </span>
      <span class="user-xp">
        <p>XP atual:</p>
        <p><%= aluno.getXpAtual() %></p>
      </span>
      <div class="user-options">
      	<button onclick="window.location.href='home.jsp'">Voltar</button>
      </div>
    </section>
    <section class="section2 edit-perfil">
      <div class="edit-perfil-container trilha-container">
        <h1>Trilha</h1>
        <h2>Fase: <%= aluno.getIdFaseAtual() %></h2>
        <h2>Assunto: <%= fase.getAssunto() %></h2>
        <h2>XP Necessária: <%= fase.getXpNecessaria() %></h2>
        <div>
          <h2></h2>
          <img src="./imagens/p-1.svg">
        </div>
      </div>
      <button onclick="window.location.href='jogar'" class="botao-jogar">Jogar</button>
    </section>
  </body>
</html>
<%
    }
%>