package signup.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import member.model.MemberBean;
import member.model.memberDAO_old;
import trainer.model.TrainerBean;
import trainer.model.TrainerService;


//@WebServlet("/signup/SignUpServlet")
@MultipartConfig(maxFileSize=2560*1600*100)
public class SignupServlet_no_security extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TrainerService trainerService;   
    
    
	private byte[] getByte(Part part) throws IOException{
		InputStream in=part.getInputStream();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		byte[] buffer=new byte[4096];
		int len=-1;
		while((len=in.read(buffer))!=-1){
			out.write(buffer, 0, len);
		}
		
		in.read();
		out.close();
		return out.toByteArray();
	}
    

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext)
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		trainerService = (TrainerService) context.getBean("trainerService");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> errorMessage = new HashMap<String,String>();
		
		
		request.setCharacterEncoding("UTF-8");
		String context = request.getContextPath();
		HttpSession session = request.getSession();
		
		request.setAttribute("ErrorMsg", errorMessage);
		
		String whoareyou = request.getParameter("t_identity");
		System.out.println("whoareyou="+whoareyou);
		
		if("trainer".equals(whoareyou)){
			String trainerid = request.getParameter("trainerid");
			if (trainerid == null || trainerid.trim().length() == 0) {
				errorMessage.put("id","帳號欄必須輸入");
			}
			String password = request.getParameter("t_password");
			if (password == null || password.trim().length() == 0) {
				errorMessage.put("password","密碼欄必須輸入");
			}
			String name = request.getParameter("t_name");
			if (name == null || name.trim().length() == 0) {
				errorMessage.put("name","姓名欄必須輸入");
			}
			String email = request.getParameter("t_mail");
			if (email == null || email.trim().length() == 0) {
				errorMessage.put("email","電子信箱必須輸入");
			}
			String idNumber = request.getParameter("t_id_number");
			if ( idNumber == null ||  idNumber.trim().length() == 0) {
				errorMessage.put("idNumber","身分證號碼必須輸入");
			}
			String address = request.getParameter("t_address");
			if (address == null || address.trim().length() == 0) {
				errorMessage.put("address","地址欄必須輸入");
			}
			String phone = request.getParameter("t_mobile");
			if (phone == null || phone.trim().length() == 0) {
				errorMessage.put("phone","手機號碼必須輸入");
			}
			String HomeNumber = request.getParameter("t_tel");
			
			String bday = request.getParameter("t_bday");
			java.sql.Date date = null;
			if (bday != null && bday.trim().length() > 0) {
				try {
					date = java.sql.Date.valueOf(bday);
				} catch (IllegalArgumentException e) {
					errorMessage.put("date","生日欄格式錯誤");
				}
			}
			
			String field = request.getParameter("t_field");
			if (phone == null || phone.trim().length() == 0) {
				errorMessage.put("Field","專業領域必須輸入");
			}
			String exp = request.getParameter("t_exp");
			if (phone == null || phone.trim().length() == 0) {
				errorMessage.put("Exp","經歷必須輸入");
			}
			String lice = request.getParameter("t_licence");
			if (phone == null || phone.trim().length() == 0) {
				errorMessage.put("Lice","專業證照必須輸入");
			}
			
			String Gender = request.getParameter("t_gender");
			
			
			String identity = request.getParameter("t_identity");
		    Part pic=request.getPart("t_photo");
		    
		    
		    byte[] photo_bytes=getByte(pic);
		    	
			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request
						.getRequestDispatcher("signup.jsp");
				rd.forward(request, response);
				return;
			}
			String t_register = request.getParameter("t_register");
			String is_blacklist = request.getParameter("is_blacklist");
			String t_identity = request.getParameter("t_identity");
			String t_field = request.getParameter("t_field");
			String t_experience = request.getParameter("t_exp");
			String t_licence = request.getParameter("t_licence");
			
			TrainerBean bean = new TrainerBean();
			
			bean.setTrainerID(trainerid);
			bean.setT_password(password);
			bean.setT_name(name);
			bean.setT_id_number(idNumber);
			bean.setT_gender(Gender);
			bean.setT_bday(java.sql.Date.valueOf(bday));
			bean.setT_mail(email);
			bean.setT_mobile(phone);
			bean.setT_tel(HomeNumber);
			bean.setT_address(address);
			bean.setT_register(java.sql.Date.valueOf(t_register));
			bean.setT_photo(photo_bytes);
			bean.setIs_blacklist(is_blacklist);
			bean.setT_identity(t_identity);
			bean.setT_field(t_field);
			bean.setT_experience(t_experience);
			bean.setT_licence(t_licence);
			
			TrainerBean result = trainerService.insert(bean);
			System.out.println("result="+result);
			session.setAttribute("trainerLoginOK", result);
			session.setAttribute("loginidentity","trainer");
			response.sendRedirect(context+"/index.jsp");
			return;
		}else{
			
			// Member Sign Up
			
			String memberid = request.getParameter("trainerid");
			if (memberid == null || memberid.trim().length() == 0) {
				errorMessage.put("id","帳號欄必須輸入");
			}
			String password = request.getParameter("t_password");
			if (password == null || password.trim().length() == 0) {
				errorMessage.put("password","密碼欄必須輸入");
			}
			String name = request.getParameter("t_name");
			if (name == null || name.trim().length() == 0) {
				errorMessage.put("name","姓名欄必須輸入");
			}
			String email = request.getParameter("t_mail");
			if (email == null || email.trim().length() == 0) {
				errorMessage.put("email","電子信箱必須輸入");
			}
			String idNumber = request.getParameter("t_id_number");
			if ( idNumber == null ||  idNumber.trim().length() == 0) {
				errorMessage.put("idNumber","身分證號碼必須輸入");
			}
			String address = request.getParameter("t_address");
			if (address == null || address.trim().length() == 0) {
				errorMessage.put("address","地址欄必須輸入");
			}
			String phone = request.getParameter("t_mobile");
			if (phone == null || phone.trim().length() == 0) {
				errorMessage.put("phone","手機號碼必須輸入");
			}
			String HomeNumber = request.getParameter("t_tel");
			
			String bday = request.getParameter("t_bday");
			java.sql.Date date = null;
			if (bday != null && bday.trim().length() > 0) {
				try {
					date = java.sql.Date.valueOf(bday);
				} catch (IllegalArgumentException e) {
					errorMessage.put("date","生日欄格式錯誤");
				}
			}
			
			String field = request.getParameter("t_field");
			if (phone == null || phone.trim().length() == 0) {
				errorMessage.put("Field","專業領域必須輸入");
			}
			String exp = request.getParameter("t_exp");
			if (phone == null || phone.trim().length() == 0) {
				errorMessage.put("Exp","經歷必須輸入");
			}
			String lice = request.getParameter("t_licence");
			if (phone == null || phone.trim().length() == 0) {
				errorMessage.put("Lice","專業證照必須輸入");
			}
			
			String Gender = request.getParameter("t_gender");
			
			
			String identity = request.getParameter("t_identity");
		    Part pic=request.getPart("t_photo");
		    
		    
		    byte[] photo_bytes=getByte(pic);
		    	
			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request
						.getRequestDispatcher("signup.jsp");
				rd.forward(request, response);
				return;
			}
			String t_register = request.getParameter("t_register");
			String is_blacklist = request.getParameter("is_blacklist");
			String t_identity = request.getParameter("t_identity");

			Timestamp ts = new Timestamp(System.currentTimeMillis());
			

			
			MemberBean  bean = new  MemberBean(memberid,password,name,idNumber,Gender,date,email,phone,HomeNumber,address,ts,photo_bytes,is_blacklist,"member");
			
			memberDAO_old dao = new memberDAO_old();
			try {
				dao.insert(bean);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			session.setAttribute("memberLoginOK", bean);
			session.setAttribute("loginidentity","member");
			response.sendRedirect(context+"/index.jsp");
		}	
	}

}
