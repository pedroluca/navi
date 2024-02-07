<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="model.Aluno" %>
<%
    HttpSession currentSession = request.getSession(false);
    Aluno aluno = (Aluno) currentSession.getAttribute("loggedInUser");

    if (aluno == null) {
        response.sendRedirect("index.html");
    } else {
%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/carrossel.css">
    <link rel="shortcut icon" type="image/x-icon" href="imagens/logo.png">
    <title>Navi | tutorial</title>
    
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
    <section class="section2">
		<h1 class="system-name">Tutorial</h1>
		<div class="carousel-container">
	        <div class="carousel">
	            <div class="carousel-item"><img src="./imagens/sla.jpg" alt="Imagem 1"></div>
	            <div class="carousel-item"><img src="./imagens/profile-icon.jpg" alt="Imagem 2"></div>
	            <div class="carousel-item"><img src="imagem3.jpg" alt="Imagem 3"></div>
	        </div>
	
	        <button class="prev" onclick="prevSlide()">&#10094;</button>
	        <button class="next" onclick="nextSlide()">&#10095;</button>
	    </div>
    </section>
    <script>
        let currentSlide = 0;

        function showSlide(index) {
            const carousel = document.querySelector('.carousel');
            const totalSlides = document.querySelectorAll('.carousel-item').length;

            if (index >= totalSlides) {
                currentSlide = 0;
            } else if (index < 0) {
                currentSlide = totalSlides - 1;
            } else {
                currentSlide = index;
            }

            const translateValue = -currentSlide * 100 + '%';
            carousel.style.transform = 'translateX(' + translateValue + ')';
        }

        function prevSlide() {
            showSlide(currentSlide - 1);
        }

        function nextSlide() {
            showSlide(currentSlide + 1);
        }
    </script>
  </body>
</html>

<%
    } // End of else block
%>
