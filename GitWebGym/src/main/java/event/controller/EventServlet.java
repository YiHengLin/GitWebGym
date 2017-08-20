package event.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import event.model.EventBean;
import event.model.EventService;


@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService eventService;
       
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext)
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		eventService = (EventService) context.getBean("eventService");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<EventBean> beans = eventService.select();
		System.out.println("beans="+beans);

		JSONArray jsonArray = new JSONArray();
		JSONObject jSONObject = null;

		for (EventBean bean : beans) {
			jSONObject = new JSONObject();
			jSONObject.put("title", bean.getTitle());
			jSONObject.put("start", bean.getEventstart());
			jsonArray.put(jSONObject);
		}
		
		System.out.println("jsonArray="+jsonArray);
		response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(jsonArray.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
