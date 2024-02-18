<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="model.Aluno" %>
<%
  HttpSession currentSession = request.getSession(false);
    Aluno aluno = (Aluno) currentSession.getAttribute("loggedInUser");

    if (aluno == null) {
    	currentSession.setAttribute("alerta", "<div id='mensagem' class='corpo-mensagem mensagem-erro'> Faça login primeiro! </div>");  
        response.sendRedirect("index.jsp");
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
    <title>Navi | <%= aluno.getUsername() %></title>
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
      	<button onclick="window.location.href='home.jsp'">Voltar</button>
        <a href="logout" class="logout-button">Sair</a>
      </div>
    </section>
    <section class="section2 edit-perfil">
      <div class="edit-perfil-container">
        <h2>Editar perfil</h2>
        <form action="update" method="post">
          <label for="nome">Nome:</label>
          <input type="text" id="nome" name="nome" value="<%= aluno.getNome() %>">
          <label for="email">E-mail:</label>
          <input type="email" id="email" name="email" value="<%= aluno.getEmail() %>">
          <label for="username">Username:</label>
          <input type="text" id="username" name="username" value="<%= aluno.getUsername() %>">
          <label for="telefone">Telefone:</label>
          <input type="text" id="telefone" name="telefone" value="<%= aluno.getTelefone() %>">
          <label for="sexo">Sexo</label>
		  <div class="area-sexo">
			  <input id="feminino" type="radio" name="genero" value="F" <% if(aluno.getSexo() == 'F') %>checked <%; %>>
			  <label for="feminino">Feminino</label> 
			  <input id="masculino" type="radio" name="genero" value="M" <% if(aluno.getSexo() == 'M') %>checked <%; %>>
			  <label for="masculino">Masculino</label> 
		  </div>
          <label for="senha">Senha atual:</label>          
          <div class="password-container">
            <input type="password" name="senha" id="senha" value="<%= aluno.getSenha() %>">
            <i class="fa-solid fa-eye togglePassword" id="togglePassword"></i>
          </div>
          <label for="nova-senha">Nova senha:</label>
          <div class="password-container">
            <input type="password" name="nova-senha" id="senha2">
            <i class="fa-solid fa-eye togglePassword" id="togglePassword2"></i>
          </div>
          <label for="confirma-nova-senha">Confirme a nova senha:</label>  
          <div class="password-container">
            <input type="password" name="confirma-nova-senha" id="senha3">
            <i class="fa-solid fa-eye togglePassword" id="togglePassword3"></i>
          </div>
          <button type="submit">Salvar</button>
        </form>
      </div>
    </section>
    <script src="./js/visualizadorSenha.js"></script>
    <%
    	String mensagem = (String) currentSession.getAttribute("alerta-perfil");
    	if (mensagem != null) {
	%>
		<%= mensagem %>
	<%
    	}
    %>
    <script>
	    setInterval(() => {
	        document.getElementById('mensagem').classList.add('esconder-mensagem')
	        <% currentSession.removeAttribute("alerta-perfil"); %>
	    },2500)
    </script>
  </body>
</html>
<%
    }
%>