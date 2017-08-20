package logout.controller;

import java.io.IOException;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null){
    		response.sendRedirect(request.getContextPath()+"/login/login.jsp");
    		return;
    	}
		String context = request.getContextPath();
		session.removeAttribute("errMsg");
		session.removeAttribute("AdminOK");
		session.removeAttribute("memberLoginOK");
		session.removeAttribute("trainerLoginOK");
		session.removeAttribute("loginidentity");
		session.invalidate();
		
		response.sendRedirect(context+"/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
