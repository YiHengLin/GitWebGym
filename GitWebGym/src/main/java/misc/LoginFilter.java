package misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberBean;
import trainer.model.TrainerBean;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = { "/*" }, initParams = { 
		@WebInitParam(name = "url_1", value = "/trainercenter/*"),
		@WebInitParam(name = "url_2", value = "/membercenter/*"),
		@WebInitParam(name = "url_3", value = "/group/creategroup")})

public class LoginFilter implements Filter {
	Collection<String> url = new ArrayList<String>();

	public LoginFilter() {

	}

	public void destroy() {

	}
	
	String requestURI;
	

	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<String> e = fConfig.getInitParameterNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			// System.out.println("Init: "+fConfig.getInitParameter(name));
			url.add(fConfig.getInitParameter(name));
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String sevletPath = req.getServletPath();
			String requestURI = req.getRequestURI();
			
			System.out.println("LoginFilter requestURI:" + requestURI);
			
			if (mustLogin(sevletPath)) {
				if (checkLogin(req)) {
					//System.out.println("需要Login, 已經Login");
					chain.doFilter(request, response);
				} else {
					HttpSession session = req.getSession();
					session.setAttribute("requestURI", requestURI);
					//System.out.println("需要Login, 尚未Login,ServletPath=" + req.getServletPath());
					req.getRequestDispatcher("/login/login.jsp").forward(req, res);
				}

			} else {
				//System.out.println("不需要Login");
				chain.doFilter(request, response);
			}
		} else {
			throw new ServletException("req/res Type Error");
		}
	}

	private boolean mustLogin(String servletPath) {
		boolean login = false;
		for (String sUrl : url) {
			if (sUrl.endsWith("*")) {
				sUrl= sUrl.substring(0, sUrl.length() - 1);
				if (servletPath.startsWith(sUrl)) {
					login = true;
					break;
				}
			} else {
				if (servletPath.equals(sUrl)) {
					login = true;
					break;
				}
			}
		}
		return login;
	}

	private boolean checkLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("trainerLoginOK") instanceof  TrainerBean){
			TrainerBean loginToken = (TrainerBean) session.getAttribute("trainerLoginOK");
			if (loginToken == null) {
				return false;
			} else {
				return true;
			}
		}else{
			MemberBean loginToken = (MemberBean) session.getAttribute("memberLoginOK");
			if (loginToken == null) {
				return false;
			} else {
				return true;
			}
		}
	}

}
