<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="model.Aluno" %>
<%@ page import="model.Questao" %>
<%@ page import="model.Alternativa" %>
<%@ page import="java.util.ArrayList" %>
<%
    HttpSession currentSession = request.getSession(false);
    Aluno aluno = (Aluno) currentSession.getAttribute("loggedInUser");
    ArrayList<Aluno> alunos = (ArrayList<Aluno>) currentSession.getAttribute("alunos");
    ArrayList<Questao> questoes = (ArrayList<Questao>) currentSession.getAttribute("questoes");

    if (aluno == null) {
    	currentSession.setAttribute("alerta", "<div id='mensagem' class='corpo-mensagem mensagem-erro'> Faça login primeiro! </div>");
        response.sendRedirect("index.jsp");
    } else if (!aluno.getIsAdm()) {
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
    <title>Navi | dashboard</title>
  </head>
  <body class="pagina-home">
    <section class="section1">
      <h1 class="system-name">Navi</h1>
      <img class="user-icon" src="imagens/profile-icon.jpg">
      <span class="user-info">
        <h2 class="user-name"><%= aluno.getNome() %></h2>
        <h3 class="user-username"><%= aluno.getUsername() %></h3>
      </span>
      <span class="user-xp">
        <p>EXP:</p>
        <p>10000</p>
      </span>
      <div class="user-options">
        <button onclick="window.location.href='perfil.jsp'">Perfil</button>
        <button id="btn-alunos" onclick="mostrarAlunos()" class="ativo" onclick="window.location.href='/alunos'">Ver Alunos</button>
        <button id="btn-questoes" onclick="mostrarQuestoes()" onclick="window.location.href='/questoes'">Ver Questões</button>
        <a href="logout" class="logout-button">Sair</a>
      </div>
    </section>
    <section class="section2">
      <div class="conteudo-dashboard">
        <div id="alunos" class="">
          <h2>Gerenciar Alunos</h2>
          <table>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Email</th>
              <th>Telefone</th>
              <th>Sexo</th>
            </tr>
            
            <% for (Aluno alunoList : alunos){ %>
		    	<tr>
			     	<td><%= alunoList.getId() %></td>
			      	<td><%= alunoList.getNome() %></td>
			      	<td><%= alunoList.getEmail() %></td>
			      	<td><%= alunoList.getTelefone() %></td>
			      	<td><%= alunoList.getSexo() %></td>
		    	</tr>
		  	<% } %>
          </table>
        </div>
  
        <div id="questoes" class="sessao-oculta">
          <h2>Gerenciar Questões <button>Cadastrar nova questão</button></h2>
          <table>
            <tr>
              <th>ID</th>
              <th>Enunciado</th>
              <th>Respostas</th>
            </tr>
                        
            <% for (Questao questao : questoes){ %>
		    	<tr>
			     	<td><%= questao.getId() %></td>
			      	<td><%= questao.getEnunciado() %></td>
			      	<td>
			      		<ul><% for (Alternativa alternativa : questao.getAlternativas()){ %>
			      			<li><%=		alternativa.getTexto() %></li>
			      			<% } %>
			      		</ul>
			      	</td>
		    	</tr>
		  	<% } %>
          </table>
        </div>
      </div>
    </section>
    <script>
      function mostrarAlunos() {
        document.getElementById("alunos").classList.remove("sessao-oculta");
        document.getElementById("questoes").classList.add("sessao-oculta");
        document.getElementById("btn-alunos").classList.add("ativo");
        document.getElementById("btn-questoes").classList.remove("ativo");
      }

      function mostrarQuestoes() {
        document.getElementById("alunos").classList.add("sessao-oculta");
        document.getElementById("questoes").classList.remove("sessao-oculta");
        document.getElementById("btn-alunos").classList.remove("ativo");
        document.getElementById("btn-questoes").classList.add("ativo");
      }
    </script>
  </body>
</html>
<%
    }
%>