package Groups.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Groupdetail.model.GroupdetailDAO;
import Groups.model.GroupsDAO;

@WebServlet("/group/deletemygroup")
public class deletemygroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("deletemygroup");
		
		String groupid = request.getParameter("groupid");
		String memberid = request.getParameter("memberid");
		
		System.out.println("groupid="+groupid+", memberid="+memberid);
		GroupdetailDAO  groupdetailDAO = new GroupdetailDAO(); 
		int n1 = groupdetailDAO.deleteDetailsOfGroup(groupid);
		
		GroupsDAO  groupDAO = new GroupsDAO();  
		int n2 = groupDAO.deleteMyGroup(groupid);
		
		System.out.println("n1="+n1+", n2="+n2);
		response.setHeader("Content-Type", "plain/text;charset=UTF-8");
		response.getWriter().println("delete done");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		
	}

}
