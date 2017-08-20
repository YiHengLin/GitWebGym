package mytrains.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class traindetailsDAO {
	
	DataSource ds = null;
	public traindetailsDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
    //新增訓練資料功能使用
	private static final String INSERT = "Insert into traindetails ( selftrainno, traintitle, trainset, trainweight) values (?, ?, ?, ?)";

	public traindetailsBean insertTrainDetail(traindetailsBean bean) throws SQLException {
		traindetailsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(INSERT);

			stmt.setString(1, bean.getTrainno());
			stmt.setString(2, bean.getTraintitle());
			stmt.setString(3, bean.getTrainset());
			stmt.setString(4, bean.getTrainweight());

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
	
    //刪除訓練資料功能使用(先刪除訓練明細)
	private static final String DELETE1 = "delete from traindetails where selftrainno=? and traintitle=?";
	public traindetailsBean deletetraindetail(traindetailsBean bean) throws SQLException {
		traindetailsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(DELETE1);
			
			stmt.setString(1, bean.getTrainno());
			stmt.setString(2, bean.getTraintitle());
			
			int i = stmt.executeUpdate();
			System.out.println("執行刪除明細="+i);
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
	
	//執行刪除訓練明細時使用(查詢訓練明細李是否還有屬於selftrainno的資料，用title的存在來判斷)
	private static final String selectAA = "select traintitle from traindetails where selftrainno=?";
	public traindetailsBean selecttraindetail(selftrainsBean bean) throws SQLException {
		traindetailsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(selectAA);
			
			stmt.setString(1, bean.getSelftrainno());
			rset = stmt.executeQuery();
			if (rset.next()) {
				result = new traindetailsBean();
				result.setTraintitle(rset.getString("traintitle"));		
			}
			System.out.println("執行查詢明細內容="+result);
		
	   }catch (SQLException e) {
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
	
	//執行修該訓練項目的功能
	private static final String UPDATE = "update traindetails set traintitle=?, trainset=?, trainweight=? where selftrainno=? and traintitle=?";
	public traindetailsBean updatetraindetail(traindetailsBean bean) throws SQLException {
		traindetailsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(UPDATE);
			
			stmt.setString(1, bean.getTraintitle());
			stmt.setString(2, bean.getTrainset());
			stmt.setString(3, bean.getTrainweight());
			stmt.setString(4, bean.getTrainno());
			stmt.setString(5, bean.getOldtraintitle());
			
			int i = stmt.executeUpdate();
			System.out.println("執行修改="+i);
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
	
	private static final String SELECTDATA1 = "select traintitle,trainset,trainweight from traindetails where selftrainno=?";
	public List<traindetailsBean> selectselftrainno2(String id) throws SQLException {
		List<traindetailsBean> result = new ArrayList<traindetailsBean>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECTDATA1);
			
			stmt.setString(1, id);
			
			rset = stmt.executeQuery();
			while (rset.next()) {
				traindetailsBean temp = new traindetailsBean();
				 temp.setTraintitle(rset.getString("traintitle"));
				 temp.setTrainset(rset.getString("trainset"));
				 temp.setTrainweight(rset.getString("trainweight"));
				 result.add(temp);
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
	
}