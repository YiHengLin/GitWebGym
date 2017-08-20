package YuiMember;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/YuiLoginServletForAjax")
public class YuiLoginServletForAjax extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		//準備一個PrintWriter寫出資料
		PrintWriter out = response.getWriter();
		
		//使用Json寫出資料
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		
		HttpSession session = request.getSession();
		
		//Map可以拿來做一些判斷
		Map<String,String> errorMsgMap = new HashMap<>();
		
		//已使用Ajax已不需要session
//		session.setAttribute("errorMsgKey", errorMsgMap);

		// 1. 讀取使用者輸入資料(<Input>標籤內的name屬性分別為 userId與pswd
		String userId = request.getParameter("userId");
		String pswd = request.getParameter("pswd");

		// 2. 進行必要的資料轉換
		// 無

		// 3. 檢查使用者輸入資料
		if (userId == null || userId.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號必須輸入");
			jsonObject.put("AccountEmptyError", "帳號必須輸入");
			
		}

		if (pswd == null || pswd.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
			jsonObject.put("PasswordEmptyError", "密碼欄必須輸入");
		}

			 if(!errorMsgMap.isEmpty()){
//			 RequestDispatcher rd =
//			 request.getRequestDispatcher("/YuiReadDB/login.jsp");
//			 rd.forward(request, response);
	
//			 response.getWriter().println(jsonArray.toString());
			 jsonarray.put(jsonObject);
			 out.print(jsonarray.toString());
			 return;
		 }

		// 4. 進行 Business Logic 運算
		YuiLoginService yuiloginservice = new YuiLoginService();
		YuiMemberBean yuimemberbean = yuiloginservice.checkIDPassword(userId, pswd);
		if (yuimemberbean != null) {
			session.setAttribute("memberLoginOK", yuimemberbean);
			
			
			jsonObject.put("memberLoginOK", "登陸成功");
			jsonarray.put(jsonObject);
			out.print(jsonarray.toString());
			return;
		} else {
			errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
			jsonObject.put("LoginError", "該帳號不存在或密碼錯誤");
			
			
			jsonarray.put(jsonObject);
			out.print(jsonarray.toString());
			return;
		}
		
		

		// 5.依照 Business Logic 運算結果來挑選適當的畫面
		// if(errorMsgMap.isEmpty()){
		// String contextPath = getServletContext().getContextPath();
		// String target = (String)session.getAttribute("target");
		// if(target!=null){
		// session.removeAttribute("target");
		// response.sendRedirect(response.encodeRedirectURL(contextPath+target));
		// }else{
		// response.sendRedirect(contextPath+"/YuiReadDB/YUITEST.jsp");
		// }
		// return;
		// }else{
		// RequestDispatcher rd = request
		// .getRequestDispatcher("/YuiReadDB/login.jsp");
		// rd.forward(request, response);
		// return;
		// }

	}

}
