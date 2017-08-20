package member.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

public class memberDAO implements MemberDAO_interface {
	
	DataSource ds=null;
	//Connection conn=null;
	public memberDAO(){
		 try {
		 Context context = new InitialContext();
		 ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		 } catch (NamingException e) {
		 e.printStackTrace();
		 }

	}
//
//	public void getConnection() {
//		try {
//			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Temmujin_Group", "sa",
//					"123456");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("connectionEX:" + e);
//		}
//	}
	 
	private static final String INSERT = "Insert into members values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public MemberBean insert(MemberBean memberBean) throws SQLException {
		MemberBean result=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(INSERT);
			
			pstmt.setString(1, memberBean.getMemberID());
			pstmt.setString(2, memberBean.getM_password());
			pstmt.setString(3, memberBean.getM_name());
			pstmt.setString(4, memberBean.getM_id_number());
			pstmt.setString(5, memberBean.getM_gender());
			java.util.Date temp = memberBean.getM_bday();
			if (temp != null) {
				java.sql.Date someTime = new java.sql.Date(temp.getTime());
				pstmt.setDate(6, someTime);
			} else {
				pstmt.setDate(6, null);
			}
			pstmt.setString(7, memberBean.getM_mail());
			pstmt.setString(8, memberBean.getM_mobile());
			pstmt.setString(9, memberBean.getM_tel());
			pstmt.setString(10, memberBean.getM_addr());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(11, ts);
			pstmt.setBytes(12,memberBean.getM_photo() );
			pstmt.setString(13, memberBean.getBlist());
			pstmt.setString(14, memberBean.getM_iden());
			
			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = this.SelectByPrimaryKey(memberBean.getMemberID());
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	
	private static final String UPDATE = "UPDATE members SET m_password=?,m_name=?,m_id_number=?,m_gender=?,m_bday=?,m_mail=?,m_mobile=?,m_tel=?,m_address=?,m_register=?,m_photo=?,is_blacklist=?,m_identity=? WHERE memberid=?";
	@Override
	public MemberBean update(MemberBean memberBean) throws SQLException {
		MemberBean result=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			
	
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(UPDATE);
			
			pstmt.setString(1, memberBean.getM_password());
			pstmt.setString(2, memberBean.getM_name());
			pstmt.setString(3, memberBean.getM_id_number());
			pstmt.setString(4, memberBean.getM_gender());
			java.util.Date temp = memberBean.getM_bday();
			if (temp != null) {
				java.sql.Date someTime = new java.sql.Date(temp.getTime());
				pstmt.setDate(5, someTime);
			} else {
				pstmt.setDate(5, null);
			}
			pstmt.setString(6, memberBean.getM_mail());
			pstmt.setString(7, memberBean.getM_mobile());
			pstmt.setString(8, memberBean.getM_tel());
			pstmt.setString(9, memberBean.getM_addr());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(10, ts);
			pstmt.setBytes(11,memberBean.getM_photo() );
			pstmt.setString(12, memberBean.getBlist());
			pstmt.setString(13, memberBean.getM_iden());
			pstmt.setString(14, memberBean.getMemberID());
			
			
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	
	

	 private static final String DELETE = "DELETE members WHERE memberid=?";
	 @Override
	 public int delete(String memberid) {
	
	 int deleteCount=0;
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 try {
	 conn = ds.getConnection();
	 pstmt = conn.prepareStatement(DELETE);
	
	 pstmt.setString(1, memberid);
	 deleteCount = pstmt.executeUpdate();
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } finally {
	 if (pstmt != null) {
	 try {
	 pstmt.close();
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
	
	 return deleteCount;
	
	 }
	
	 private static final String Find_BY_ID = "Select * from members where memberid = ?";
	 @Override
	 public MemberBean SelectByPrimaryKey(String memberid) {
	 MemberBean result = null;
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 try {
	 conn = ds.getConnection();
	 pstmt = conn.prepareStatement(Find_BY_ID);
	 pstmt.setString(1, memberid);
	 rs = pstmt.executeQuery();
	 if (rs.next()) {
	 result = new MemberBean();
	 result.setMemberID(rs.getString("memberid"));
	 result.setM_password(rs.getString("m_password"));
	 result.setM_name(rs.getString("m_name"));
	 result.setM_id_number(rs.getString("m_id_number"));
	 result.setM_gender(rs.getString("m_gender"));
	 result.setM_bday(rs.getDate("m_bday"));
	 result.setM_mail(rs.getString("m_mail"));
	 result.setM_mobile(rs.getString("m_mobile"));
	 result.setM_tel(rs.getString("m_tel"));
	 result.setM_addr(rs.getString("m_address"));
	 result.setM_Regi(rs.getTimestamp("m_register"));
	 result.setBlist(rs.getString("is_blacklist"));
	 result.setM_iden(rs.getString("m_identity"));
	
	 }
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } finally {
	 if (rs!= null) {
	 try {
	 rs.close();
	 } catch (SQLException e) {
	 e.printStackTrace();
	 }
	 }
	 if (pstmt != null) {
	 try {
	 pstmt.close();
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
	
	
	 private static final String Find_all = "Select * from members ORDER BY memberid ";
	 @Override
	 public List<MemberBean> selectALL() {
	 MemberBean result = null;
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 try {
	 conn = ds.getConnection();
	 pstmt = conn.prepareStatement(Find_all);
	 rs = pstmt.executeQuery();
	 if (rs.next()) {
	 result = new MemberBean();
	 result.setMemberID(rs.getString("memberid"));
	 result.setM_password(rs.getString("m_password"));
	 result.setM_name(rs.getString("m_name"));
	 result.setM_id_number(rs.getString("m_id_number"));
	 result.setM_gender(rs.getString("m_gender"));
	 result.setM_bday(rs.getDate("m_bday"));
	 result.setM_mail(rs.getString("m_mail"));
	 result.setM_mobile(rs.getString("m_mobile"));
	 result.setM_tel(rs.getString("m_tel"));
	 result.setM_addr(rs.getString("m_address"));
	 result.setM_Regi(rs.getTimestamp("m_register"));
	 result.setBlist(rs.getString("is_blacklist"));
	 result.setM_iden(rs.getString("m_identity"));
	
	 }
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } finally {
	 if (rs!= null) {
	 try {
	 rs.close();
	 } catch (SQLException e) {
	 e.printStackTrace();
	 }
	 }
	 if (pstmt != null) {
	 try {
	 pstmt.close();
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
	
	 return null;
	 }
	
}
