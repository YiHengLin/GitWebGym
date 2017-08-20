package comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class commDAO implements CommDAO_interface {

	DataSource ds = null;

	public commDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String POST_STMT = "insert into comments values (?,?,?,?)";

	public int PostComm(CommBean cb) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int postCount = 0;
		System.out.println("aaaaaa");

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(POST_STMT);
			pstmt.setInt(1, cb.getArticleNo());
			pstmt.setString(2, cb.getMemberId());
			pstmt.setString(3, cb.getCommcontet());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(4, ts);
			postCount = pstmt.executeUpdate();

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
		return postCount;

	}

	private static final String MODIFY_STMT = "update comments set articleno=?, memberid=?, content=?, commentdate=? where commentno=? ";

	public int ModifyComm(CommBean cb) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int ModifyCount = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(MODIFY_STMT);
			pstmt.setInt(1, cb.getArticleNo());
			pstmt.setString(2, cb.getMemberId());
			pstmt.setString(3, cb.getCommcontet());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(4, ts);
			pstmt.setInt(5, cb.getCommNo());
			ModifyCount = pstmt.executeUpdate();

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
		return ModifyCount;
	}

	private static final String DELETE_STMT = "delete comments where commentno=?";

	public int DeletComm(int commNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int DeleteCount = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, commNo);
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

	private static final String Find_BY_PK = "select * from comments where commentno=? ";

	public CommBean findbyCommNO(int commNo) throws SQLException {
		CommBean cb = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(Find_BY_PK);
			pstmt.setInt(1, commNo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cb = new CommBean();
				cb.setCommNo(rs.getInt("commentno"));
				cb.setArticleNo(rs.getInt("articleno"));
				cb.setMemberId(rs.getString("memberid"));
				cb.setCommcontet(rs.getString("content"));
				cb.setCommdate(rs.getTimestamp("pubdatecommentno"));
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
		return cb;
	}

	private static final String GET_ALL = "select * from comments order by commentdate desc";
	                                   

	public List<CommBean> getCommemts() throws SQLException {
		CommBean cb = null;
		List<CommBean> cbs = new ArrayList<CommBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				cb = new CommBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5));
				cbs.add(cb);
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
		return cbs;
	}
}
