package article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ArticleDAO implements ArticleDAO_interface {
	//Connection conn = null;
	DataSource ds = null;

	

	public ArticleDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
 
//	public void getConnection() {
//		try {
//			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Temmujin_Group", "sa",
//					"123456");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("connectionEX:" + e);
//		}
//	}

	private static final String POST_STMT = "insert into articles values (?,?,?,?)";
	String sql = "INSERT INTO Message values(?, ?, ?, ?, ?)";

	public int PostArticle(ArticleBean ab) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
	    
		int postCount = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(POST_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, ab.getTrainId());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(2, ts);
			pstmt.setString(3, ab.getTitle());
			pstmt.setString(4, ab.getContent());
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

	private static final String MODIFY_STMT = "update articles set trainerid=?,pubdate=?, title=?, content=? where articleno=? ";

	public int ModifyArticle(ArticleBean ab) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int ModifyCount = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(MODIFY_STMT);
			pstmt.setString(1, ab.getTrainId());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(2, ts);
			pstmt.setString(3, ab.getTitle());
			pstmt.setString(4, ab.getContent());
			pstmt.setInt(5, ab.getArticleNo());
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

	private static final String DELETE_STMT = "delelte from articles where articleno=?";

	public int DeletArticle(int ArticleNo) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int DeleteCount = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ArticleNo);
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

	private static final String Find_BY_PK = "select * from articles where articleno=? ";

	public ArticleBean findbyArticleNO(int ArticleNo) throws SQLException {
		ArticleBean ab = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(Find_BY_PK);
			pstmt.setInt(1, ArticleNo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				ab = new ArticleBean();
				ab.setArticleNo(rs.getInt("articleno"));
				ab.setTrainId(rs.getString("trainerid"));
				ab.setPubdate(rs.getTimestamp("pubdate"));
				ab.setTitle(rs.getString("title"));
				ab.setContent(rs.getString("content"));
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
		return ab;
	}

	//private static final String GET_ALL = "select * from articles where trainerid=? order by pubdate desc";
	private static final String GET_ALL = "select * from articles order by pubdate desc";
	public List<ArticleBean> getAritcle() throws SQLException {
		List<ArticleBean> abs = new ArrayList<ArticleBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
    
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ArticleBean ab = new ArticleBean(rs.getInt(1),rs.getString(2),rs.getTimestamp(3),rs.getString(4),rs.getString(5));

				abs.add(ab);
				
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
		return abs;
	}

}