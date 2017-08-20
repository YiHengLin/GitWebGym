package admin_mail.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import java.sql.*;

public class MailDAO implements MailDAO_interface {
	
	

	DataSource ds = null;

	public MailDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String SEND_STMT = "INSERT INTO mail VALUES (?, ?, ?, ?, ?)";

	private static final String DELETE_STMT = "DELETE FROM mail WHERE mailno";

	private static final String GET_ONE_STMT = "SELECT * FROM mail WHERE mailno=? ";

	private static final String GET_ALL_STMT = "SELECT * FROM mail ORDER BY mailno desc";

	@Override
	public int sendMail(MailBean mb) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int sendCount = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SEND_STMT);
			pstmt.setString(1, mb.getMemberid());
			pstmt.setString(2, mb.getTitle());
			pstmt.setString(3, mb.getContent());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(4, ts);
			pstmt.setString(5, mb.getKind());
			sendCount = pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return sendCount;
	}
    
	@Override
	public int deleteMail (int mailno) throws SQLException{
		  
		Connection conn = null;
		PreparedStatement pstmt = null;
		int DeleteCount = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mailno);
			DeleteCount = pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return DeleteCount;
	}
			
		
	
	
	@Override
	public MailBean findByPrimaryKey(int mailno) throws SQLException {
		MailBean mb = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, mailno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				mb=new MailBean();
				mb.setMemberid(rs.getString("memberid"));
				mb.setTitle(rs.getString("title"));
				mb.setContent(rs.getString("content"));
				mb.setM_postdata(rs.getTimestamp("postdate"));
				mb.setKind(rs.getString("kind"));
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return mb;
	}

	@Override
	public List<MailBean> getMails() throws SQLException {
		MailBean mb = null;
		List<MailBean> mbs = new ArrayList<MailBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				mb=new MailBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getString(6));
				mbs.add(mb);
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return mbs;
	}

}