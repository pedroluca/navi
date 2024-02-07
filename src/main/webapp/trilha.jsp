<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="model.Aluno" %>
<%
    HttpSession currentSession = request.getSession(false);
    Aluno aluno = (Aluno) currentSession.getAttribute("loggedInUser");

    if (aluno == null) {
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
	   <title>Navi | trilha</title>
	 </head>
	<body class="pagina-trilha">
        <section class="section1">
            <div>
                <img class="user-icon" src="imagens/profile-icon.jpg">
                <span class="user-info">
                    <h2 class="user-name"><%= aluno.getNome() %></h2>
                    <h3 class="user-username"><%= aluno.getUsername() %></h3>
                </span>
                <span class="user-xp">
                    <p>EXP:</p>
                    <p>10000</p>
                </span>
            </div>
        </section>
        <section class="section2">
            
        </section>
	</body>
</html>

<%
    }
%>