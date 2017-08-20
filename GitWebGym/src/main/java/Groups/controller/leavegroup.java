package Groups.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Groupdetail.model.GroupdetailDAO;


@WebServlet("/group/leavegroup")
public class leavegroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String groupid = request.getParameter("groupid");
		String memberid = request.getParameter("memberid");
		GroupdetailDAO groupdetailDAO = new GroupdetailDAO();
		int resulut = groupdetailDAO.deleteGroupDetail(groupid, memberid);
		System.out.println("delete groupdetail resulut="+resulut);
		System.out.println("leave group");
	}

}
