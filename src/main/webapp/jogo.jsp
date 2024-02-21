<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="model.Aluno" %>
<%@ page import="model.Questao" %>
<%@page import="model.Alternativa"%>
<%@page import="modelDAO.UsuarioDAO"%>
<%
  	HttpSession currentSession = request.getSession(false);
    Aluno aluno = (Aluno) currentSession.getAttribute("loggedInUser");
    Questao questao = (Questao) currentSession.getAttribute("questaoAtual");
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    if (aluno == null) {
        response.sendRedirect("index.jsp");
    } else {  
    aluno.setXpAtual(usuarioDAO.validarUsuario(aluno).getXpAtual());
%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/modal.css">
    <link rel="shortcut icon" type="image/x-icon" href="imagens/logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <title>Navi</title>
  </head>
  <body class="pagina-jogo">
    <i class="fa-solid fa-xmark botao-voltar" onclick="window.location.href='fecharJogo'"></i>
    <div class="progresso-container">XP: <%= aluno.getXpAtual() %></div>
    <div class="jogo-container">
      <section class="jogo-pergunta">
        <div>
          <h2><%= questao.getEnunciado() %></h2>
        </div>
        <img src="imagens/pergunta-icon.svg">
      </section>
      <section class="jogo-respostas">
        <form method="get" action="proxima" class="jogo-opcoes">
	      <%
	       for (Alternativa opcao : questao.getAlternativas()) {
	      %>
          <input name="opcoes" id="op<%= opcao.getId() %>" type="radio" value="<%= opcao.getId() %>" data-opcao-id="<%= opcao.getId() %>" required><label for="op<%= opcao.getId() %>"><%= opcao.getTexto() %></label>
          <%} %>
          <button type="button" id="abrirModal" class="botao-proxima"><i class="fa-solid fa-circle-right"></i></button>
        </form>
      </section>
    </div>
    
    <div id="alert-modal" class="modal modal-center">
      <div class="modal-content">
        <div class="modal-header">
		  <h2 id="modal-response-text">A resposta correta é: <br>
		  	<%= questao.getTextoAlternativaCorreta() %>
		  </h2>
		</div>
        <div class="modal-footer">
          <button type="button" id="fecharModal" class="btn-primary font-s width-100">OK</button>
        </div>
      </div>
    </div>
    
    <script>
	    const alertModal = document.getElementById("alert-modal")
	    const botaoAbrir = document.getElementById("abrirModal")
	    const botaoFechar = document.getElementById("fecharModal")

		botaoAbrir.addEventListener('click', (event) => {
		  event.preventDefault();
		  const opcoes = document.querySelectorAll(".jogo-opcoes input[type='radio']");
		  let selecionada = false;
		  let opcaoSelecionadaId;
		
		  for (let i =  0; i < opcoes.length; i++) {
		    if (opcoes[i].checked) {
		      selecionada = true;
		      opcaoSelecionadaId = opcoes[i].dataset.opcaoId;
		      break;
		    }
		  }
		
		  if (selecionada) {
		    alertModal.style.display = "flex";
		  }
		});

	    
	    botaoFechar.addEventListener('click', () => {
	      document.querySelector(".jogo-opcoes").submit()
        })
	    
    </script>
  </body>
</html>
<%
    }
%>