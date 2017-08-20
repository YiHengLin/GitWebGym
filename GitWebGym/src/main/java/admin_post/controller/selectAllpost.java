package admin_post.controller;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin_post.model.PostBean;
import admin_post.model.PostDAO;

@WebServlet("/selectAllpost")
public class selectAllpost extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		PostDAO postdao = new PostDAO();
		HttpSession session = request.getSession(false);
		String action=request.getParameter("action");
		
		if(session==null){
			RequestDispatcher rd = request.getRequestDispatcher("/manage/Admin.jsp");
			rd.forward(request, response);
			}
		
		try {
			List<PostBean> postbeanlist = postdao.getPosts();
			for(PostBean k : postbeanlist){
				System.out.println(k);
			}
			
			
			
			session.setAttribute("postList", postbeanlist);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if("postList".equals(action)){
			response.sendRedirect(request.getContextPath()+"/manage/sendpost.jsp");
			return;
		}else{
			response.sendRedirect(request.getContextPath()+"/news/allnews.jsp");
			return;	
		}
		

	}
}
