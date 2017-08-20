package YuiShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/YuiUpdateShoppingCartItem")
public class YuiUpdateShoppingCartItem extends HttpServlet {
	
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session==null){
    		response.sendRedirect(request.getContextPath()+"/login/login.jsp");
    		return;
    	}
		
		if (session.getAttribute("memberLoginOK") == null) {   
			String contextpath = request.getContextPath();
			String servletpath = request.getServletPath();
			session.setAttribute("target", servletpath);// 使用逾時
			RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
			rd.forward(request, response);
			return;
		}
		
		YuiShoppingCartService cart = (YuiShoppingCartService)session.getAttribute("ShoppingCart");
		
		if(cart==null){
			response.sendRedirect(getServletContext().getContextPath()+"/courses/enroll.jsp");
			return;
		}
		
		String cmd = request.getParameter("cmd");
		String eventIDstr = request.getParameter("eventID");
		int eventno = Integer.parseInt(eventIDstr.trim());
		
		if(cmd.equalsIgnoreCase("DEL")){
			int i = cart.deleteEvent(eventno);
//			RequestDispatcher rd = request.getRequestDispatcher("/YuiReadDB/YuiShoppingCart/ShowCartContent.jsp");
//			rd.forward(request, response);
			out.print(i);
			return;
		}
		
		
	
	}

}
