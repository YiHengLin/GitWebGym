package YuiAddOrder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import YuiEvent.YuiEventBean;
import YuiEvent.YuiEventDAO;
import member.model.MemberBean;
import member.model.memberDAO_old;



@WebServlet("/YuiOrderTestServlet")
public class YuiOrderTestServlet extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDAO_old memberDAO = new memberDAO_old();
		MemberBean memberBean = new MemberBean();
		
		
		memberBean = memberDAO.SelectByPrimaryKey("Chad");
		System.out.println(memberBean);
		YuiEventDAO yuieventdao = new YuiEventDAO();
		List<YuiEventBean> list = yuieventdao.select();
		System.out.println(list.get(0));
		yuieventdao.select();
//		YuiOrderBean yuiorderbean = test.InsertNewOrder(memberBean, list.get(0));
//		System.out.println(yuiorderbean);
	}

	
	
}
