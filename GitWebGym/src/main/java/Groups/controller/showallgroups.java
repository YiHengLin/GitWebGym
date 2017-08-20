package Groups.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;


import Groups.model.GroupsDAO;


@WebServlet("/showallgroups")
public class showallgroups extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		
		
    	GroupsDAO  groupDAO = new GroupsDAO();   
    	List<Map<String, String>>  groups = groupDAO.getAllGroups();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
    	
		for(Map<String, String> group : groups){
			jsonObject =new JSONObject();
			jsonObject.put("groupid",group.get("groupid"));
			jsonObject.put("creator",group.get("creator"));
			jsonObject.put("title",group.get("title"));
			jsonObject.put("joins",group.get("joins"));
			
			jsonArray.put(jsonObject);
		}
    	
     	session.setAttribute("groups", jsonArray);
     	
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.getWriter().println(jsonArray.toString());
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
