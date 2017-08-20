package Groups.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Groupdetail.model.GroupdetailDAO;


@WebServlet("/group/joingroup")
public class joingroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in joingroup");
		
		String groupid = request.getParameter("groupid");
		String memberid = request.getParameter("memberid");
		
		GroupdetailDAO groupdetailDAO =new GroupdetailDAO();
		
		int is_exist = groupdetailDAO.isInGroup(groupid, memberid);
		System.out.println("is_exist="+is_exist);
		
		if(is_exist==0){
			int result = groupdetailDAO.createGroupDetail(groupid, memberid);
			System.out.println("join group result="+result);
			response.setHeader("Content-Type", "plain/text;charset=UTF-8");
			response.getWriter().print("join group done");
		}else{
			response.setHeader("Content-Type", "plain/text;charset=UTF-8");
			response.getWriter().print("already in group");
		}

		
		
		

		
	}

}
