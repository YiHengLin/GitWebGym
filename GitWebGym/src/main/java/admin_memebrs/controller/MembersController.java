package admin_memebrs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin_members.model.MembersBean;
import admin_members.model.MembersService;

@WebServlet("/MembersController")
public class MembersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MembersController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String contextPath = request.getContextPath();
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("errorMsgKey", errorMsgMap);

		if ("ListAll".equals(action)) {

			MembersService memSvc = new MembersService();
			List<MembersBean> list = memSvc.getAll();
			session.setAttribute("list", list);
			
			for(MembersBean bean:list){
				System.out.println(bean.getMemberid());
			}
			
			response.sendRedirect(contextPath+"/manage/ListAll.jsp");
			return;
		}

		if ("BlackList".equals(action)) {

			MembersService memSvc = new MembersService();
			List<MembersBean> list = memSvc.getBlackList();
			session.setAttribute("blacklist", list);
			response.sendRedirect(contextPath+"/manage/BlackList.jsp");
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String contextPath = request.getContextPath();
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("errorMsgKey", errorMsgMap);
		
				// 
				if ("authority_check".equals(action)) {

					try {

						String id = new String(request.getParameter("id"));

						MembersService memSvc = new MembersService();
						MembersBean membersBean = memSvc.getOneMem(id);
						
						request.setAttribute("MembersBean", membersBean);
						request.getRequestDispatcher("/manage/modify.jsp").forward(request, response);

					} catch (Exception e) {
						errorMsgMap.put("DataInputError", "資料輸入錯誤");
						request.getRequestDispatcher("/manage/ListAll.jsp").forward(request, response);

					}

				}
				
				//加入黑名單
				if ("set_blacklist".equals(action)) {

					// 接收資料
					String memberid = new String(request.getParameter("memberid"));

					
					MembersBean membersBean = new MembersBean();
					membersBean.setMemberid(memberid);

					// 查詢資料
					MembersService memSvc = new MembersService();
					memSvc.updateBlackList(membersBean);

					
					session.setAttribute("MembersBean", membersBean);
					response.sendRedirect(contextPath+"/manage/ListAll.jsp");
					return;

				}
				
				//從黑名單移出
				if ("authority_regain".equals(action)) {

					// 接收資料
					String memberid = new String(request.getParameter("memberid"));

					MembersBean membersBean = new MembersBean();
					membersBean.setMemberid(memberid);

					// 查詢資料
					MembersService memSvc = new MembersService();
					memSvc.updateNormal(membersBean);
					
					// 取得最新黑名單,並將 blacklist 物件更新為最新的黑名單
					List<MembersBean> list = memSvc.getBlackList();
					session.setAttribute("blacklist", list);

					// 查詢完成，準備轉交

					session.setAttribute("MembersBean", membersBean);
					response.sendRedirect(contextPath+"/manage/BlackList.jsp");
					return;
				}
		

		
	}
}
