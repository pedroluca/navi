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
    <title>Cadastre-se em nossa plataforma!</title>
  </head>
	<body class="pagina-cadastro">
		<section class="section1">
			<h1 class="system-name">Navi</h1>
      		<img src="imagens/logo.png" class="logo-l" alt="Logo Navi">
		</section>
		<section class="section2">
			<h2>Bem-vindo(a) ao Navi</h2>
			<form class="cadastro-form" name="frmCadastro" action="insert">
				<label for="nome">Nome</label>
				<input type="text" name="nome">
				<label for="username">Usuário</label>
				<input type="text" name="username">
				<label for="telefone">Telefone</label>
				<input type="text" name="telefone">
				<label for="email">Email</label>
				<input type="text" name="email">
				<div class="area-senha">
					<span>
						<label for="senha">Senha</label>
						<div class="password-container pass-view-cadastro">
							<input type="password" name="senha" id="senha">
							<i class="fa-solid fa-eye" id="togglePassword"></i>
						</div>
					</span>
					<span>
            			<label for="confirmarSenha">Confirmar Senha</label>
						<div class="password-container pass-view-cadastro">
							<input type="password" name="confirmarSenha" id="senha">
							<i class="fa-solid fa-eye" id="togglePassword"></i>
						</div>
					</span>
				</div>
				<div class="area-sexo">
					<input id="feminino" type="radio" name="genero" value="F">
					<label for="feminino">Feminino</label> 
					<input id="masculino" type="radio" name="genero" value="M">
					<label for="masculino">Masculino</label> 
				</div>
				<p>Já possui conta? <a href="index.jsp">Faça login</a></p>
				<button onclick="validarCadastro()">Cadastrar</button>
			</form>
		</section>
		<script src="./js/app.js"></script>
    	<script src="./js/visualizadorSenha.js"></script>
	</body>
</html>

<%
    } // End of else block
%>