package mytrains.model;

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



import java.sql.Blob;

public class selftrainsDAO {
	
	DataSource ds = null;
	public selftrainsDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	

	private static final String SELECT_SELFNO_BY_ID = "select selftrainno from selftrains where memberid=? and trainday=?";

	public selftrainsBean selectselftrainno(selftrainsBean bean) throws SQLException {
		selftrainsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement( SELECT_SELFNO_BY_ID);
			
			stmt.setString(1, bean.getMemberid());
			stmt.setString(2, bean.getTrainday());
			
			rset = stmt.executeQuery();
			if (rset.next()) {
				result = new selftrainsBean();
				result.setSelftrainno(rset.getString("selftrainno"));		
			}
//			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
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
		return result;
	}
	
	private static final String INSERT = "Insert into selftrains (memberid, trainday) values (?, ?)";

	public selftrainsBean insertSelfTrainDate(selftrainsBean bean) throws SQLException {
		selftrainsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(INSERT);

			stmt.setString(1, bean.getMemberid());
			stmt.setString(2, bean.getTrainday());

			int i = stmt.executeUpdate();
//			System.out.println("i="+i);
			
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
		return result;
	}
	
	//執行刪除資料時使用(確定訓練明細裡沒有屬於selftrainno的資料時執行)
	private static final String DELETE2 = "delete selftrains where memberid=? and trainday=?";
	public selftrainsBean deletetraindate(selftrainsBean bean) throws SQLException {
		selftrainsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(DELETE2);
			
			stmt.setString(1, bean.getMemberid());
			stmt.setString(2, bean.getTrainday());
			
			int i = stmt.executeUpdate();
			System.out.println("執行刪除日期="+i);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
		return result;
	}	


	private static final String SELECTFK = "select selftrainno,trainday from selftrains where memberid=? order by trainday desc";
	public List<selftrainsBean> selectselftrainno2(String id) throws SQLException {
		
		
		List<selftrainsBean> result = new ArrayList<selftrainsBean>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECTFK);
			
			stmt.setString(1, id);
			
			rset = stmt.executeQuery();
			while (rset.next()) {
				 selftrainsBean temp = new selftrainsBean();
				 temp.setSelftrainno(rset.getString("selftrainno"));
				 temp.setTrainday(rset.getString("trainday"));
				 result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
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
		return result;
	}
	
}