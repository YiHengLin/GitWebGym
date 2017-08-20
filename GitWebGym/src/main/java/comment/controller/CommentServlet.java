package comment.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.model.CommBean;
import comment.model.commDAO;


/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/comment.do")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
    public CommentServlet() {
     
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String contextPath = request.getContextPath();
		
		if (session == null) {    
			response.sendRedirect(contextPath + "/index.jsp");
			return;
		}
		Collection<String> errorMsg=new ArrayList<String>();
		
	    String Act=request.getParameter("act");
	    String id=request.getParameter("memberId");
	    String comm=request.getParameter("comm");
	    Timestamp ts = new Timestamp(System.currentTimeMillis());
	    
	    int ArticleNo;
		try{
			 ArticleNo=Integer.parseInt(request.getParameter("ArticleNo"));
		     System.out.println("no="+ArticleNo);
		}catch(NumberFormatException e){
			
			 ArticleNo=0;
		}
		 int commid;
		 try{
			 commid=Integer.parseInt(request.getParameter("commid"));
		    
		}catch(NumberFormatException e){
			commid=0;
			 
		}
	    
	    if(Act.equalsIgnoreCase("post")){
	
//		if(id==null||id.trim().length()==0){
//			errorMsg.add("id is empty");
//		}
		
		if(comm==null||comm.trim().length()==0){
			errorMsg.add("comment is empty");
		}
	   
	    if(!errorMsg.isEmpty()){
	    	
      	  RequestDispatcher rd=request.getRequestDispatcher("/blog/articles.jsp");
      	  System.out.println(errorMsg);
      	  rd.forward(request, response);
      	  return;
        }
		
		CommBean cb=new CommBean(ArticleNo,id,comm,ts);
	    
		try{
			commDAO cDAO=new commDAO();
			cDAO.PostComm(cb);
		}catch (SQLException e) {
			errorMsg.add("LoginError"+ e.getMessage());
			System.out.println(errorMsg);
			e.printStackTrace();
		}
		
		if (errorMsg.isEmpty()) {
  			session.setAttribute("MessageInsertOK", "留言成功");
  			response.sendRedirect(contextPath + "/blog/articles.jsp");
  			return;
  		} else {
  			response.sendRedirect(contextPath + "/blog/articles.jsp");
  			return;
  		}
	    
	 }else if(Act.equalsIgnoreCase("del")){
		
		 commDAO cDAO=new commDAO();
		 try {
			cDAO.DeletComm(commid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			response.sendRedirect(contextPath + "/blog/articles.jsp");
			return;
		 
	 }else if(Act.equalsIgnoreCase("update")){
		  
		 
			if(comm==null||comm.trim().length()==0){
				errorMsg.add("comment is empty");
			}
		   
		    if(!errorMsg.isEmpty()){
		    	
	  			response.sendRedirect(contextPath + "/blog/articles.jsp");
	  			return;
	        }
			
		CommBean cb=new CommBean(commid,ArticleNo,id,comm,ts);
		 
		 commDAO cDAO=new commDAO();
		 try {
			cDAO.ModifyComm(cb);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			response.sendRedirect(contextPath + "/blog/articles.jsp");
			return;
		 
	 }

  }
}	
