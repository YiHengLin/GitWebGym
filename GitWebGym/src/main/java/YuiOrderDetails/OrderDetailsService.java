package YuiOrderDetails;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import YuiEvent.YuiEventBean;
import YuiMember.YuiMemberBean;

/**
 * Servlet implementation class OrderDetTestServlet
 */
@WebServlet("/TestScope")
public class OrderDetailsService extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 此Service把前端fullCalendar的json物件傳回來，並塞到Scope裡面
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);

		if (session != null) {
			
			session.removeAttribute("yuieventbean");

			String eventnostg = request.getParameter("eventno");
			String trainerid = request.getParameter("trainerid");
			String roomno = request.getParameter("roomno");
			String title = request.getParameter("title");
			String coursekind = request.getParameter("coursekind");
			String e_status = request.getParameter("e_status");
			String stringstart = request.getParameter("start");
			String stringend = request.getParameter("end");
			String enroll = request.getParameter("enroll");
			String chargestg = request.getParameter("charge");

			Timestamp start = new Timestamp(System.currentTimeMillis());
			start = Timestamp.valueOf(stringstart);

			Timestamp end = new Timestamp(System.currentTimeMillis());
			end = Timestamp.valueOf(stringend);

			int eventno = 0;
			eventno = Integer.parseInt(eventnostg.trim());

			double charge = 0;
			charge = Double.parseDouble(chargestg.trim());

			YuiEventBean yuieventbean = new YuiEventBean(eventno, trainerid, roomno, title, coursekind, e_status, start,
					end, enroll, charge);

			session.setAttribute("yuieventbean", yuieventbean);
			System.out.println(yuieventbean);
		}

	}

}
