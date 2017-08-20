package YuiOrderDetails;

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

import YuiAddOrder.YuiOrderBean;
import YuiEvent.YuiEventBean;
import YuiGlobalParam.yuiGlobalService;

public class YuiOrderDetailsDAO {
	DataSource ds = null;

	public YuiOrderDetailsDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(yuiGlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private static final String SelectOrderDetailFromOrderno = "select orderdetailno, orderno, eventno, charge from orderdetails where orderno = ?";

	public List<YuiOrderDetailsBean> select(int orderno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<YuiOrderDetailsBean> yuiorderdetailbeanlist = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SelectOrderDetailFromOrderno);
			stmt.setInt(1, orderno);
			rset = stmt.executeQuery();
			yuiorderdetailbeanlist = new Vector();

			while (rset.next()) {
				YuiOrderDetailsBean temp = new YuiOrderDetailsBean();
				temp.setOrderdetailno(rset.getString("orderdetailno"));
				temp.setOrderno(rset.getInt("orderno"));
				temp.setEventno(rset.getInt("eventno"));
				temp.setCharge(rset.getDouble("charge"));

				yuiorderdetailbeanlist.add(temp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return yuiorderdetailbeanlist;
	}
	
	private static final String SelectOrderDetailFromOrdernoAndEventnoSQL = "select orderdetailno, orderno, eventno, charge from orderdetails where orderno = ? and eventno = ?";
	
	public List<YuiOrderDetailsBean> SelectOrderDetailFromOrdernoAndEventno(int orderno, int eventno){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<YuiOrderDetailsBean> yuiorderdetailbeanlist = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SelectOrderDetailFromOrdernoAndEventnoSQL);
			stmt.setInt(1, orderno);
			stmt.setInt(2, eventno);
			rset = stmt.executeQuery();
			yuiorderdetailbeanlist = new Vector();

			while (rset.next()) {
				YuiOrderDetailsBean temp = new YuiOrderDetailsBean();
				temp.setOrderdetailno(rset.getString("orderdetailno"));
				temp.setOrderno(rset.getInt("orderno"));
				temp.setEventno(rset.getInt("eventno"));
				temp.setCharge(rset.getDouble("charge"));
				yuiorderdetailbeanlist.add(temp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return yuiorderdetailbeanlist;
	}
	
	
	
	private static final String InsertNewOrderDetailsSQL = "Insert into orderdetails(orderno, eventno, charge) values (?,?,?)";

	public List<YuiOrderDetailsBean> InsertNewOrderDetails(YuiOrderBean yuiorderbean, YuiEventBean testbean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<YuiOrderDetailsBean> yuiorderdetailsbeanlist = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(InsertNewOrderDetailsSQL);
			stmt.setInt(1, yuiorderbean.getOrderno());
			stmt.setInt(2, testbean.getEventno());
			stmt.setDouble(3, testbean.getCharge());
			int i = stmt.executeUpdate();
			if(i==1){
				yuiorderdetailsbeanlist = new Vector<>();
				yuiorderdetailsbeanlist = SelectOrderDetailFromOrdernoAndEventno(yuiorderbean.getOrderno(),testbean.getEventno());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return yuiorderdetailsbeanlist;
	}
}
