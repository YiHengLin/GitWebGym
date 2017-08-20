package YuiMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import YuiGlobalParam.yuiGlobalService;

public class YuiMemberDAO {
	DataSource ds = null;

	public YuiMemberDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(yuiGlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String selectFromMemberId = "select memberid,m_password,m_name,m_id_number,m_gender,m_bday,m_mail,m_mobile,m_tel,m_address,m_register,m_photo,is_blacklist,m_identity from members where memberid = ?";

	public YuiMemberBean select(String id) {
		YuiMemberBean yuimemberbean = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(selectFromMemberId);
			stmt.setString(1, id);
			rset = stmt.executeQuery();
			
			if (rset.next()) {
				yuimemberbean = new YuiMemberBean();
				yuimemberbean.setMemberID(rset.getString("memberid"));
				yuimemberbean.setM_password(rset.getString("m_password"));
				yuimemberbean.setM_name(rset.getString("m_name"));
				yuimemberbean.setM_id_number(rset.getString("m_id_number"));
				yuimemberbean.setM_gender(rset.getString("m_gender"));
				yuimemberbean.setM_bday(rset.getTimestamp("m_bday"));
				yuimemberbean.setM_mail(rset.getString("m_mail"));
				yuimemberbean.setM_mobile(rset.getString("m_mobile"));
				yuimemberbean.setM_tel(rset.getString("m_tel"));
				yuimemberbean.setM_address(rset.getString("m_address"));
				yuimemberbean.setM_Regi(rset.getTimestamp("m_register"));
				yuimemberbean.setM_photo(rset.getString("m_photo"));
				yuimemberbean.setBlist(rset.getString("is_blacklist"));
				yuimemberbean.setM_iden(rset.getString("m_identity"));
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

		return yuimemberbean;
	}

}
