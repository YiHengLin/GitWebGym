package admin_mail.controller;

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

import admin_mail.model.MailBean;
import admin_mail.model.MailDAO;



@WebServlet("/selectAllmail")
public class selectAllmail extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		MailDAO maildao = new MailDAO();
		HttpSession session = request.getSession(false);
		if(session==null){
			RequestDispatcher rd = request.getRequestDispatcher("/manage/Admin.jsp");
			rd.forward(request, response);
			return;
		}
		try {
			List<MailBean> mailbeanlist = maildao.getMails();
			System.out.println("list="+mailbeanlist);
			for(MailBean bean : mailbeanlist){
				System.out.println("mail="+bean);
			}
			session.setAttribute("mailList", mailbeanlist);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/manage/sendmail.jsp");
		return;
	}

}
