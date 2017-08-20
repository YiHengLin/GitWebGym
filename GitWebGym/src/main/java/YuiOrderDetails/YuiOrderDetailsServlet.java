package YuiOrderDetails;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import YuiAddOrder.YuiOrderBean;
import YuiAddOrder.YuiOrderDAO;
import YuiEvent.YuiEventBean;
import YuiEvent.YuiEventDAO;
import member.model.MemberBean;



@WebServlet("/YuiOrderDetailsTestServlet")
public class YuiOrderDetailsServlet extends HttpServlet {
	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session==null){
    		response.sendRedirect(request.getContextPath()+"/login/login.jsp");
    		return;
    	}
		
		if((MemberBean)session.getAttribute("memberLoginOK")==null){
			session.setAttribute("target", "/YuiReadDB/enroll.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/YuiReadDB/enroll.jsp");
			rd.forward(request, response);
			return;
		}
		YuiOrderDetailsDAO  yuiorderdetailsdao = new YuiOrderDetailsDAO();
		YuiOrderDAO yuiorderdao = new YuiOrderDAO();
		MemberBean memberBean = (MemberBean)session.getAttribute("memberLoginOK");
		YuiEventBean yuieventbean = (YuiEventBean)session.getAttribute("yuieventbean");
		YuiOrderBean yuiorderbean = yuiorderdao.InsertNewOrder(memberBean, yuieventbean);
		
		yuiorderdetailsdao.InsertNewOrderDetails(yuiorderbean, yuieventbean);
	}

	

}
