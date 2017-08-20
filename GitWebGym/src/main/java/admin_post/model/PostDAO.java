package admin_post.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import java.sql.*;

public class PostDAO implements PostDAO_interface{
	
	
	DataSource ds = null;

	public PostDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	
	
	private static final String SEND_STMT = "INSERT INTO post VALUES (?, ?, ?, ?, ?)";

	private static final String DELETE_STMT = "DELETE FROM post WHERE postno";

	private static final String GET_ONE_STMT = "SELECT * FROM post WHERE postno=? ";

	private static final String GET_ALL_STMT = "SELECT * FROM post ORDER BY postno desc";
	
	
//////////////////////////////
@Override	
public int sendPost(PostBean pb) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int sendCount = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SEND_STMT);
			pstmt.setString(1, pb.getMemberid());
			pstmt.setString(2, pb.getTitle());
			pstmt.setString(3, pb.getContent());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(4, ts);
			pstmt.setString(5, pb.getKind());
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

//////////////////////////////

@Override
public int deletePost (int postno) throws SQLException{
	  
	Connection conn = null;
	PreparedStatement pstmt = null;
	int DeleteCount = 0;

	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(DELETE_STMT);
		pstmt.setInt(1, postno);
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

//////////////////////////////	
	
@Override	
public PostBean findByPrimaryKey(int postno) throws SQLException {
	PostBean pb = null;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(GET_ONE_STMT);
		pstmt.setInt(1, postno);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			pb=new PostBean();
			pb.setMemberid(rs.getString("memberid"));
			pb.setTitle(rs.getString("title"));
			pb.setContent(rs.getString("content"));
			pb.setPostdate(rs.getTimestamp("postdate"));
			pb.setKind(rs.getString("kind"));
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
	return pb;
}


//////////////////////////////

@Override
public List<PostBean> getPosts() throws SQLException {
	PostBean pb = null;
	List<PostBean> pbs = new ArrayList<PostBean>();
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(GET_ALL_STMT);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			
			pb=new PostBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getString(6));
			pbs.add(pb);
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
	return pbs;
}
	
	
	
	
	
	

}
