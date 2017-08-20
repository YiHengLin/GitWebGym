package Groups.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Groups.model.GroupsDAO;

/**
 * Servlet implementation class editmygroup
 */
@WebServlet("/group/editmygroup")
public class editmygroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("editmygroup");
		
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		String groupid = request.getParameter("groupid");
		String title = request.getParameter("title");
		
		GroupsDAO groupDAO = new GroupsDAO();
		int result = groupDAO.updateGroup(title, groupid);
		
		response.setHeader("Content-Type", "plain/text;charset=UTF-8");
		response.getWriter().println("update done");
	}

}
