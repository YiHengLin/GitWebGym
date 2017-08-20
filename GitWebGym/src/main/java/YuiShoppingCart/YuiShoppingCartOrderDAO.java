package YuiShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import YuiGlobalParam.yuiGlobalService;
import YuiOrderDetails.YuiOrderDetailsBean;
import member.model.MemberBean;

public class YuiShoppingCartOrderDAO {
	private DataSource ds = null;
	
	public YuiShoppingCartOrderDAO(){
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup(yuiGlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String InsertOrderSQL = "Insert into orders(memberid, totalcharge, enrollday, is_pay) values (?,?,?,?)";
	private static final String InsertNewOrderDetailsSQL = "Insert into orderdetails(orderno, eventno, charge) values (?,?,?)";
	
	public int insertOrder(MemberBean memberBean,double Subtotal,List<YuiShoppingCartBean> orderdetails) throws SQLException{
		int is_success = 0;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		ResultSet rset = null;
		PreparedStatement stmt2 = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);  // 開啟JDBC Transaction
			stmt1 = conn.prepareStatement(InsertOrderSQL, Statement.RETURN_GENERATED_KEYS);
			stmt1.setString(1, memberBean.getMemberID());
			stmt1.setDouble(2, Subtotal);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = Timestamp.valueOf(sdf.format(time));
			stmt1.setTimestamp(3, time);
			stmt1.setString(4, "已繳費");
			stmt1.executeUpdate();
			int orderno = 0;
			rset = stmt1.getGeneratedKeys();
			if(rset.next()){
				orderno = rset.getInt(1);
			}else{
				throw new SQLException("新增訂單失敗");
			}
			
			stmt2 = conn.prepareStatement(InsertNewOrderDetailsSQL);
			for(YuiShoppingCartBean odb : orderdetails){
				stmt2.setInt(1, orderno);
				stmt2.setInt(2,odb.getEventno());
				stmt2.setDouble(3,odb.getCharge());
				int count = stmt2.executeUpdate();
				stmt2.clearParameters();
			}

			conn.commit();
			is_success = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("資料還原");
			if(conn!=null){
				conn.rollback();
			}
			return is_success;
		}finally{
			if(rset!=null){
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt1!=null){
				try {
					stmt1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt2!=null){
				try {
					stmt2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return is_success;
		
	}
}
