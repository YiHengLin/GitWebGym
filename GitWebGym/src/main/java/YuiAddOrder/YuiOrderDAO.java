package YuiAddOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import YuiEvent.YuiEventBean;
import YuiGlobalParam.yuiGlobalService;
import member.model.MemberBean;

public class YuiOrderDAO {
	private DataSource ds = null;
	public YuiOrderDAO(){
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup(yuiGlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<YuiOrderBean> getAllOrder(){
		return selectAll();	
	}
	
	private static final String SelectOrderFromOrderNoSQL = "select * from orders where orderno = ?";
	public YuiOrderBean selectFromOrderNo(int orderno){
		YuiOrderBean yuiorderbean= null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SelectOrderFromOrderNoSQL);
			stmt.setInt(1, orderno);
			rset = stmt.executeQuery();
			if(rset.next()){
				yuiorderbean = new YuiOrderBean();
				yuiorderbean.setOrderno(rset.getInt("orderno"));
				yuiorderbean.setmemberID(rset.getString("memberID"));
				yuiorderbean.setTotalcharge(rset.getString("totalcharge"));
				yuiorderbean.setEnrollday(rset.getTimestamp("enrollday"));
				yuiorderbean.setIs_pay(rset.getString("is_pay"));
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
		
		
		
		
		return yuiorderbean;
	}
	
	
	private static final String SeleAllctOrder = "Select orderno ,memberID, totalcharge, enrollday, is_pay from orders";
	public List<YuiOrderBean> selectAll(){
		List<YuiOrderBean> yuiorderbeanlist = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SeleAllctOrder);
			rset = stmt.executeQuery();
			yuiorderbeanlist = new Vector<>();
			while(rset.next()){
				YuiOrderBean temp = new YuiOrderBean();
				temp.setOrderno(rset.getInt("orderno"));
				temp.setmemberID(rset.getString("memberID"));
				temp.setTotalcharge(rset.getString("totalcharge"));
				temp.setEnrollday(rset.getTimestamp("enrollday"));
				temp.setIs_pay(rset.getString("is_pay"));
				yuiorderbeanlist.add(temp);
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

		return yuiorderbeanlist;
	}
	
	
	private static final String SelectOrderFromId = "Select orderno ,memberID, totalcharge, enrollday, is_pay from orders where memberID = ?";
	public List<YuiOrderBean> select(String memberID){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<YuiOrderBean> listyuiorderbean= null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SelectOrderFromId);
			stmt.setString(1, memberID);
			rset = stmt.executeQuery();
			listyuiorderbean = new Vector();
			
			while(rset.next()){
				YuiOrderBean temp = new YuiOrderBean();
				temp.setOrderno(rset.getInt("orderno"));
				temp.setmemberID(rset.getString("memberID"));
				temp.setTotalcharge(rset.getString("totalcharge"));
				temp.setEnrollday(rset.getTimestamp("enrollday"));
				temp.setIs_pay(rset.getString("is_pay"));
				listyuiorderbean.add(temp);
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
			if(stmt != null){
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
		
		return listyuiorderbean;
	}
	
	private static final String SelectOrderFromTime = "Select orderno ,memberID, totalcharge, enrollday, is_pay from orders where enrollday = ?";
	public YuiOrderBean select(Timestamp time){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		YuiOrderBean yuiorderbean= null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SelectOrderFromTime);
			stmt.setTimestamp(1,time);
			rset = stmt.executeQuery();
			while(rset.next()){
				yuiorderbean = new YuiOrderBean();
				yuiorderbean.setOrderno(rset.getInt("orderno"));
				yuiorderbean.setmemberID(rset.getString("memberID"));
				yuiorderbean.setTotalcharge(rset.getString("totalcharge"));
				yuiorderbean.setEnrollday(rset.getTimestamp("enrollday"));
				yuiorderbean.setIs_pay(rset.getString("is_pay"));	
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
			if(stmt != null){
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
		
		return yuiorderbean;
	}
	
	
	
	private static final String InsertOrderString = "Insert into orders(memberID, totalcharge, enrollday, is_pay) values (?,?,?,?)";
	
	public YuiOrderBean InsertNewOrder(MemberBean memberBean, YuiEventBean yuieventbean){
		Connection conn = null;
		YuiOrderBean yuiOrderbean = null;
		PreparedStatement stmt = null;
		Timestamp time = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(InsertOrderString);
			stmt.setString(1, memberBean.getMemberID());
			stmt.setDouble(2, yuieventbean.getCharge());
			time = new Timestamp(System.currentTimeMillis());
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = Timestamp.valueOf(sdf.format(time));
			stmt.setTimestamp(3, time);
			stmt.setString(4, "已繳費");
			int i = stmt.executeUpdate();
			if(i==1){
				yuiOrderbean = select(time);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
		return yuiOrderbean;
		
	}
	
	
	
	
}
