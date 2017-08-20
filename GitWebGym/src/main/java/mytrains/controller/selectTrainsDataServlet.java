package mytrains.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import member.model.MemberBean;
import mytrains.model.selftrainsBean;
import mytrains.model.selftrainsDAO;
import mytrains.model.traindetailsBean;
import mytrains.model.traindetailsDAO;



@WebServlet("/selectdata.do")
public class selectTrainsDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 定義存放錯誤訊息的 Collection物件
		HttpSession session = request.getSession(false);  
		if(session==null){
    		response.sendRedirect(request.getContextPath()+"/login/login.jsp");
    		return;
    	}
//		System.out.println(session);
		// 設定輸入資料的編碼
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberBean member=(MemberBean)session.getAttribute("memberLoginOK");
		
        //查出會員id之List型態的selftrainno資料
		JSONObject jsonObject=null;
		JSONArray jsonarray = new JSONArray();
		selftrainsDAO mfio = new selftrainsDAO();
		
		try {
			List<selftrainsBean> selftrainBeanList = mfio.selectselftrainno2(member.getMemberID());
			for(selftrainsBean selftrainBean:selftrainBeanList){
				String selfnum = selftrainBean.getSelftrainno();
				String trainday = selftrainBean.getTrainday();
				traindetailsDAO mfio2 = new traindetailsDAO();
				List<traindetailsBean> traindetailsBeanList = mfio2.selectselftrainno2(selfnum);
				for(traindetailsBean traindetailsBean:traindetailsBeanList){
					String traintitle =traindetailsBean.getTraintitle();
					String trainset=traindetailsBean.getTrainset();
					String trainweight=traindetailsBean.getTrainweight();
					
					jsonObject= new JSONObject();
					jsonObject.put("trainday", trainday);
					jsonObject.put("traintitle", traintitle);
					jsonObject.put("trainset",trainset);
					jsonObject.put("trainweight", trainweight);
					
					jsonarray.add(jsonObject);			
				}		
			}
			
			 out.print(jsonarray);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

