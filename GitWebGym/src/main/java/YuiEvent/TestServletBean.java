package YuiEvent;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;



@WebServlet("/TestServletBean")
public class TestServletBean extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		EventBean bean = service.select();
//		
//		bean->[{\"title\":\"8+9\",\"start\":\"2017-05-05\"}]
		
		PrintWriter out = response.getWriter();
		// events: 
		out.println("[{\"title\":\"8+9\",\"start\":\"2017-07-05\"}]");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
