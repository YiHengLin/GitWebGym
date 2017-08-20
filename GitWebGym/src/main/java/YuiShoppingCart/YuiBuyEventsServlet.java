package YuiShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import YuiLocations.YuiLocationsDAO;


import YuiRooms.YuiRoomsDAO;
import YuiTrainers.YuiTrainerDAO;
import member.model.MemberBean;

@WebServlet("/YuiAddShoppingCartEventsServlet")
public class YuiBuyEventsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
 
		HttpSession session = request.getSession(false);
		if (session != null) {

			MemberBean memberBean = (MemberBean) session.getAttribute("memberLoginOK");
			if (memberBean == null) {
//				String contextpath = request.getContextPath();
//				String servletpath = request.getServletPath();
//				session.setAttribute("target", servletpath);
//				RequestDispatcher rd = request.getRequestDispatcher("/YuiReadDB/login.jsp");
//				rd.forward(request, response);
				
				//已用Ajax無須轉址
				//用PrintWriter寫出訊息讓前端程式能夠進行邏輯判斷
				out.print("尚未登入");
				return;
			}

			YuiShoppingCartService cart = (YuiShoppingCartService) session.getAttribute("ShoppingCart");
			if (cart == null) {
				// 就新建ShoppingCart物件
				cart = new YuiShoppingCartService();
				// 將此新建ShoppingCart的物件放到session物件內
				session.setAttribute("ShoppingCart", cart); // ${ShoppingCart.zzz}
			}
			//抓取要加入購物車的課程資訊
			String eventnostg = request.getParameter("eventno");
			String trainerid = request.getParameter("trainerid");
			String roomno = request.getParameter("roomno");
			String title = request.getParameter("title");
			String coursekind = request.getParameter("coursekind");
			String e_status = request.getParameter("e_status");
			String stringstart = request.getParameter("start");
			String stringend = request.getParameter("end");
			String enroll = request.getParameter("enroll");
			String chargestg = request.getParameter("charge");
			String t_name = request.getParameter("t_name");
			String locationname = request.getParameter("locationname");
			String room_title = request.getParameter("room_title");
			
			
			Timestamp start = new Timestamp(System.currentTimeMillis());
			start = Timestamp.valueOf(stringstart);

			Timestamp end = new Timestamp(System.currentTimeMillis());
			end = Timestamp.valueOf(stringend);

			int eventno = 0;
			eventno = Integer.parseInt(eventnostg.trim());

			double charge = 0;
			charge = Double.parseDouble(chargestg.trim());

			YuiEventBean yuieventbean = new YuiEventBean(eventno, trainerid, roomno, title, coursekind, e_status, start,
					end, enroll, charge);


			
//			YuiTrainerDAO yuitrainerdao = new YuiTrainerDAO();
//			YuiLocationsDAO yuilocationsdao = new YuiLocationsDAO();
//			YuiRoomsDAO yuiroomsdao = new YuiRoomsDAO();
			String event_title = yuieventbean.getTitle();
			Timestamp eventstart = yuieventbean.getEventstart();
			Timestamp eventend = yuieventbean.getEventend();
//			String t_name = yuitrainerdao.SelectByPrimaryKey(yuieventbean.getTrainerid()).getT_name();
//			String room_title = yuiroomsdao.select(yuieventbean.getRoomno()).getTitle();
//			String locationname = yuilocationsdao.select(yuiroomsdao.select(yuieventbean.getRoomno()).getLocationno())
//					.getLocationname();

			YuiShoppingCartBean yuishoppingcartbean = new YuiShoppingCartBean(eventno, event_title, eventstart,
					eventend, charge, t_name, locationname, room_title);

			Boolean addboolean = cart.addToCart(yuieventbean.getEventno(), yuishoppingcartbean);
			if (addboolean == true) {
				//response.sendRedirect(request.getContextPath() + "/YuiReadDB/YUITEST.jsp");
				//已用Ajax無須轉址
				//用PrintWriter寫出訊息讓前端程式能夠進行邏輯判斷
				out.print("新增成功");
				
			}

			if (addboolean == false) {
				//用PrintWriter寫出訊息讓前端程式能夠進行邏輯判斷
				out.print("新增失敗，已加入過此課程");
			}

		}
	}

}
