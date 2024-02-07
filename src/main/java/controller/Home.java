package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
        if(action.equals("/home")) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("loggedInUser") != null) response.sendRedirect("home.jsp");
            else response.sendRedirect("index.html");
        }
	}
}
