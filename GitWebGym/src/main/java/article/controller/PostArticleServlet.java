package article.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.model.ArticleBean;
import article.model.ArticleDAO;


@WebServlet("/postArticle.do")
public class PostArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public PostArticleServlet() {
  
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          request.setCharacterEncoding("UTF-8");
          response.setCharacterEncoding("UTF-8");
          HttpSession session =request.getSession();
          String contextPath = request.getContextPath();
          
          Map<String,String> errorMsg =new HashMap<String,String>();
          
          request.setAttribute("errorMsg", errorMsg );
          String title=request.getParameter("title");
          String content=request.getParameter("content");
          String id=request.getParameter("trainerid");
          Timestamp ts = new Timestamp(System.currentTimeMillis());
          
          if(title==null||title.trim().length()==0){
        	  errorMsg.put("titleError","請輸入標題");
          }
          if(content==null||content.trim().length()==0){
        	  errorMsg.put("contentError","文章不得為空白");
          }
          
          
          if(!errorMsg.isEmpty()){
        	  RequestDispatcher rd=request.getRequestDispatcher("/blog/postarticle.jsp");
        	  rd.forward(request, response);
        	  return;
          }
          
          ArticleBean ab=new ArticleBean(id,ts,title,content);
 
          
          try {
        	ArticleDAO aDAO =new ArticleDAO() ; 
			aDAO.PostArticle(ab);
			List<ArticleBean> BeanList = aDAO.getAritcle();
			request.getSession().setAttribute("BeanList", BeanList);
		
		}  catch (SQLException e) {
			errorMsg.put("LoginError",
					"LoginServlet->SQLException:" + e.getMessage());
			e.printStackTrace();
		}
          
          if (errorMsg.isEmpty()) {
  			session.setAttribute("MessageInsertOK", "新增留言成功");
  			response.sendRedirect(request.getContextPath() + "/blog/articles.jsp");
  			return;
  		} else {
  			RequestDispatcher rd = request.getRequestDispatcher("/blog/postarticle.jsp");
  			rd.forward(request, response);
  			return;
  		}
          
          
    }	
}