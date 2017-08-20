package admin_event.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin_event.model.EventBean;
import admin_event.model.EventService;
	     
@WebServlet("/EventController")
public class EventController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public EventController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String contextPath = request.getContextPath();
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		

		if ("ItemListAll".equals(action)) {

			EventService eventSvc = new EventService();
			List<EventBean> list = eventSvc.getAll();
			session.setAttribute("list", list);
			response.sendRedirect(contextPath+"/manage/ItemListAll.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String contextPath  = request.getContextPath();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		
		
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("errorMsgKey", errorMsgMap);
		
		
		System.out.println("action="+action);
		
		if ("authority_check".equals(action)) {
			try {

				// 接收資料

				// 查詢資料
				EventService eventSvc = new EventService();
				EventBean eventBean = eventSvc.getOneEvent(Integer.parseInt(id));

				// 查詢完成，準備轉交

				request.setAttribute("EventBean", eventBean);
				request.getRequestDispatcher("/manage/modifyItem.jsp").forward(request, response);

			} catch (Exception e) {
				errorMsgMap.put("DataInputError", "資料輸入錯誤");
				request.getRequestDispatcher("/manage/ItemListAll.jsp").forward(request, response);

			}

		}

		if ("modifyItem".equals(action)) {
			
			// 轉換驗證資料
        
			String trainerid = request.getParameter("trainerid");
			String status = request.getParameter("status").trim();
			String roomno = request.getParameter("roomno");
			String title = request.getParameter("title");
			String coursekind = request.getParameter("coursekind");
			String eventstart = request.getParameter("eventstart");
			String eventend = request.getParameter("eventend");
			String enroll = request.getParameter("enroll");
			String charge = request.getParameter("charge");
			System.out.println("status="+status);

			//開框修改權限
			System.out.println("id="+id);
			
			EventBean eventBean = new EventBean();
			eventBean.setEventno(Integer.parseInt(id)); // id = y is wrong String i= y
			eventBean.setTrainerid(trainerid);
			eventBean.setE_status(status); // status=y 
			eventBean.setRoomno(Integer.parseInt(roomno));
			eventBean.setTitle(title);
			eventBean.setCoursekind(coursekind);
			eventBean.setEventstart(java.sql.Date.valueOf(eventstart));
			eventBean.setEventend(java.sql.Date.valueOf(eventend));
			eventBean.setEnroll(Integer.parseInt(enroll));
			eventBean.setCharge(Integer.parseInt(charge));

			

			if (!errorMsgMap.isEmpty()) {
				request.setAttribute("EventBean", eventBean); // 含有輸入格式錯誤的EventBean物件,也存入request
				request.getRequestDispatcher("/manage/modifyItem.jsp").forward(request, response);
				return; // 程式中斷
			}

			// 修改資料

			EventService eventSvc = new EventService();
			
			eventSvc.updateEvent(eventBean);
			request.setAttribute("EventBean", eventBean); // 資料庫update成功後,正確的的EventBean物件,存入req
			
			List<EventBean> list = eventSvc.getAll();
			session.setAttribute("list", list);


			response.sendRedirect(contextPath+"/manage/ItemListAll.jsp");

		}
}
	
}
