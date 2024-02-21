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
			<form class="cadastro-form" method="post" action="insert">
				<label for="nome">Nome</label>
				<input type="text" name="nome" required>
				<label for="username">Usuário</label>
				<input type="text" name="username" required>
				<label for="telefone">Telefone</label>
				<input type="text" name="telefone">
				<label for="email">Email</label>
				<input type="text" name="email" required>
				<div class="area-senha">
					<span>
						<label for="senha">Senha</label>
						<div class="password-container pass-view-cadastro">
							<input type="password" name="senha" id="senha" required>
							<i class="fa-solid fa-eye togglePassword" id="togglePassword"></i>
						</div>
					</span>
					<span>
            			<label for="confirmarSenha">Confirmar Senha</label>
						<div class="password-container pass-view-cadastro">
							<input type="password" name="confirmarSenha" id="senha2" required>
							<i class="fa-solid fa-eye togglePassword" id="togglePassword2"></i>
						</div>
					</span>
				</div>
				<div class="area-sexo">
					<input id="feminino" type="radio" name="genero" value="F" required>
					<label for="feminino">Feminino</label> 
					<input id="masculino" type="radio" name="genero" value="M" required>
					<label for="masculino">Masculino</label> 
				</div>
				<p>Já possui conta? <a href="index.jsp">Faça login</a></p>
				<button type="submit">Cadastrar</button>
			</form>
		</section>
	    <script src="./js/visualizadorSenha.js"></script>
	    <%
	    	String mensagem = (String) currentSession.getAttribute("alerta-cadastro");
	    	if (mensagem != null) {
		%>
			<%= mensagem %>
		<%
	    	}
	    %>
	    <script>
	    	setInterval(() => {
	        	document.getElementById('mensagem').classList.add('esconder-mensagem')
		        <% currentSession.removeAttribute("alerta-cadastro"); %>
	    	},2500)
	    </script>
	</body>
</html>

<%
    }
%>