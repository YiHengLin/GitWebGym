package mytrains.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import member.model.MemberBean;
import mytrains.model.selftrainsBean;
import mytrains.model.selftrainsDAO;
import mytrains.model.traindetailsBean;
import mytrains.model.traindetailsDAO;



@WebServlet("/updatedata.do")
public class updateTrainsDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 定義存放錯誤訊息的 Collection物件
		HttpSession session = request.getSession(false);
		if(session==null){
    		response.sendRedirect(request.getContextPath()+"/login/login.jsp");
    		return;
    	}
		Collection<String> errorMessage = new ArrayList<String>();
		request.setAttribute("ErrorMsg", errorMessage);

		request.setCharacterEncoding("UTF-8"); 
		MemberBean member=(MemberBean)session.getAttribute("memberLoginOK");
		
		String oldtitle = request.getParameter("oldtraintitle");

		String udday = request.getParameter("oldtrainday");
		if (udday == null || udday.trim().length() == 0) {
			errorMessage.add("修改日期欄必須輸入");
		}
		
		String udtitle = request.getParameter("udTitle");
		if (udtitle == null || udtitle.trim().length() == 0) {
			errorMessage.add("更改項目欄必須輸入");
		}
				
		String udset = request.getParameter("udSet");
		if (udset == null || udset.trim().length() == 0) {
			errorMessage.add("更改組數欄必須輸入");
		}
		
		String udweight = request.getParameter("udWeight");
		if (udweight == null || udweight.trim().length() == 0) {
			errorMessage.add("更改重量欄必須輸入");
		}		
		System.out.println("id="+member.getMemberID());
		System.out.println("舊title="+oldtitle);
		System.out.println("day="+udday);
		System.out.println("新項目="+udtitle);
		System.out.println("組數="+udset);
		System.out.println("重量="+udweight);
						
		// 如果有錯誤，呼叫view元件，送回錯誤訊息
//		if (!errorMessage.isEmpty()) {
//			RequestDispatcher rd = request
//					.getRequestDispatcher("/insertdata/InsertDataError.jsp");
//			rd.forward(request, response);
//			return;
//		}
		
		//先查詢欲修改的會員及日期所屬的selftrainno
		String selfno = null;
		selftrainsBean sb = new selftrainsBean(member.getMemberID(), udday );
		try {
			selftrainsDAO mfio = new selftrainsDAO();
			selfno = mfio.selectselftrainno(sb).getSelftrainno();  //查詢屬於登入會員及訓練日期之selfrrainno
			System.out.println("selfno="+selfno);
			request.setAttribute("updateselftrainlBean", sb);
		} catch (SQLException e) {
				errorMessage.add(e.getMessage());
			}
		
		//用上面查詢到的selftrainno來執行訓練明細的修改
		traindetailsBean ub = new traindetailsBean(oldtitle, selfno, udtitle, udset, udweight);
		try{
			traindetailsDAO mfio1 = new traindetailsDAO();
			mfio1.updatetraindetail(ub);
		}catch(SQLException e) {
			errorMessage.add(e.getMessage());
		}
	}
	}

