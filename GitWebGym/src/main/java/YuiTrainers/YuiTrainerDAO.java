package YuiTrainers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import YuiGlobalParam.yuiGlobalService;

public class YuiTrainerDAO {
	DataSource ds= null;
	public YuiTrainerDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup(yuiGlobalService.JNDI_DB_NAME);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static final String Find_BY_ID = "Select * from trainers where trainerid= ?";
	public YuiTrainerBean SelectByPrimaryKey(String trainerid){
		Connection conn = null;
		YuiTrainerBean yuitrainerbean = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(Find_BY_ID);
			stmt.setString(1, trainerid);
			rset = stmt.executeQuery();
			if (rset.next()) {
				yuitrainerbean = new YuiTrainerBean();
				yuitrainerbean.setTrainerID(rset.getString("trainerid"));
				yuitrainerbean.setT_password(rset.getString("t_password"));
				yuitrainerbean.setT_name(rset.getString("t_name"));
				yuitrainerbean.setT_id_number(rset.getString("t_id_number"));
				yuitrainerbean.setT_gender(rset.getString("t_gender"));
				yuitrainerbean.setT_bday(rset.getDate("t_bday"));
				yuitrainerbean.setT_mail(rset.getString("t_mail"));
				yuitrainerbean.setT_mobile(rset.getString("t_mobile"));
				yuitrainerbean.setT_tel(rset.getString("t_tel"));
				yuitrainerbean.setT_addr(rset.getString("t_address"));
				yuitrainerbean.setT_Regi(rset.getTimestamp("t_register"));
				yuitrainerbean.setT_pic(rset.getBlob("t_photo"));
				yuitrainerbean.setT_blist(rset.getBoolean("is_blacklist"));
				yuitrainerbean.setT_iden(rset.getString("t_identity"));
				yuitrainerbean.setT_field(rset.getString("t_field"));
				yuitrainerbean.setT_exp(rset.getString("t_experience"));
				yuitrainerbean.setT_lice(rset.getString("t_licence"));	
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
			return yuitrainerbean;
	}
	

}
