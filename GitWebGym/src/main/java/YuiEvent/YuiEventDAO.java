package YuiEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import YuiGlobalParam.yuiGlobalService;

public class YuiEventDAO {

	DataSource ds = null;
	public YuiEventDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(yuiGlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	
private static final String SelectFromEventNo = "select eventno,trainerid,roomno,title,coursekind,e_status,eventstart,eventend,enroll,charge from event where eventno = ?";

	public YuiEventBean selectFromEventNo(int eventno){
		YuiEventBean yuieventbean = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SelectFromEventNo);
			stmt.setInt(1, eventno);
			rset = stmt.executeQuery();
			if(rset.next()){
				yuieventbean = new YuiEventBean();
				yuieventbean.setEventno(rset.getInt("eventno"));
				yuieventbean.setTrainerid(rset.getString("trainerid"));
				yuieventbean.setRoomno(rset.getString("roomno"));
				yuieventbean.setTitle(rset.getString("title"));
				yuieventbean.setCoursekind(rset.getString("coursekind"));
				yuieventbean.setE_status(rset.getString("e_status"));
				yuieventbean.setEventstart(rset.getTimestamp("eventstart"));
				yuieventbean.setEventend(rset.getTimestamp("eventend"));
				yuieventbean.setEnroll(rset.getString("enroll"));
				yuieventbean.setCharge(rset.getDouble("charge"));
		
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rset != null){
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return yuieventbean;
	}
	
private static final String SELECT_ALL = "select eventno,trainerid,roomno,title,coursekind,e_status,eventstart,eventend,enroll,charge from event";
	
	public List<YuiEventBean> select() {
		List<YuiEventBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			result = new Vector<>();
			while (rset.next()) {
				YuiEventBean temp = new YuiEventBean();

				temp.setEventno(rset.getInt("eventno"));
				temp.setTrainerid(rset.getString("trainerid"));
				temp.setRoomno(rset.getString("roomno"));
				temp.setTitle(rset.getString("title"));
				temp.setCoursekind(rset.getString("coursekind"));
				temp.setE_status(rset.getString("e_status"));	
				temp.setEventstart(rset.getTimestamp("eventstart"));
				temp.setEventend(rset.getTimestamp("eventend"));
				temp.setEnroll(rset.getString("enroll"));
				temp.setCharge(rset.getDouble("charge"));
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
