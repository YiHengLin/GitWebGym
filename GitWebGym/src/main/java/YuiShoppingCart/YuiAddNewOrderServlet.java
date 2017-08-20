package YuiShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberBean;



@WebServlet("/YuiAddNewOrderServlet")
public class YuiAddNewOrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	PrintWriter out = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			String finalDecision = request.getParameter("finalDecision");
			HttpSession session = request.getSession(false);
			if (session == null) {
				String contextpath = request.getContextPath();
				response.sendRedirect(contextpath + "/YuiReadDB/enroll.jsp");
				return;
			}
			MemberBean memberBean = (MemberBean) session.getAttribute("memberLoginOK");
			if (memberBean == null) {
				String contextpath = request.getContextPath();
				response.sendRedirect(contextpath + "/YuiReadDB/enroll.jsp");
				return;
			}
			YuiShoppingCartService cart = (YuiShoppingCartService) session.getAttribute("ShoppingCart");
			if (cart == null) {
				String contextpath = request.getContextPath();
				response.sendRedirect(contextpath + "/YuiReadDB/enroll.jsp");
				return;
			}
			Double Subtotal = cart.getSubtotal();
			Map<Integer, YuiShoppingCartBean> cartMap = cart.getContent();
			List<YuiShoppingCartBean> orderdetails = new ArrayList<YuiShoppingCartBean>();
			Set<Integer> set = cartMap.keySet();
			for (Integer k : set) {
				orderdetails.add(cartMap.get(k));
			}

			YuiShoppingCartOrderDAO yuishoppingcartorderdao = new YuiShoppingCartOrderDAO();
			
			
			int is_success = yuishoppingcartorderdao.insertOrder(memberBean, Subtotal, orderdetails);
			if(is_success == 1){
				session.removeAttribute("ShoppingCart");
				System.out.println("訂單產生成功");
				out.print("訂單產生成功");
			}else{
				System.out.println("訂單產生失敗");
				out.print("訂單產生失敗");
			}
			
			
//			response.sendRedirect(request.getContextPath() + "/YuiReadDB/YuiShoppingCart/YuiThanksForOrdering.jsp");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("訂單產生失敗");
			out.print("訂單產生失敗");
			e.printStackTrace();
		}

	}

}
