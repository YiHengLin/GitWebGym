package YuiShoppingCart;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import YuiOrderDetails.YuiOrderDetailsBean;
import YuiOrderDetails.YuiOrderDetailsDAO;
import YuiRooms.YuiRoomsDAO;
import YuiTrainers.YuiTrainerDAO;


@WebServlet("/YuiShowOrderDetailServlet")
public class YuiShowOrderDetailServlet extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	HttpSession session = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//先拿到orderno
		String ordernostg = request.getParameter("orderno");
		int orderno = Integer.parseInt(ordernostg);
		
		//把需要用的DAO new出來
		YuiOrderDAO yuiorderdao = new YuiOrderDAO();
		YuiOrderDetailsDAO yuiorderdetailsdao = new YuiOrderDetailsDAO();
		YuiTrainerDAO yuitrainerdao = new YuiTrainerDAO();
		YuiRoomsDAO yuiroomsdao = new YuiRoomsDAO();
		YuiLocationsDAO yuilocationsdao = new YuiLocationsDAO();
		YuiEventDAO yuieventdao = new YuiEventDAO();
		
		//訂單上的資訊YuiShoppingCartBean上面都有，所以直接拿來用
		YuiShoppingCartBean yuishoppingcartbean =null;
		
		//因為一張訂單有好多個明細物件，所以用List裝
		//這個後面會放入request的scope裡面，前端用EL的foreach拿出來顯示
		List<YuiShoppingCartBean> yuishoppingcartbeanlist = new ArrayList<YuiShoppingCartBean>();
		
		
		//拿到yuiorderbean，這個bean之後也會放入request的scpoe，負責顯示訂購日期、訂單編號、總金額
		YuiOrderBean yuiorderbean = yuiorderdao.selectFromOrderNo(orderno);
		
		//拿到一串訂單明細，這個List就是這個訂單的所有明細物件
		List<YuiOrderDetailsBean> yuiorderdetailbeanlist = yuiorderdetailsdao.select(orderno);
		
		
		//用for迴圈把一個訂單明細的各個屬性，放到YuiShoppingCartBean裡面
		//但是訂單明細表格能顯示的只有金額而已，所以不夠的部分再用DAO做查詢再放進去YuiShoppingCartBean裡面
		for(YuiOrderDetailsBean temp : yuiorderdetailbeanlist){

			int tempeventno = temp.getEventno();
			YuiEventBean yuieventbean = yuieventdao.selectFromEventNo(tempeventno);
			String tempeventtitle = yuieventbean.getTitle();
			Timestamp tempeventstart = yuieventbean.getEventstart();
			Timestamp tempeventend = yuieventbean.getEventend();
			String tempt_name = yuitrainerdao.SelectByPrimaryKey(yuieventbean.getTrainerid()).getT_name();
			String temproomtitle = yuiroomsdao.select(yuieventbean.getRoomno()).getTitle();
			String templocationname = yuilocationsdao.select(yuiroomsdao.select(yuieventbean.getRoomno()).getLocationno()).getLocationname();
			double tempcharge = temp.getCharge();
			yuishoppingcartbean = new YuiShoppingCartBean(tempeventno, tempeventtitle, tempeventstart, tempeventend, tempcharge, tempt_name, templocationname, temproomtitle);
			yuishoppingcartbeanlist.add(yuishoppingcartbean);
			
		}
		request.setAttribute("orderDetails", yuishoppingcartbeanlist);
		request.setAttribute("orderBean", yuiorderbean);
		
		RequestDispatcher rd = request.getRequestDispatcher("/courses/shoppingcart/showorderdetail.jsp");
		rd.forward(request, response);
	}

}
