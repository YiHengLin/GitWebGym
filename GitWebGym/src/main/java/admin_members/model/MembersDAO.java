package admin_members.model;

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

//import Join.model.MemShopVO;
//import Member.model.MemVO;
import admin_members.model.MembersDAO_interface;

public class MembersDAO implements MembersDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO members (memberid,m_password,m_name,m_id_number,m_gender,m_bday,m_mail,m_mobile,m_tel,m_address,m_register,m_photo,is_blacklist,m_identity) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE members set memberid,m_password,m_name,m_id_number,m_gender,m_bday,m_mail,m_mobile,m_tel,m_address,m_register,m_photo,is_blacklist,m_identity where memberid = ?";
	private static final String DELETE_STMT = "DELETE FROM members where memberid = ?";
	private static final String GET_ONE_STMT = "SELECT memberid,m_password,m_name,m_id_number,m_gender,m_bday,m_mail,m_mobile,m_tel,m_address,m_register,m_photo,is_blacklist,m_identity FROM members where memberid = ?";
	private static final String GET_ALL_STMT = "SELECT memberid,m_password,m_name,m_id_number,m_gender,m_bday,m_mail,m_mobile,m_tel,m_address,m_register,m_photo,is_blacklist,m_identity FROM members order by memberid ";

	// 以下為新增SQL指令//
	private static final String UPDATE2 = "UPDATE members set m_password=?,is_blacklist=? where memberid = ?";
	private static final String GET_BLACKLIST = "SELECT memberid,m_password,m_name, m_id_number,m_gender,m_bday,m_mail,m_mobile,m_tel,m_address,m_register,m_photo,is_blacklist,m_identity FROM members where is_blacklist = 'y'";
	private static final String UPDATE_BLACKLIST = "UPDATE members set is_blacklist= 'y' where memberid = ?";
	private static final String UPDATE_NORMAL = "UPDATE members set is_blacklist= 'n' where memberid = ?";
	private static final String UPDATE_MALL = "UPDATE members set m_identity=trainer where memberid = ?";

	@Override
	public void insert(MembersBean membersBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, membersBean.getMemberid());
			pstmt.setString(2, membersBean.getM_password());
			pstmt.setString(3, membersBean.getM_name());
			pstmt.setString(4, membersBean.getM_id_number());
			pstmt.setString(5, membersBean.getM_gender());
			pstmt.setDate(6, membersBean.getM_bday());
			pstmt.setString(7, membersBean.getM_mail());
			pstmt.setString(8, membersBean.getM_mobile());
			pstmt.setString(9, membersBean.getM_tel());
			pstmt.setString(10, membersBean.getM_address());
			pstmt.setDate(11, membersBean.getM_register());
			pstmt.setBlob(12, membersBean.getM_photo());
			pstmt.setString(13, membersBean.getIs_blacklist());
			pstmt.setString(14, membersBean.getM_identity());

			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}


	@Override
	public void update(MembersBean membersBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, membersBean.getMemberid());
			pstmt.setString(2, membersBean.getM_password());
			pstmt.setString(3, membersBean.getM_name());
			pstmt.setString(4, membersBean.getM_id_number());
			pstmt.setString(5, membersBean.getM_gender());
			pstmt.setDate(6, membersBean.getM_bday());
			pstmt.setString(7, membersBean.getM_mail());
			pstmt.setString(8, membersBean.getM_mobile());
			pstmt.setString(9, membersBean.getM_tel());
			pstmt.setString(10, membersBean.getM_address());
			pstmt.setDate(11, membersBean.getM_register());
			pstmt.setBlob(12, membersBean.getM_photo());
			pstmt.setString(13, membersBean.getIs_blacklist());
			pstmt.setString(14, membersBean.getM_identity());
			

			pstmt.setString(15, membersBean.getMemberid());

			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// DELETE_STMT
	@Override
	public void delete(MembersBean membersBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, membersBean.getMemberid());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// GET_ONE_STMT
	@Override
	public MembersBean findByPrimaryKey(String memberid) {

		MembersBean membersBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memberid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// EventBean 也稱為 Domain objects
				membersBean = new MembersBean();

				membersBean.setMemberid(rs.getString("memberid"));
				membersBean.setM_password(rs.getString("m_password"));
				membersBean.setM_name(rs.getString("m_name"));
				membersBean.setM_id_number(rs.getString("m_id_number"));
				membersBean.setM_gender(rs.getString("m_gender"));
				membersBean.setM_bday(rs.getDate("m_bday"));
				membersBean.setM_mail(rs.getString("m_mail"));
				membersBean.setM_mobile(rs.getString("m_mobile"));
				membersBean.setM_tel(rs.getString("m_tel"));
				membersBean.setM_address(rs.getString("m_address"));
				membersBean.setM_register(rs.getDate("m_register"));
				membersBean.setM_photo(rs.getBlob("m_photo"));
				membersBean.setIs_blacklist(rs.getString("is_blacklist"));
				membersBean.setM_identity(rs.getString("m_identity"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return membersBean;
	}

	// GET_ALL_STMT
	@Override
	public List<MembersBean> getAll() {
		List<MembersBean> list = new ArrayList<MembersBean>();
		MembersBean membersBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MembersBean 亦為 Domain objects
				membersBean = new MembersBean();

				membersBean.setMemberid(rs.getString("memberid"));
				membersBean.setM_password(rs.getString("m_password"));
				membersBean.setM_name(rs.getString("m_name"));
				membersBean.setM_id_number(rs.getString("m_id_number"));
				membersBean.setM_gender(rs.getString("m_gender"));
				membersBean.setM_bday(rs.getDate("m_bday"));
				membersBean.setM_mail(rs.getString("m_mail"));
				membersBean.setM_mobile(rs.getString("m_mobile"));
				membersBean.setM_tel(rs.getString("m_tel"));
				membersBean.setM_address(rs.getString("m_address"));
				membersBean.setM_register(rs.getDate("m_register"));
				membersBean.setM_photo(rs.getBlob("m_photo"));
				membersBean.setIs_blacklist(rs.getString("is_blacklist"));
				membersBean.setM_identity(rs.getString("m_identity"));

				list.add(membersBean); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	@Override
	public void update2(MembersBean membersBean) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);

			pstmt.setString(1, membersBean.getM_password());
			pstmt.setString(2, membersBean.getIs_blacklist());
			pstmt.setString(3, membersBean.getMemberid());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// GET_BLACKLIST
	@Override
	public List<MembersBean> getBlackList() {
	List<MembersBean> list = new ArrayList<MembersBean>();
	MembersBean membersBean = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null ;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_BLACKLIST);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// membersBean 也稱為 Domain objects
			membersBean = new MembersBean();
			membersBean.setMemberid(rs.getString("memberid"));
			membersBean.setM_password(rs.getString("m_password"));
			membersBean.setM_name(rs.getString("m_name"));
			
			membersBean.setM_id_number(rs.getString("m_id_number"));
			membersBean.setM_gender(rs.getString("m_gender"));
			membersBean.setM_bday(rs.getDate("m_bday"));
			membersBean.setM_mail(rs.getString("m_mail"));
			membersBean.setM_mobile(rs.getString("m_mobile"));
			membersBean.setM_tel(rs.getString("m_tel"));
			membersBean.setM_address(rs.getString("m_address"));
			membersBean.setM_register(rs.getDate("m_register"));
			membersBean.setM_photo(rs.getBlob("m_photo"));
			membersBean.setIs_blacklist(rs.getString("is_blacklist"));
			membersBean.setM_identity(rs.getString("m_identity"));
			
			list.add(membersBean); // Store the row in the list
//			membersBean.setM_account(rs.getString("m_account"));
		}

		// Handle any driver errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. " + se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	return list;
}
		
	// UPDATE_BLACKLIST
	@Override
	public void updateBlackList(MembersBean membersBean) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BLACKLIST);

			pstmt.setString(1, membersBean.getMemberid());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	// UPDATE_NORMAL
	@Override
	public void updateNormal(MembersBean membersBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_NORMAL);

			pstmt.setString(1, membersBean.getMemberid());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String memberid) {
		// TODO Auto-generated method stub
		
	}
	
	// GET_MALL_LIST
//	public List<MemShopVO> getMallList() {
//		List<MemShopVO> list = new ArrayList<MemShopVO>();
//		MemShopVO meshVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_MALL_LIST);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// memVO 也稱為 Domain objects
//				meshVO = new MemShopVO();
//				meshVO.setS_id(rs.getInt("s_id"));
//				meshVO.setM_account(rs.getString("m_account"));
//				meshVO.setM_name(rs.getString("m_name"));			
//				list.add(meshVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}


	// UPDATE_MALL
//	public void updateMall(MemVO memVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE_MALL);
//			
//			pstmt.setInt(1, memVO.getM_id());
//			pstmt.executeUpdate();
//			
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
	

}

	
	
	

