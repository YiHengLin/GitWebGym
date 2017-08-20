package login.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import misc.CipherUtils;
import member.model.MemberBean;
import member.model.memberDAO_old;
import trainer.model.TrainerBean;
import trainer.model.TrainerService;

@WebServlet("/login/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrainerService trainerService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		trainerService = (TrainerService) context.getBean("trainerService");
	}
	
	
	private String getSHA1(String password){
		String key = "temmujinfitness!"; // 對稱式金鑰
//		byte[] iv = new byte[16]; // 初始向量
		byte[] iv = "0012254776511111".getBytes();
		SecureRandom srnd = new SecureRandom();
//		srnd.nextBytes(iv);
		String plainText = password;
		String cipherText = "";
		String decryptedString = "";
		String SHA1 = "";
		try {
			// encryptString(key, plainText, iv) : 將明文轉換為密文
			cipherText = CipherUtils.encryptString(key, plainText, iv);
			
			SHA1 = CipherUtils.getStringSHA1(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("登入原始字串: " + plainText);
		System.out.println("登入加密字串: " + cipherText);
		System.out.println("登入SHA1: " + SHA1);
		return SHA1;
	}
	
	
	private MemberBean member_checkAccount(String id, String pw) throws SQLException {

		memberDAO_old dao = new memberDAO_old();
		MemberBean mb = dao.SelectByPrimaryKey(id);
//-----------------------AES+SHA1加密------------------------------

		String passwordSHA1 = getSHA1(pw);
		
//-----------------------AES+SHA1加密------------------------------
	
		if (mb != null && passwordSHA1.equals(mb.getM_password())) {
			return mb;
		}

		return null;
	}

	private TrainerBean trainer_checkAccount(String id, String pw) throws SQLException {

		TrainerBean mb = trainerService.select(id);
//-----------------------AES+SHA1加密------------------------------

		String passwordSHA1 = getSHA1(pw);
				
//-----------------------AES+SHA1加密------------------------------
		if (mb != null && passwordSHA1.equals(mb.getT_password())) {
			return mb;
		}

		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		System.out.println("LoginSevlet requestURI:"+session.getAttribute("requestURI"));
		String context = request.getContextPath();

		Map<String, String> errMsg = new HashMap<String, String>();
		request.setAttribute("errMsgkey", errMsg);

		
		String id = request.getParameter("trainerID");
		String pw = request.getParameter("Password");
		String rm = request.getParameter("remeberMe");
		String checkid = request.getParameter("identity");
		String requestURI = (String) session.getAttribute("requestURI");
		
		
		//System.out.println("id="+id+", pw="+pw+", rm="+rm);
		
		if (id == null || id.trim().length() == 0) {
			errMsg.put("idEmpty", "帳號必須輸入");
		}
		if (pw == null || pw.trim().length() == 0) {
			errMsg.put("pwEmpty", "密碼必須輸入");
		}

		Cookie cookid = null;
		Cookie cookpw = null;
		Cookie cookRm = null;

//		if (rm != null) {
//			cookid = new Cookie("id", id);
//			cookid.setMaxAge(30 * 60 * 60);
//			cookid.setPath(request.getContextPath());
//			cookpw = new Cookie("pw", pw);
//			cookpw.setMaxAge(30 * 60 * 60);
//			cookpw.setPath(request.getContextPath());
//			cookRm = new Cookie("rm", "true");
//			cookRm.setMaxAge(30 * 60 * 60);
//			cookRm.setPath(request.getContextPath());
//
//		} else {
//
//			cookid = new Cookie("user", id);
//			cookid.setMaxAge(0);
//			cookid.setPath(request.getContextPath());
//			cookpw = new Cookie("password", pw);
//			cookpw.setMaxAge(0);
//			cookpw.setPath(request.getContextPath());
//			cookRm = new Cookie("rm", "false");
//			cookRm.setMaxAge(0);
//			cookRm.setPath(request.getContextPath());
//		}
//		response.addCookie(cookid);
//		response.addCookie(cookpw);
//		response.addCookie(cookRm);

		if (!errMsg.isEmpty()) {
			RequestDispatcher RD = request.getRequestDispatcher("/login/login.jsp");
			RD.forward(request, response);
			return;

		}
		TrainerBean trainer;
		MemberBean member;

		if ("member".equals(checkid)) {
			try {
				member = member_checkAccount(id, pw);

				if (member != null) {
					session.setAttribute("loginidentity","member");
					session.setAttribute("memberLoginOK", member);
					if("admin".equals(id)){
						session.setAttribute("AdminOK", member);
						
					}
					
				} else {
					errMsg.put("LoginError", "帳號或密碼錯誤");

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			try {
				trainer = trainer_checkAccount(id, pw);

				if (trainer != null) {
					session.setAttribute("loginidentity","trainer");
					session.setAttribute("trainerLoginOK", trainer);
				} else {
					errMsg.put("LoginError", "帳號或密碼錯誤");

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (errMsg.isEmpty()) {

			if (requestURI != null) {
				System.out.println("LoginServlet requestURI="+requestURI);
				
				requestURI = (requestURI.length() == 0)  ? request.getContextPath() : requestURI;
				
				if("trainer".equals(checkid)){
					response.sendRedirect(context+"/trainercenter/trainer-welcome.jsp");
					return ;
				}
				
				response.sendRedirect(response.encodeRedirectURL(requestURI)); 
				return;
			} else {
				
				if ("member".equals(checkid)) {
					if("admin".equals(id)){
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/manage/Admin.jsp"));
						return;
					}
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
					return;
				}else{
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/trainercenter/trainer-welcome.jsp"));
					return;
				}

			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
			rd.forward(request, response);
			return;
		}

	}
}
