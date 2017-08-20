package admin_mail.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import Event.model.EventBean;
//import Event.model.EventService;

import admin_mail.model.*;

@WebServlet("/SendMailController")
public class SendMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		
		//若使用以下方法須建立MailService.java
//		if ("MailListAll".equals(action)) {
//
//			MailService mailSvc = new MailService();
//			List<MailBean> list = mailSvc.getAll();
//			request.setAttribute("mailList", list);
//			request.getRequestDispatcher("/sendmail.jsp").forward(request, response);
//
//		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         HttpSession session =request.getSession();
         
         Map<String,String> errorMsg =new HashMap<String,String>();
         
         request.setAttribute("errorMsg", errorMsg );
         String title=request.getParameter("title");
         String kind=request.getParameter("kind");
         String content=request.getParameter("content");
         String id=request.getParameter("memberid");
         Timestamp ts = new Timestamp(System.currentTimeMillis());
         
         
         if(title==null||title.trim().length()==0){
       	  errorMsg.put("titleError","請輸入標題");
         }
         
         if(kind==null||kind.trim().length()==0){
          	  errorMsg.put("kindError","請選擇分類");
            }
         if(content==null||content.trim().length()==0){
       	  errorMsg.put("contentError","內容不得為空白");
         }
         
         
         if(!errorMsg.isEmpty()){
       	  RequestDispatcher rd=request.getRequestDispatcher("/sendmail.jsp");
       	  rd.forward(request, response);
       	  return;
         }
         
         System.out.println("id = " + id);
         
         MailBean mb=new MailBean(id,title,content,ts,kind);
        
         
         try {
         	MailDAO mDAO =new MailDAO() ; 
			mDAO.sendMail(mb);
			
			
		
		}  catch (SQLException e) {
			errorMsg.put("LoginError",
					"LoginServlet->SQLException:" + e.getMessage());
			e.printStackTrace();
		}
         
         if (errorMsg.isEmpty()) {
 			session.setAttribute("MessageInsertOK", "新增郵件成功");
 			response.sendRedirect(request.getContextPath() + "/reportDone.jsp");
 			return;
 		} else {
 			RequestDispatcher rd = request.getRequestDispatcher("sendmail.jsp");
 			rd.forward(request, response);
 			return;
 		}
         
         
   }	
		
		
	

}
