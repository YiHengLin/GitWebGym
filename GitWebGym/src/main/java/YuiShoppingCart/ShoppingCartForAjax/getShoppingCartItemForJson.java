package YuiShoppingCart.ShoppingCartForAjax;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import YuiShoppingCart.YuiShoppingCartBean;
import YuiShoppingCart.YuiShoppingCartService;


@WebServlet("/getShoppingCartItemForJson")
public class getShoppingCartItemForJson extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		HttpSession session = request.getSession(false);
		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		YuiShoppingCartService cart = (YuiShoppingCartService)session.getAttribute("ShoppingCart");
		
		if(cart==null){
			System.out.println("cart json="+cart);
			jsonObject =new JSONObject();
			jsonObject.put("noShoppingCart", "noShoppingCart");
			jsonArray.put(jsonObject);
			response.getWriter().print(jsonArray);
			return;
		}
		if(cart.getSubtotal() == 0){
			System.out.println(cart);
			jsonObject =new JSONObject();
			jsonObject.put("noShoppingCart", "noShoppingCart");
			jsonArray.put(jsonObject);
			response.getWriter().print(jsonArray);
			return;
		}
		Map<Integer, YuiShoppingCartBean> map = cart.getContent();
		Set<Integer> set = map.keySet();
		for(int n : set){
			jsonObject =new JSONObject();
			int eventno = map.get(n).getEventno();
			String event_title = map.get(n).getEvent_title();
			java.sql.Timestamp eventstart = map.get(n).getEventstart();
			java.sql.Timestamp eventend = map.get(n).getEventend();
			double charge = map.get(n).getCharge();
			String t_name = map.get(n).getT_name();
			String locationname = map.get(n).getLocationname();
			String room_title = map.get(n).getRoom_title();
			
			jsonObject.put("eventno", eventno);
			jsonObject.put("event_title", event_title);
			jsonObject.put("eventstart", eventstart);
			jsonObject.put("eventend", eventend);
			jsonObject.put("charge", charge);
			jsonObject.put("t_name", t_name);
			jsonObject.put("locationname", locationname);
			jsonObject.put("room_title", room_title);
			
			jsonArray.put(jsonObject);
			
		}
		
		System.out.println("items in cart"+jsonArray);
		response.getWriter().print(jsonArray);
		
	}

}
