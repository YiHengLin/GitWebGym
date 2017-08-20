package YuiLocations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import YuiGlobalParam.yuiGlobalService;

public class YuiLocationsDAO {
	DataSource ds = null;

	public YuiLocationsDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup(yuiGlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String selectFromLocationNo = "select locationno,locationname,l_address,openhour,phone from locations where locationno = ?";
	
	public YuiLocationsBean select(String locationno){
		YuiLocationsBean yuilocationsbean = null;
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rset = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(selectFromLocationNo);
			stmt.setString(1, locationno);
			rset = stmt.executeQuery();
			if(rset.next()){
				yuilocationsbean = new YuiLocationsBean();
				yuilocationsbean.setLocationno(rset.getString("locationno"));
				yuilocationsbean.setLocationname(rset.getString("locationname"));
				yuilocationsbean.setL_address(rset.getString("l_address"));
				yuilocationsbean.setOpenhour(rset.getString("openhour"));
				yuilocationsbean.setPhone(rset.getString("phone"));	
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rset!=null){
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
		return yuilocationsbean;
	}
	
	
	
	
}
