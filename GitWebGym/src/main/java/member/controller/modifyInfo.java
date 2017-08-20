package member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import member.model.MemberBean;
import member.model.memberDAO;


@WebServlet("/modifyServlet.do")
@MultipartConfig(maxFileSize=2560*1600*100)
public class modifyInfo extends HttpServlet  {
	private static final long serialVersionUID = 1L;
    public modifyInfo() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String context = request.getContextPath();
		Map<String,String> errorMessage = new HashMap<String,String>();
		request.setAttribute("ErrorMsg", errorMessage);
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("memberid");
		if (id == null || id.trim().length() == 0) {
			errorMessage.put("id","帳號欄必須輸入");
		}
		
		String password = request.getParameter("m_password");
		if (password == null || password.trim().length() == 0) {
			errorMessage.put("password","密碼欄必須輸入");
		}
		
		String name = request.getParameter("m_name");
		if (name == null || name.trim().length() == 0) {
			errorMessage.put("name","姓名欄必須輸入");
		}
		
		String email = request.getParameter("m_mail");
		if (email == null || email.trim().length() == 0) {
			errorMessage.put("email","電子信箱必須輸入");
		}
		
		String idNumber = request.getParameter("m_id_number");
		if ( idNumber == null ||  idNumber.trim().length() == 0) {
			errorMessage.put("idNumber","身分證號碼必須輸入");
		}
		
		String address = request.getParameter("m_address");
		if (address == null || address.trim().length() == 0) {
			errorMessage.put("address","地址欄必須輸入");
		}
		 
		String phone = request.getParameter("m_mobile");
		if (phone == null || phone.trim().length() == 0) {
			errorMessage.put("phone","手機號碼必須輸入");
		}
	    String blist=request.getParameter("is_blacklist");
		String HomeNumber = request.getParameter("m_tel");
        System.out.println("b="+blist);
		String bday = request.getParameter("m_bday");
		java.sql.Date date = null;
		if (bday != null && bday.trim().length() > 0) {
			try {
				date = java.sql.Date.valueOf(bday);
			} catch (IllegalArgumentException e) {
				errorMessage.put("date","生日欄格式錯誤");
			}
		}
		
		String Gender = request.getParameter("m_gender");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		String identity = request.getParameter("m_identity");
	     
		
		 Part pic=request.getPart("m_photo");
		 byte[] photo_bytes=getByte(pic);
		
		if (!errorMessage.isEmpty()) {
			RequestDispatcher rd = request
					.getRequestDispatcher("manage/memberprofile.jsp"); 
			rd.forward(request, response);
			return;
		}

		MemberBean mb;
		
		
			mb=new MemberBean(id,password,name,idNumber,
				Gender,date,email,phone,HomeNumber,
				address,ts,photo_bytes,blist,identity);
			
			
			try {
				memberDAO dao = new memberDAO();
				dao.update(mb);
				session.setAttribute("memberLoginOK", mb);
				response.sendRedirect(context+"/membercenter/memberprofile.jsp");
				return;
			} catch (SQLException e) {
		
					errorMessage.put("exception","資料庫存取錯誤:" + e.getMessage());
				}
			

			      RequestDispatcher rd = request.getRequestDispatcher("/manage/memberprofile.jsp");
						rd.forward(request, response);
						return;
					
				
		}
		
			

	
	
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

		
}


