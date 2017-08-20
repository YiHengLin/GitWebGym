package misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/misc/showmemberphoto")
public class ShowMemberPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		OutputStream OS = null;
		InputStream IS = null;

		HttpSession session = request.getSession();
		String loginidentity = (String) session.getAttribute("loginidentity");
		String id = request.getParameter("id");

		System.out.println("In ShowMemberPhoto, id=" + id + ", loginidentity=" + loginidentity);

		Context context;

		try {
			context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select m_photo from members where memberid=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				IS = rs.getBinaryStream(1);
				response.setContentType("image/jpg");
				OS = response.getOutputStream();

				int len = 0;
				byte[] b = new byte[4096];
				while ((len = IS.read(b)) != -1) {
					OS.write(b, 0, len);
				}
			}

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
