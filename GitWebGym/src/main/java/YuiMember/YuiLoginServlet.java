package YuiMember;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/YuiLoginServlet")
public class YuiLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Map errorMsgMap = new HashMap();
		session.setAttribute("errorMsgKey", errorMsgMap);
		
		// 1. 讀取使用者輸入資料(<Input>標籤內的name屬性分別為 userId與pswd
		String userId = request.getParameter("userId");
		String pswd = request.getParameter("pswd");
		
		// 2. 進行必要的資料轉換
		//無
		
		
		// 3. 檢查使用者輸入資料
		if(userId == null || userId.trim().length()==0){
			errorMsgMap.put("AccountEmptyError", "帳號必須輸入");
		}
		
		if(pswd == null || pswd.trim().length() ==0 ){
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
		
		if(!errorMsgMap.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/YuiReadDB/login.jsp");
			rd.forward(request, response);
			return;
		}
		// 4. 進行 Business Logic 運算
		YuiLoginService yuiloginservice = new YuiLoginService();
		YuiMemberBean yuimemberbean = yuiloginservice.checkIDPassword(userId, pswd);
		if(yuimemberbean!=null){
			session.setAttribute("memberLoginOK", yuimemberbean);
			
		}else{
			errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
		}
		
		// 5.依照 Business Logic 運算結果來挑選適當的畫面
		if(errorMsgMap.isEmpty()){
			String contextPath = getServletContext().getContextPath();
			String target = (String)session.getAttribute("target");
			if(target!=null){
				session.removeAttribute("target");
				response.sendRedirect(response.encodeRedirectURL(contextPath+target));
			}else{
				response.sendRedirect(contextPath+"/YuiReadDB/enroll.jsp");
			}
			return;
		}else{
			RequestDispatcher rd = request
					.getRequestDispatcher("/YuiReadDB/login.jsp");
			rd.forward(request, response);
			return;
		}
		
	}

}
