package mytrains.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberBean;
import mytrains.model.selftrainsBean;
import mytrains.model.selftrainsDAO;
import mytrains.model.traindetailsBean;
import mytrains.model.traindetailsDAO;


@WebServlet("/insertdata.do")
public class InsertTrainsDataServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session==null){
    		response.sendRedirect(request.getContextPath()+"/login/login.jsp");
    		return;
    	}
		Collection<String> errorMessage = new ArrayList<String>();
		request.setAttribute("ErrorMsg", errorMessage);
		
		request.setCharacterEncoding("UTF-8");
//		String usid=(String)session.getAttribute("userid");	
		MemberBean member=(MemberBean)session.getAttribute("memberLoginOK");
		
		String tday = request.getParameter("udDay");
		// 檢查使用者所輸入的資料
		if (tday == null || tday.trim().length() == 0) {
			errorMessage.add("日期欄必須輸入");
		}				
		String title = request.getParameter("udTitle");
		// 檢查使用者所輸入的資料
		if (title == null || title.trim().length() == 0) {
			errorMessage.add("項目欄必須輸入");
		}
		// 讀取使用者所輸入，由瀏覽器送來的 tSet 欄位內的資料
		String set = request.getParameter("udSet");
		// 檢查使用者所輸入的資料
		if (set == null || set.trim().length() == 0) {
			errorMessage.add("組數必須輸入");
		}
		// 讀取使用者所輸入，由瀏覽器送來的 tWei 欄位內的資料
		String weight = request.getParameter("udWeight");
		// 檢查使用者所輸入的資料
		if (weight == null || weight.trim().length() == 0) {
			errorMessage.add("重量必須輸入");
		}
		String selfno = null;
		
		selftrainsBean sb = new selftrainsBean(member.getMemberID(), tday );
		try {
			selftrainsDAO mfio = new selftrainsDAO();
			//判斷會員是否已有在該日期的訓練資料
			if(mfio.selectselftrainno(sb)==null){                        //未存在既有selftrainno
				mfio.insertSelfTrainDate(sb);                            //新增訓練日期及產生新的selftrainno
				sb = new selftrainsBean(member.getMemberID(), tday );                         //在查詢一次該會員在此日期的selftrainno
				selfno = mfio.selectselftrainno(sb).getSelftrainno();    //將值帶出來放到selfno裡，供接續程式使用
			}
			
			selfno = mfio.selectselftrainno(sb).getSelftrainno();        //存在既有selftrainno，查出來使用
			
		} catch (SQLException e) {
				errorMessage.add("資料庫存取錯誤:" + e.getMessage());
			}
			
		traindetailsBean mb = new traindetailsBean(selfno, title, set, weight);
		try {
			traindetailsDAO mfio = new traindetailsDAO();
			mfio.insertTrainDetail(mb);
			request.setAttribute("TdetailBean", mb);
			// 依照執行的結果挑選適當的view元件，送回相關訊息
			return;
		} catch (SQLException e) {
				errorMessage.add("資料庫存取錯誤:" + e.getMessage());
			}
			return;		
	}
}
