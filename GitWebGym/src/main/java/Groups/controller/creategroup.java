package Groups.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Groups.model.GroupsBean;
import Groups.model.GroupsDAO;
import member.model.MemberBean;
import trainer.model.TrainerBean;

/**
 * Servlet implementation class creategroup
 */
@WebServlet("/group/creategroup")
public class creategroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession(false);
    	if(session==null){
    		response.sendRedirect(request.getContextPath()+"/login/login.jsp");
    		return;
    	}
    	String contextPath = request.getContextPath(); 
    	
    	
    	MemberBean memberBean = (MemberBean)session.getAttribute("memberLoginOK");     
    		
        String groupid = request.getParameter("newgroupid");
        String memberid = request.getParameter("memberid");
        System.out.println("memberid="+memberid);
        String title = request.getParameter("newgrouptitle");
        
    	GroupsDAO groupDAO = new GroupsDAO();
    	groupDAO.createGroup(groupid,memberid,title);

    	Collection<Map<String, String>>  groups = groupDAO.getMyGroups(memberid) ;
    	
    	session.setAttribute("groups", groups);
    	response.sendRedirect(contextPath+"/group/create.jsp");
		
	}

}
