package mytrains.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberBean;
import mytrains.model.selftrainsBean;
import mytrains.model.selftrainsDAO;
import mytrains.model.traindetailsBean;
import mytrains.model.traindetailsDAO;




@WebServlet("/deletedata.do")
public class deleteTrainsDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null){
    		response.sendRedirect(request.getContextPath()+"/login/login.jsp");
    		return;
    	}
		Collection<String> errorMessage = new ArrayList<String>();
		request.setAttribute("ErrorMsg", errorMessage);
		
		request.setCharacterEncoding("UTF-8"); 
		MemberBean member=(MemberBean)session.getAttribute("memberLoginOK");  //	
					
		String traintitle = request.getParameter("traintitle");
		
		String trainday = request.getParameter("trainday");
		
		//先查詢欲刪除selftrainno
		String selfno = null;
		selftrainsBean sb = new selftrainsBean(member.getMemberID(), trainday );
		try {
			selftrainsDAO mfio = new selftrainsDAO();
			selfno = mfio. selectselftrainno(sb).getSelftrainno();
//			System.out.println("selfno="+selfno);
			request.setAttribute("updateselftrainlBean", sb);
		} catch (SQLException e) {
				errorMessage.add(e.getMessage());
			}
		
		//刪除訓練明細內容
		traindetailsBean db1 = new traindetailsBean(selfno, traintitle);
		try{
			traindetailsDAO mfio1 = new traindetailsDAO();
			mfio1.deletetraindetail(db1);
		}catch(SQLException e) {
			errorMessage.add(e.getMessage());
		}
			
		
		//
		selftrainsBean db2= new selftrainsBean();
	    db2.setSelftrainno(selfno);
	    
	    selftrainsBean db3 = new selftrainsBean();
		db3.setMemberid(member.getMemberID());
		db3.setTrainday(trainday);
		
		try{
			traindetailsDAO mfio1 = new traindetailsDAO();
			selftrainsDAO mfio2 =new selftrainsDAO();
			if(mfio1.selecttraindetail(db2)==null){
				mfio2.deletetraindate(db3);
//				System.out.println("欲刪除之selfno知明細內容="+mfio1.selecttraindetail(db2));
			}
			
			
		}catch(SQLException e) {
			errorMessage.add(e.getMessage());
		}
			return;
	}
}
