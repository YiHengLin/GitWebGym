package YuiRooms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import YuiGlobalParam.yuiGlobalService;



public class YuiRoomsDAO {
	DataSource ds = null;
	public YuiRoomsDAO(){
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup(yuiGlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String selectFromRoomNo = "select roomno,locationno,title,capacity from rooms where roomno = ?";
	
	public YuiRoomsBean select(String roomno){
		YuiRoomsBean yuiroomsbean = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(selectFromRoomNo);
			stmt.setString(1, roomno);
			rset = stmt.executeQuery();
			if(rset.next()){
				yuiroomsbean = new YuiRoomsBean();
				yuiroomsbean.setRoomno(rset.getString("roomno"));
				yuiroomsbean.setLocationno(rset.getString("locationno"));
				yuiroomsbean.setTitle(rset.getString("title"));
				yuiroomsbean.setCapacity(rset.getString("capacity"));
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
		return yuiroomsbean;
	}
	
}
