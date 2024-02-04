<!DOCTYPE html>
<html lang="pt-br">
  <head>
  	<%@ page import="javax.servlet.http.*" %>
	<%@ page import="objetos.Aluno" %>
	<%
    	HttpSession currentSession = request.getSession(false);
    	Aluno aluno = (Aluno) currentSession.getAttribute("loggedInUser");
	%>
  
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="shortcut icon" type="image/x-icon" href="imagens/logo.png">
    <title>Navi | p�gina principal</title>
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
				<button>Perfil</button>
				<button>Trilha</button>
				<button>Tutorial</button>
			</div>
			<p class="copyrights">2024&copy; All rights reserved</p>
		</section>
		<section class="section2">

		</section>
	</body>
</html>