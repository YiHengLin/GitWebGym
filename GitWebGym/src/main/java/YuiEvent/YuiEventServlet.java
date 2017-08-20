package YuiEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import YuiLocations.YuiLocationsDAO;
import YuiRooms.YuiRoomsDAO;
import YuiTrainers.YuiTrainerDAO;

@WebServlet("/TestServlet")
public class YuiEventServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		YuiEventDAO yuieventdao = new YuiEventDAO();
		List<YuiEventBean> yuienentbean = yuieventdao.select();
		YuiTrainerDAO yuitrainerdao = new YuiTrainerDAO();
		YuiRoomsDAO yuiroomsdao = new YuiRoomsDAO();
		YuiLocationsDAO yuilocationdao = new YuiLocationsDAO();
		
//		for (int i = 0; i < test.size(); i++) {
//			
//			System.out.print(test.get(i).getEventno() + ",");
//			System.out.print(test.get(i).getCoursename() + ",");
//			System.out.print(test.get(i).getCoursekind()+ ",");
//			System.out.print(test.get(i).getRoomno() + ",");
//			System.out.print(test.get(i).getE_status() + ",");
//			System.out.print(test.get(i).getEventstart()+",");
//			System.out.print(test.get(i).getEventend()+",");
//			System.out.print(test.get(i).getEnroll() + ",");
//			System.out.println(test.get(i).getCharge());
//		}
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		for(YuiEventBean item : yuienentbean){
			jsonObject =new JSONObject();
			jsonObject.put("eventno", item.getEventno());
			jsonObject.put("trainerid", item.getTrainerid());
			jsonObject.put("t_name",yuitrainerdao.SelectByPrimaryKey(item.getTrainerid()).getT_name());
			jsonObject.put("roomno", item.getRoomno());
			jsonObject.put("locationname", yuilocationdao.select(yuiroomsdao.select(item.getRoomno()).getLocationno()).getLocationname());
			jsonObject.put("room_title", yuiroomsdao.select(item.getRoomno()).getTitle());
			jsonObject.put("title", item.getTitle());
			jsonObject.put("coursekind", item.getCoursekind());
			jsonObject.put("e_status", item.getE_status());
			jsonObject.put("start", item.getEventstart());
			jsonObject.put("end", item.getEventend());
			jsonObject.put("enroll", item.getEnroll());
			jsonObject.put("charge", item.getCharge());
			switch(item.getCoursekind()){
			case "aerobic":
				jsonObject.put("color", "#002147");
				break;
			case "weighttrain":
				jsonObject.put("color", "#990000");
				break;
			case "cycle":
				jsonObject.put("color", "#96B6B8");
				break;
			case "other":
				jsonObject.put("color", "#DAC99D");
				break;
			default:
				continue;	
			}
			jsonArray.put(jsonObject);
		}
		
//		System.out.println(jsonArray.toString());
		
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.getWriter().println(jsonArray.toString());	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
