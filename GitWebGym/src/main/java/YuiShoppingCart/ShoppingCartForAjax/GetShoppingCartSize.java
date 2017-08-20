package YuiShoppingCart.ShoppingCartForAjax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import YuiShoppingCart.YuiShoppingCartService;


@WebServlet("/GetShoppingCartSize")
public class GetShoppingCartSize extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session==null){
			return;
		}
		YuiShoppingCartService cart = (YuiShoppingCartService)session.getAttribute("ShoppingCart");
		if(cart==null){
			return;
		}
		int cartSize = cart.getSize();
		
		response.getWriter().print(cartSize);
		
	}

}
